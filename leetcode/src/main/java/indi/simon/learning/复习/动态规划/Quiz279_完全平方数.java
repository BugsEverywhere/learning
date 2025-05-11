package indi.simon.learning.复习.动态规划;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz279_完全平方数 {

    public static void main(String[] args) {
        Quiz279_完全平方数 quiz279完全平方数 = new Quiz279_完全平方数();
        int res = quiz279完全平方数.numSquares(43);
        System.out.println(res);
    }

    public int numSquares(int n) {
        //dp[i]代表组成i的最少完全平方数的数量
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 1);
        //todo: 从1开始的dp，dp[0]也要记得初始化
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i - j * j] + 1, dp[i]);
            }
        }
        return dp[n];
    }


}
