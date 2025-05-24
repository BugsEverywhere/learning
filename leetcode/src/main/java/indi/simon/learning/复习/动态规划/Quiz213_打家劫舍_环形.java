package indi.simon.learning.复习.动态规划;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 198的变形，首尾相连的话，将dp分为两种情况，0~length-2以及1~length-1，分别运用dp
public class Quiz213_打家劫舍_环形 {

    public static void main(String[] args) {
        Quiz213_打家劫舍_环形 quiz213Hinted = new Quiz213_打家劫舍_环形();
        int[] nums = new int[]{1, 2, 3, 1};
        int res = quiz213Hinted.rob(nums);
        System.out.println(res);
    }

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return nums[0];
        }

        return Math.max(dp(nums, 0, n - 2), dp(nums, 1, n - 1));
    }

    private int dp(int[] nums, int start, int end) {
        int n = nums.length;
        //dp[i][0]表示偷第i家所能获取的最大价值
        //dp[i][1]表示不偷第i家所能获取的最大价值
        int[][] dp = new int[n][2];

        for (int i = start; i <= end; i++) {
            if (i == 0) {
                dp[i][0] = nums[0];
                dp[i][1] = 0;
            } else {
                dp[i][0] = dp[i - 1][1] + nums[i];
                dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            }
        }

        return Math.max(dp[end][0], dp[end][1]);
    }
}
