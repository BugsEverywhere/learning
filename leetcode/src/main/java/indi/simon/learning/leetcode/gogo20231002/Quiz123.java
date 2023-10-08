package indi.simon.learning.leetcode.gogo20231002;

/**
 * Created by Chen Zhuo on 2023/10/8.
 */
public class Quiz123 {
    public static void main(String[] args) {
        Quiz123 quiz123 = new Quiz123();
        int res = quiz123.maxProfit(new int[]{1, 2, 3, 4, 5});
        System.out.println(res);
    }

    public int maxProfit(int[] prices) {

        int[][][] dp = new int[prices.length][2][3];
        //dp[i][0][1]表示第i天持有现金（不买入股票，或者卖出之前已经持有的股票），并且到i为止只进行了1次交易的情况下，的最大利润
        dp[0][0][0] = 0;
        dp[0][0][1] = 0;
        dp[0][0][2] = 0;

        //dp[i][1][1]表示第i天持有股票（买入股票，或者不卖出之前已有的股票），并且到i为止只进行了1次交易的情况下，的最大利润
        dp[0][1][0] = -prices[0];
        dp[0][1][1] = -prices[0];
        dp[0][1][2] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            //当天之前进行了0次交易的情况下，持有现金的状态只可能从前一天相同状态转移而来
            dp[i][0][0] = dp[i - 1][0][0];
            dp[i][1][0] = Math.max(dp[i - 1][0][0] - prices[i], dp[i - 1][1][0]);

            dp[i][0][1] = Math.max(dp[i - 1][0][1], dp[i - 1][1][0] + prices[i]);
            dp[i][1][1] = Math.max(dp[i - 1][0][1] - prices[i], dp[i - 1][1][1]);

            if ((i+1) / 2 < 2) {
                continue;
            }
            dp[i][0][2] = Math.max(dp[i - 1][0][2], dp[i - 1][1][1] + prices[i]);
            dp[i][1][2] = Math.max(dp[i - 1][0][2] - prices[i], dp[i - 1][1][2]);
        }
        int n = prices.length - 1;

        int num1 = Math.max(dp[n][0][0], Math.max(dp[n][0][1], dp[n][0][2]));
        int num2 = Math.max(dp[n][1][0], Math.max(dp[n][1][1], dp[n][1][2]));

        return Math.max(num1, num2);
    }


}
