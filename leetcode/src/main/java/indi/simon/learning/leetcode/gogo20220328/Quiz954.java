package indi.simon.learning.leetcode.gogo20220328;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz954 {

    public static void main(String[] args) {
        Quiz954 quiz954 = new Quiz954();
        int[] arr = new int[]{-33, 0};
        boolean res = quiz954.canReorderDoubled(arr);
        System.out.println(res);
    }

    public boolean canReorderDoubled(int[] arr) {
        if (arr.length == 0) {
            return false;
        }
        Map<Integer, Integer> numCountMap = new HashMap<>(arr.length);
        for (int singleNum : arr) {
            numCountMap.merge(singleNum, 1, Integer::sum);
        }

        Arrays.sort(arr);

        int twoTimesCount = 0;
        for (int singleNum : arr) {
            Integer thisNumCount = numCountMap.get(singleNum);
            if (thisNumCount == null) {
                //已经成对用完了，不用计算在内
                continue;
            }
            //0的情况单独处理
            if (singleNum == 0) {
                //todo: 第二次提交没考虑0的特殊情况
                int leftZeroCount = numCountMap.get(singleNum);
                if (leftZeroCount >= 2) {
                    twoTimesCount++;
                    if (leftZeroCount - 2 == 0) {
                        numCountMap.remove(singleNum);
                    } else {
                        numCountMap.put(singleNum, leftZeroCount - 2);
                    }
                }
            } else {
                Integer twoTimesNum = numCountMap.get(2 * singleNum);
                if (twoTimesNum != null) {
                    //剩余的数里面，存在自己的两倍数
                    twoTimesCount++;
                    //两倍数要减一
                    if (twoTimesNum - 1 == 0) {
                        numCountMap.remove(2 * singleNum);
                    } else {
                        numCountMap.put(2 * singleNum, twoTimesNum - 1);
                    }
                    //本数字也要减一
                    if (thisNumCount - 1 == 0) {
                        numCountMap.remove(singleNum);
                    } else {
                        numCountMap.put(singleNum, thisNumCount - 1);
                    }
                    //todo: 第一次提交没考虑已经成对的数字的个数需要减一的情况
                }
            }
        }

        if (twoTimesCount >= arr.length / 2) {
            return true;
        }
        return false;
    }
}
