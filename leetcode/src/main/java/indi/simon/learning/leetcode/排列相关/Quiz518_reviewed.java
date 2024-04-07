package indi.simon.learning.leetcode.排列相关;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 区分排列与组合的DP
public class Quiz518_reviewed {

    public static void main(String[] args) {
        Quiz518_reviewed quiz518NeedReview = new Quiz518_reviewed();
        int res = quiz518NeedReview.change(5, new int[]{1, 2, 5});
        System.out.println(res);
    }

    //todo: 错误的解法！统计出来的是排列数，也就是对于凑够3块钱，1+2和2+1考虑成了两种情况
    public int changeWrong(int amount, int[] coins) {
        //dp[i]代表凑够i块钱有多少种凑法
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        //todo: 更别说还用了额外的存储，直接在下面的循环里面遍历coins数组不就得了，还搞个Set存起来干嘛
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

    //todo: 正确的解法，统计的组合数，硬币的顺序我们不关心，硬币有没有被用到，用了几个，是我们关心的，
    // 因此外层循环要遍历硬币，里层循环遍历金额，这样就能保证
    public int change(int amount, int[] coins) {
        //dp[i]表示数额i可以用coins凑出的不同组合数
        int[] dp = new int[amount + 1];
        //0块钱不需要任何硬币就能凑出，组合数为1
        dp[0] = 1;
        for (int coin : coins) {
            for (int k = coin; k <= amount; k++) {
                dp[k] += dp[k - coin];
            }
        }

        return dp[amount];
    }

}
