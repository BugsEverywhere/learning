package indi.simon.learning.leetcode.gogo20230306;

/**
 * @author chenzhuo(zhiyue)
 */
public class Offer47 {

    public static void main(String[] args) {

    }

    public int maxValue(int[][] grid) {

        int[][] dp = new int[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0) {
                    if (j == 0) {
                        dp[i][j] = grid[i][j];
                    } else {
                        dp[i][j] = dp[i][j - 1] + grid[i][j];
                    }
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }

}
