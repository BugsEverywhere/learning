package indi.simon.learning.leetcode.gogo20220718;


/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz516_notfinish {

    public static void main(String[] args) {
        Quiz516_notfinish quiz516Notfinish = new Quiz516_notfinish();
        int res = quiz516Notfinish.longestPalindromeSubseq("bbbab");
        System.out.println(res);
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            char c1 = s.charAt(i);
            for (int j = i + 1; j < n; j++) {
                char c2 = s.charAt(j);
                if (c1 == c2) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
