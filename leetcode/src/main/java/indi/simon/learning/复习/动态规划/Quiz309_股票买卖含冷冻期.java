package indi.simon.learning.复习.动态规划;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz309_股票买卖含冷冻期 {

    public static void main(String[] args) {
        Quiz309_股票买卖含冷冻期 quiz309 = new Quiz309_股票买卖含冷冻期();
        int res = quiz309.maxProfit(new int[]{1});
        System.out.println(res);
    }

    public int maxProfit(int[] prices) {

        //dp[i] 表示第i天能获取到的最大利润，其中dp[i][0]代表当天买入状态下的最大利润，dp[i][1]代表当天卖出状态下的最大利润，dp[i][2]代表当天冷冻期状态下的最大利润
        int[][] dp = new int[prices.length][3];

        for (int i = 1; i < prices.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                //当天买入，那么该状态可能来自dp[i-1][2]，以及dp[i-2][1]、dp[i-2][2]，以及dp[i-3][1]、dp[i-3][2].......以此类推
                if (j == i - 1) {
                    dp[i][0] = Math.max(dp[i][0], dp[j][2]);
                } else {
                    dp[i][0] = Math.max(Math.max(dp[i][0], dp[j][2]), dp[j][1]);
                }

                //当天卖出，那么该状态可能来自dp[i-1][0]，dp[i-2][0]，dp[i-3][0]....依次类推
                dp[i][1] = Math.max(dp[i][1], dp[j][0] + prices[i] - prices[j]);

                //当天为冷冻期，那么该状态只可能来自dp[i-1][1]
                if (j == i - 1) {
                    dp[i][2] = dp[j][1];
                }
            }
        }

        return Math.max(dp[dp.length - 1][0], Math.max(dp[dp.length - 1][1], dp[dp.length - 1][2]));
    }

}
