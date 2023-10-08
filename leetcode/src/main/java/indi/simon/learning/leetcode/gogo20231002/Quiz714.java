package indi.simon.learning.leetcode.gogo20231002;

/**
 * Created by Chen Zhuo on 2023/10/7.
 */
public class Quiz714 {

    public static void main(String[] args) {
        Quiz714 quiz714 = new Quiz714();
        int res = quiz714.maxProfitV2(new int[]{1, 3, 7, 5, 10, 3}, 3);
        System.out.println(res);
    }

    public int maxProfit(int[] prices, int fee) {
        //dp[i]表示第i天能获取的最大利润
        int[] dp = new int[prices.length];

        for (int i = 1; i < prices.length; i++) {
            int j = i - 1;
            dp[i] = Math.max(dp[j], dp[i]);
            for (; j >= -1; j--) {
                if (prices[i] - fee > prices[j + 1]) {
                    dp[i] = Math.max(dp[i], (j == -1 ? 0 : dp[j]) + prices[i] - fee - prices[j + 1]);
                }
            }
        }


        return dp[prices.length - 1];
    }

    public int maxProfitV2(int[] prices, int fee) {
        //dp[i][0]代表第i天不持有股票（卖出或者不买入）所获得的最大利润，dp[i][1]代表第i天买入股票获得的最大利润
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            //第i天按兵不动/第i天卖出股票
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            //第i天按兵不动/第i天买入股票
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return Math.max(dp[prices.length - 1][0],dp[prices.length - 1][0]);
    }

}
