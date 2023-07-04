package indi.simon.learning.leetcode.gogo20230702;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2679 {

    public static void main(String[] args) {

    }

    public int matrixSum(int[][] nums) {

        for (int i = 0; i < nums.length; i++) {
            Arrays.sort(nums[i]);
        }

        int score = 0;
        for (int i = nums[0].length - 1; i >= 0; i--) {
            int maxOfCol = 0;
            for (int j = 0; j < nums.length; j++) {
                maxOfCol = Math.max(maxOfCol, nums[j][i]);
            }
            score += maxOfCol;
        }
        return score;
    }

}
