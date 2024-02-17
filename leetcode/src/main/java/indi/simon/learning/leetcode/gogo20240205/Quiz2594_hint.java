package indi.simon.learning.leetcode.gogo20240205;

/**
 * Created by Chen Zhuo on 2024/2/9.
 */
public class Quiz2594_hint {

    public static void main(String[] args) {
        Quiz2594_hint quiz2594Hint = new Quiz2594_hint();
        long res = quiz2594Hint.repairCarsV2(new int[]{31,31,5,19,19,10,31,18,19,3,16,20,4,16,2,25,10,16,23,18,21,23,28,6,7,29,11,11,19,20,24,19,26,12,29,29,1,14,17,26,24,7,11,28,22,14,31,12,3,19,16,26,11}, 736185);
        System.out.println(res);
    }

    //左闭右开写法
    public long repairCars(int[] ranks, int cars) {
        int minR = ranks[0];
        for (int r : ranks) {
            minR = Math.min(minR, r);
        }
        long left = 1;
        long right = (long) minR * cars * cars;
        while (left < right) {
            long mid = (left + right) >> 1;
            long s = 0;
            for (int r : ranks) {
                s += Math.sqrt(mid / r);
            }
            if (s >= cars) {
                right = mid; // 满足要求
            } else {
                left = mid + 1;
            }
        }
        return left; // 最小的满足要求的值
    }


    //开区间写法
    public long repairCarsV2(int[] ranks, int cars) {
        int minR = ranks[0];
        for (int r : ranks) {
            minR = Math.min(minR, r);
        }
        long left = 0;
        long right = (long) minR * cars * cars;
        while (left + 1 < right) {
            long mid = (left + right) >> 1;
            long s = 0;
            for (int r : ranks) {
                s += Math.sqrt(mid / r);
            }
            if (s >= cars) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }


}
