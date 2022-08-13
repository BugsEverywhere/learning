package indi.simon.learning.leetcode.gogo20220808;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz509 {

    public static void main(String[] args) {
        Quiz509 quiz509 = new Quiz509();
        int res = quiz509.fib(4);
        System.out.println(res);
    }

    public int fib(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        return dp[n];
    }

}
