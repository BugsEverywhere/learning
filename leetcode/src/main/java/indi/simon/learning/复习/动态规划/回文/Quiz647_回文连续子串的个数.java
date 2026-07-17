package indi.simon.learning.复习.动态规划.回文;

/**
 * Created by Chen Zhuo on 2024/2/24.
 */
public class Quiz647_回文连续子串的个数 {

    public static void main(String[] args) {

    }

    public int countSubstrings(String s) {
        int n = s.length();
        //dp[i][j] = 1 表示 s[i~j] is 回文子串
        boolean[][] dp = new boolean[n][n];

        //对角线初始化
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        int cnt = n;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && i + 1 < j && dp[i + 1][j - 1]) {
                    //i和j不紧邻，那么他们构成一个回文串的前提是i+1与j-1也是回文串且i和j相等
                    dp[i][j] = true;
                } else if (i + 1 == j && s.charAt(i) == s.charAt(j)) {
                    //i和j紧邻，那么他们相等的话就构成一个长度为2的回文串
                    dp[i][j] = true;
                }
                //顺便记个数
                cnt += dp[i][j] ? 1 : 0;
            }
        }

        return cnt;
    }
}
