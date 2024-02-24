package indi.simon.learning.leetcode.动态规划;

/**
 * Created by Chen Zhuo on 2024/2/24.
 */
public class Quiz647 {

    public static void main(String[] args) {

    }

    public int countSubstrings(String s) {
        //dp[i][j] = 1 represents s[i~j] is a palindromic string
        int[][] dp = new int[s.length()][s.length()];

        //init
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }

        //status transfer
        for (int k = 1; k < s.length(); k++) {
            for (int i = 0; i < s.length() - 1; i++) {
                int j = i + k;
                if (j >= s.length()) {
                    continue;
                }
                if (s.charAt(i) == s.charAt(j) && (dp[i + 1][j - 1] == 1 || i + 1 > j - 1)) {
                    dp[i][j] = 1;
                }
            }
        }

        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                res += dp[i][j];
            }
        }

        return res;
    }
}
