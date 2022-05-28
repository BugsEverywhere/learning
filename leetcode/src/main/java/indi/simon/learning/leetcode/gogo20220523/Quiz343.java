package indi.simon.learning.leetcode.gogo20220523;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 把一个数拆成两个数i, j，这两个数各自有自己的最大和乘积dp[i]和dp[j]，那么一共四个数，需要判断i*dp[j], dp[i]*dp[j], dp[i]*j, i*j 四种情况最大值，然后遍历下一个i,j
public class Quiz343 {

    public static void main(String[] args) {
        Quiz343 quiz343 = new Quiz343();
        int res = quiz343.integerBreak(10);
        System.out.println(res);
    }

    public int integerBreak(int n) {

        int[] dp = new int[n + 1];

        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            int max = 0;
            for (int j = 1; j <= i; j++) {
                int val1 = j * (i - j);
                int val2 = j * dp[i - j];
                int val3 = dp[j] * (i - j);
                int val4 = dp[j] * dp[i - j];
                max = Math.max(max, Math.max(val4, Math.max(val3, Math.max(val1, val2))));
            }
            dp[i] = max;
        }

        return dp[n];
    }

}
