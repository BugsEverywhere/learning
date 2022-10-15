package indi.simon.learning.leetcode.gogo20221003;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz927_timeExceed {

    public static void main(String[] args) {
        Quiz927_timeExceed quiz927NeedReviewed = new Quiz927_timeExceed();
        int[] res = quiz927NeedReviewed.threeEqualParts(new int[]{0, 0, 0, 0, 0});
        System.out.println(res);
    }

    //todo: 超时

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
