package indi.simon.learning.leetcode.gogo20221128;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz813 {

    public static void main(String[] args) {
        Quiz813 quiz813 = new Quiz813();
        double res = quiz813.largestSumOfAverages(new int[]{4, 1, 7, 5, 6, 2, 3}, 4);
        System.out.println(res);
    }

    public double largestSumOfAverages(int[] nums, int k) {
        Arrays.sort(nums);

        double sum = 0;
        for (int i = nums.length - 1; i >= 0; ) {
            if (k > 1) {
                sum += nums[i];
                k--;
                i--;
            } else {
                double subSum = 0;
                int originI = i;
                while (i >= 0) {
                    subSum += nums[i];
                    i--;
                }
                sum += subSum / (originI + 1);
            }
        }

        return sum;
    }

}
