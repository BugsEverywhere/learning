package indi.simon.learning.leetcode.gogo20220530;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz416 {

    public static void main(String[] args) {
        Quiz416 quiz416 = new Quiz416();
        int[] nums = new int[]{100};
        boolean res = quiz416.canPartition(nums);
        System.out.println(res);
    }

    public boolean canPartition(int[] nums) {

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        if (sum % 2 != 0) {
            return false;
        }

        boolean[][] dp = new boolean[nums.length][sum + 1];
        dp[0][nums[0]] = true;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (j >= nums[i]) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - nums[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[nums.length - 1][sum / 2];
    }
}
