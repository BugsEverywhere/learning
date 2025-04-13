package indi.simon.learning.复习.组合相关;

import java.util.Arrays;

/**
 * Created by Chen Zhuo on 2025/4/13.
 */
public class Quiz322_求组合数_零钱兑换 {

    public static void main(String[] args) {
        Quiz322_求组合数_零钱兑换 quiz322_求组合数_零钱兑换 = new Quiz322_求组合数_零钱兑换();
        int[] coins = new int[]{2};
        int res = quiz322_求组合数_零钱兑换.coinChange(coins, 3);
        System.out.println(res);
    }

    //todo: 注意的点：
    // 1. dp数组初始化的时候，初始化为 amount+1 即可，不用初始化为Integer.MAX，因为后者会在状态转移过程中溢出，导致出现负数
    // 2. 审题，如果没有被转移过则要返回负一
    public int coinChange(int[] coins, int amount) {
        //dp[i]表示凑出金额i的最少硬币个数
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;


        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i] >= 0) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        if (dp[amount] > amount) {
            return -1;
        }

        return dp[amount];

    }

}
