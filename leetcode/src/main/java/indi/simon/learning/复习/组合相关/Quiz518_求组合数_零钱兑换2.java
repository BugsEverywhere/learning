package indi.simon.learning.复习.组合相关;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 区分排列与组合的DP
public class Quiz518_求组合数_零钱兑换2 {

    public static void main(String[] args) {
        Quiz518_求组合数_零钱兑换2 quiz518NeedReview = new Quiz518_求组合数_零钱兑换2();
        int res = quiz518NeedReview.change(5, new int[]{1, 2, 5});
        System.out.println(res);
    }

    public int change(int amount, int[] coins) {
        //dp[i] 代表凑出总金额i的组合数
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        //todo: 求组合数，[2,2,4]与[4,2,2]只能算一个，因此要先遍历硬币，再遍历金额，区别于377
        for (int coin : coins) {
            for (int i = 1; i <= amount; i++) {
                if (i - coin >= 0) {
                    dp[i] += dp[i - coin];
                }
            }
        }
        return dp[amount];
    }

}
