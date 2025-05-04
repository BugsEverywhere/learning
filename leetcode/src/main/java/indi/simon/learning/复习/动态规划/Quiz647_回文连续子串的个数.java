package indi.simon.learning.复习.动态规划;

/**
 * Created by Chen Zhuo on 2024/2/24.
 */
public class Quiz647_回文连续子串的个数 {

    public static void main(String[] args) {

    }

    public int countSubstrings(String s) {
        int n = s.length();
        //dp[i][j] = 1 表示 s[i~j] is 回文子串
        int[][] dp = new int[n][n];

        //对角线初始化
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        int cnt = n;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && i + 1 <= j - 1 && dp[i + 1][j - 1] == 1) {
                    //状态继承
                    dp[i][j] = 1;
                } else if (i + 1 == j) {
                    //状态转移
                    dp[i][j] = (s.charAt(i) == s.charAt(j) ? 1 : 0);
                }
                //顺便记个数
                cnt += dp[i][j];
            }
        }

        return cnt;
    }
}
