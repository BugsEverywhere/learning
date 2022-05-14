package indi.simon.learning.leetcode.gogo20220509;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 198的变形，首尾相连的话，将dp分为两种情况，0~length-2以及1~length-1，分别运用dp
public class Quiz213_hinted {

    public static void main(String[] args) {
        Quiz213_hinted quiz213Hinted = new Quiz213_hinted();
        int[] nums = new int[]{1,2,3,1};
        int res = quiz213Hinted.rob(nums);
        System.out.println(res);
    }

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        return Math.max(robInternal(nums, 0, nums.length - 2), robInternal(nums, 1, nums.length - 1));
    }

    private int robInternal(int[] nums, int start, int end) {
        int[][] dp = new int[end + 2][2];
        dp[0][0] = 0;
        dp[0][1] = 0;

        for (int i = start; i <= end; i++) {
            dp[i + 1][0] = dp[i][1] + nums[i];
            dp[i + 1][1] = Math.max(dp[i][0], dp[i][1]);
        }

        return Math.max(dp[end + 1][0], dp[end + 1][1]);
    }
}
