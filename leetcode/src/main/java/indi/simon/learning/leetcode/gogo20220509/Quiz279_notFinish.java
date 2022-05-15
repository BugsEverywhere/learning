package indi.simon.learning.leetcode.gogo20220509;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz279_notFinish {

    public static void main(String[] args) {

    }

    public int numSquares(int n) {

        int maxTotalSquare = 100;

        for (int i = maxTotalSquare; i >= 0; i--) {
            if (Math.pow(i, 2) <= n) {
                maxTotalSquare = i;
            }
        }

        //如果就等于n，那么直接返回
        if (Math.pow(maxTotalSquare, 2) == n) {
            return 1;
        }

        //填充第0行
        Integer[][][] dp = new Integer[maxTotalSquare][n + 1][2];
        for (int j = 0; j < n + 1; j++) {
            if (j * Math.pow(maxTotalSquare, 2) < n) {
                dp[0][j][0] = j;
                dp[0][j][1] = j * (int) Math.pow(maxTotalSquare, 2);
            }
        }

        //状态转移
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < n + 1; j++) {
                for (int k = 0; k < n + 1; k++) {
//                    if(){
//
//                    }
                }

            }

        }


        return -1;
    }
}
