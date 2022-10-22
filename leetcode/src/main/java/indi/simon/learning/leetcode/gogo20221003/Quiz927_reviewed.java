package indi.simon.learning.leetcode.gogo20221003;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz927_reviewed {

    public static void main(String[] args) {
        Quiz927_reviewed quiz927NeedReviewed = new Quiz927_reviewed();
        int[] res = quiz927NeedReviewed.threeEqualPartsOfficial(new int[]{1,0,1,0,1});
        System.out.println(res);
    }


    //todo: 自己仿照官方思路实现的写法
    public int[] threeEqualPartsOfficial(int[] arr) {
        int oneCount = 0;
        for (int num : arr) {
            if (num == 1) {
                oneCount++;
            }
        }

        //如果1的个数都不能被3整除
        if (oneCount % 3 != 0) {
            return new int[]{-1, -1};
        }
        //一个1也没有，那就都是0，肯定可以
        if (oneCount == 0) {
            return new int[]{0, arr.length - 1};
        }

        //根据所有的1将arr三等分，标记每一段最后一位1的位置
        Integer firstMark = null;
        Integer secondMark = null;
        int oneSum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                oneSum++;
                if (oneSum == oneCount / 3) {
                    if (firstMark == null) {
                        firstMark = i;
                    } else if (secondMark == null) {
                        secondMark = i;
                    }
                    //oneSum复位
                    oneSum = 0;
                }
            }
        }

        //因为第三段末尾的0是固定的，数一下第三段结尾有几个0，那么每一段的末尾0的个数理应一致
        int tailZeroCount = 0;
        for (int j = arr.length - 1; j > secondMark; j--) {
            if (arr[j] == 0) {
                tailZeroCount++;
            } else {
                break;
            }
        }

        //根据末尾0的数量验证一下第一段和第二段末尾是否有足够0
        for (int i = 1; i <= tailZeroCount; i++) {
            //如果中途遇到1，那么直接退出
            if (arr[firstMark + i] == 1 || arr[secondMark + i] == 1) {
                return new int[]{-1, -1};
            }
        }

        //然后比较三段中的每一位是否相同
        //至此，理论上的三段的范围就出来了，分别是[0, firstMark + tailZeroCount], [firstMark + tailZeroCount + 1, secondMark + tailZeroCount],
        // [secondMark + tailZeroCount + 1, arr.length - 1]
        // 因为之前已经数过每一段末尾0的个数，因此可以跳过每一段末尾的0，比较剩下的
        int thirdIndex = arr.length - 1 - tailZeroCount;
        int secondIndex = secondMark;
        int firstIndex = firstMark;
        while (firstIndex >= 0 && secondIndex > firstMark + tailZeroCount && thirdIndex > secondMark + tailZeroCount) {
            if (arr[firstIndex] == arr[secondIndex] && arr[secondIndex] == arr[thirdIndex]) {
                firstIndex--;
                secondIndex--;
                thirdIndex--;
            } else {
                return new int[]{-1, -1};
            }
        }

        return new int[]{firstMark + tailZeroCount, secondMark + tailZeroCount + 1};

    }


    //todo: 超时，不要一上来就dfs递归，有时候能数学统计一下的事情能不dfs就不dfs，毕竟dfs就是穷举
    private int[] res;
    private boolean weGotIt;

    public int[] threeEqualParts(int[] arr) {
        res = new int[2];
        Arrays.fill(res, -1);
        weGotIt = false;
        threeEqualPartsInternal(arr, 0, 1, null);
        return res;
    }

    private void threeEqualPartsInternal(int[] arr, int start, int round, Integer iRes) {
        if (weGotIt) {
            return;
        }

        if (round == 1) {
            //第一轮
            for (int i = start; i < arr.length; i++) {
                threeEqualPartsInternal(arr, i + 1, round + 1, i);
            }
        } else if (round == 2) {
            //第二轮
            for (int j = start; j < arr.length; j++) {
                if (numEquals(arr, 0, iRes, iRes + 1, j)) {
                    threeEqualPartsInternal(arr, j + 1, round + 1, iRes);
                }
            }
        } else if (start < arr.length) {
            //第三轮
            if (numEquals(arr, 0, iRes, start, arr.length - 1)) {
                res[0] = iRes;
                res[1] = start;
                weGotIt = true;
            }
        }
    }

    private boolean numEquals(int[] arr, int start1, int end1, int start2, int end2) {
        int i = start1;
        int j = start2;

        while (arr[i] == 0) {
            if (i < end1) {
                i++;
            } else {
                break;
            }
        }

        while (arr[j] == 0) {
            if (j < end2) {
                j++;
            } else {
                break;
            }
        }

        if (end1 - i != end2 - j) {
            return false;
        }

        while (i <= end1 && j <= end2) {
            if (arr[i] != arr[j]) {
                return false;
            }
            i++;
            j++;
        }

        return true;
    }


}
