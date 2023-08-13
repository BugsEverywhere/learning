package indi.simon.learning.leetcode.gogo20230807;


/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1289 {

    public static void main(String[] args) {
        Quiz1289 quiz1289 = new Quiz1289();
        int res = quiz1289.minFallingPathSum(new int[][]{
                {7}
        });
        System.out.println(res);
    }

    public int minFallingPathSum(int[][] grid) {
        //dp[i]表示到目前为止的最小下降和
        int[][] dp = new int[grid.length][grid[0].length];

        int firstMin = Integer.MAX_VALUE;
        for (int j = 0; j < grid[0].length; j++) {
            dp[0][j] = grid[0][j];
            firstMin = Math.min(firstMin, dp[0][j]);
        }

        int res = Integer.MAX_VALUE;
        for (int i = 1; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int minExceptJ = Integer.MAX_VALUE;
                for (int k = 0; k < grid[0].length; k++) {
                    if (k == j) {
                        continue;
                    }
                    minExceptJ = Math.min(dp[i - 1][k], minExceptJ);
                }
                dp[i][j] = minExceptJ + grid[i][j];
                if (i == grid.length - 1) {
                    res = Math.min(res, dp[i][j]);
                }
            }
        }

        return grid.length == 1 ? firstMin : res;
    }

}
