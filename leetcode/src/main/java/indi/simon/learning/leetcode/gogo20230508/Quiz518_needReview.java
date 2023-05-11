package indi.simon.learning.leetcode.gogo20230508;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz518_needReview {

    public static void main(String[] args) {
        Quiz518_needReview quiz518NeedReview = new Quiz518_needReview();
        int res = quiz518NeedReview.change(5, new int[]{1, 2, 5});
        System.out.println(res);
    }

    public int change(int amount, int[] coins) {
        //dp[i]代表凑够i块钱有多少种凑法
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        Set<Integer> coinSet = new HashSet<>();
        for (int coin : coins) {
            coinSet.add(coin);
        }

        for (int i = 1; i <= amount; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (coinSet.contains(i - j)) {
                    dp[i] += dp[j];
                }
            }
        }
        return dp[amount];
    }





    public int changeOfficial(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

}
