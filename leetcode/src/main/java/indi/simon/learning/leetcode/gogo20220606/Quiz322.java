package indi.simon.learning.leetcode.gogo20220606;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 牛逼牛逼，可以可以，注意一下第一次提交的超时问题就行了，那就是每一次状态转移没必要从dp[0]开始遍历，从本阶段往回倒最大硬币面额的地方开始遍历即可，因为再往前的话，一枚硬币已经解决不了问题了
public class Quiz322 {

    public static void main(String[] args) {
        Quiz322 quiz322 = new Quiz322();
        int[] coins = new int[]{5, 306, 188, 467, 494};
        int res = quiz322.coinChange(coins, 7047);
        System.out.println(res);
    }


    public int coinChange(int[] coins, int amount) {
        Set<Integer> coinSet = new HashSet<>();
        int maxVal = 0;
        for (int i = 0; i < coins.length; i++) {
            coinSet.add(coins[i]);
            maxVal = Math.max(maxVal, coins[i]);
        }

        //dp数组下标i元素表示使用coins中的硬币组成i面额可以使用的最少硬币个数
        int[] dp = new int[amount + 1];

        for (int i = 1; i <= amount; i++) {
            int minCount = Integer.MAX_VALUE;
            //遍历之前所有状态来计算本次状态
            for (int j = Math.max(0, i - maxVal); j < i; j++) {
                if (coinSet.contains(i - j) && dp[j] != -1) {
                    minCount = Math.min(minCount, dp[j] + 1);
                }
            }
            if (minCount == Integer.MAX_VALUE) {
                dp[i] = -1;
            } else {
                dp[i] = minCount;
            }
        }

        return dp[amount];

    }


    /**
     * 硬币不可重复使用版本
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangeNoDuplicate(int[] coins, int amount) {

        Integer[][] dp = new Integer[coins.length][amount + 1];

        dp[0][coins[0]] = 1;
        dp[0][0] = 0;

        for (int i = 1; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j >= coins[i]) {
                    //价值超过了coins[i]，考虑转移和继承的最小值
                    if (dp[i - 1][j] != null && dp[i - 1][j - coins[i]] != null) {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - coins[i]] + 1);
                    } else if (dp[i - 1][j] == null && dp[i - 1][j - coins[i]] != null) {
                        dp[i][j] = dp[i - 1][j - coins[i]] + 1;
                    } else if (dp[i - 1][j - coins[i]] == null && dp[i - 1][j] != null) {
                        dp[i][j] = dp[i - 1][j];
                    }
                } else {
                    //价值没有超过coins[i]，仅考虑继承
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[coins.length - 1][amount] == null ? -1 : dp[coins.length - 1][amount];
    }
}
