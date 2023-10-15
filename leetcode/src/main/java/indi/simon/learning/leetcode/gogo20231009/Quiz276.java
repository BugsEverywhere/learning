package indi.simon.learning.leetcode.gogo20231009;

/**
 * Created by Chen Zhuo on 2023/10/14.
 */
public class Quiz276 {

    public static void main(String[] args) {
        Quiz276 quiz276 = new Quiz276();
        int res = quiz276.numWaysV2(7, 2);
        System.out.println(res);
    }

    public int numWaysV2(int n, int k) {
        if (n == 1) {
            return k;
        }
        //dp[i]表示第i个栅栏的涂色方案数
        int[] dp = new int[n];

        dp[0] = k;
        dp[1] = k * k;

        for (int i = 2; i < n; i++) {
            //很简单明了的状态转移，dp[i]第一部分是i与i-1不同色的状态数，第二部分是i与i-1同色，但与i-2不同色的状态数
            dp[i] = dp[i-1] * (k-1) + dp[i-2] * 1 * (k-1);
        }

        return dp[n-1];
    }


    //todo: 自己的DP，虽然AC了，但是复杂度太高，原因是状态数组不应该构建为2维的，1维足够了
    // 如果是面试肯定不会通过，这里留下这个解法只是为了提醒，当遇到不能连色时的处理，下面注释了
    public int numWays(int n, int k) {
        //dp[i][j]代表第i个栅栏涂第j种涂料的方案数
        int[][] dp = new int[n][k];
        for (int j = 0; j < k; j++) {
            dp[0][j] = 1;
        }
        if (n == 1) {
            return k;
        }
        for (int j = 0; j < k; j++) {
            dp[1][j] = k;
        }

        for (int i = 2; i < n; i++) {
            for (int j = 0; j < k; j++) {
                for (int m = 0; m < k; m++) {
                    if (m != j) {
                        dp[i][j] += dp[i - 1][m];
                    } else {
                        //todo: 当i-1与i同色的情况下，相当于此时i-1的栅栏的方案数就固定为1了，
                        // 此时状态转移需要考虑从i-2转移而来
                        for (int p = 0; p < k; p++) {
                            if (p == m) {
                                continue;
                            }
                            dp[i][j] += dp[i - 2][p];
                        }
                    }
                }
            }
        }

        int sum = 0;
        for (int j = 0; j < k; j++) {
            sum += dp[n - 1][j];
        }
        return sum;
    }

}
