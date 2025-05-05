package indi.simon.learning.复习.动态规划;

/**
 * Created by Chen Zhuo on 2025/5/4.
 */
public class Quiz983_旅行出游乘火车 {

    public static void main(String[] args) {
        Quiz983_旅行出游乘火车 quiz983旅行出游乘火车 = new Quiz983_旅行出游乘火车();
        int res = quiz983旅行出游乘火车.mincostTickets(new int[]{1, 4, 6, 7, 8, 20}, new int[]{7, 2, 15});
        System.out.println(res);
    }

    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        //dp[i]表示在截止第days[i]天所花费的最少费用
        int[] dp = new int[n];
        //todo: 这里要注意，初始化单日的花销，应该取三种套票中最便宜的一个来初始化，而不是简单买单日票
        dp[0] = Math.min(costs[0], Math.min(costs[1], costs[2]));

        for (int i = 1; i < days.length; i++) {
            //todo: 同理，dp到后续每一日，初始化单日的花销也应该注意这一点
            dp[i] = dp[i - 1] + Math.min(costs[0], Math.min(costs[1], costs[2]));
            for (int j = i - 1; j >= 0; j--) {
                if (days[i] - days[j] >= 30) {
                    continue;
                }
                if (days[i] - days[j] < 7) {
                    dp[i] = Math.min(dp[i], (j > 0 ? dp[j - 1] : 0) + costs[1]);
                }
                if (days[i] - days[j] < 30) {
                    dp[i] = Math.min(dp[i], (j > 0 ? dp[j - 1] : 0) + costs[2]);
                }
            }
        }

        return dp[n - 1];
    }

}
