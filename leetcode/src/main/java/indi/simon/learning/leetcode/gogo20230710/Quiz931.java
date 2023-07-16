package indi.simon.learning.leetcode.gogo20230710;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz931 {

    public static void main(String[] args) {
        Quiz931 quiz931 = new Quiz931();
        int res = quiz931.minFallingPathSum(new int[][]{
                {2, 1, 3},
                {6, 5, 4},
                {7, 8, 9}
        });
        System.out.println(res);
    }

    public int minFallingPathSum(int[][] matrix) {

        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int j = 0; j < matrix[0].length; j++) {
            dp[0][j] = matrix[0][j];
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int leftUp = Integer.MAX_VALUE;
                int up = dp[i - 1][j] + matrix[i][j];
                int rightUp = Integer.MAX_VALUE;

                if (j != 0) {
                    leftUp = dp[i - 1][j - 1] + matrix[i][j];
                }
                if (j != matrix[0].length - 1) {
                    rightUp = dp[i - 1][j + 1] + matrix[i][j];
                }
                dp[i][j] = Math.min(leftUp, Math.min(rightUp, up));
            }
        }
        int minVal = Integer.MAX_VALUE;
        for (int j = 0; j < dp[0].length; j++) {
            minVal = Math.min(minVal, dp[dp.length - 1][j]);
        }

        return minVal;
    }


}
