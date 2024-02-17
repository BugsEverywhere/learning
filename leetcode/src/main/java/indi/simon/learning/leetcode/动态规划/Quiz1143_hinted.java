package indi.simon.learning.leetcode.动态规划;

/**
 * Created by Chen Zhuo on 2024/2/17.
 */
//todo: 二维dp需要注意状态转移的方式，dp[i][j]不一定是从dp[i-1][j]以及dp[i][j-1]转移而来，
// 也有可能是从dp[i-1][j-1]转移而来
public class Quiz1143_hinted {

    public static void main(String[] args) {
        Quiz1143_hinted quiz1143Hinted = new Quiz1143_hinted();
        int res = quiz1143Hinted.longestCommonSubsequence("bsbininm", "jmjkbkjkv");
        System.out.println(res);
    }

    public int longestCommonSubsequence(String text1, String text2) {
        //dp[i][j] means the length of common subsequence of text1[0~i] and text2[0~j]
        int[][] dp = new int[text1.length()][text2.length()];

        //init the first row
        for (int j = 0; j < text2.length(); j++) {
            if (text1.charAt(0) == text2.charAt(j) || (j >= 1 && dp[0][j - 1] == 1)) {
                dp[0][j] = 1;
            }
        }

        //init the first col
        for (int i = 0; i < text1.length(); i++) {
            if (text2.charAt(0) == text1.charAt(i) || (i >= 1 && dp[i - 1][0] == 1)) {
                dp[i][0] = 1;
            }
        }

        //status transfer
        for (int i = 1; i < text1.length(); i++) {
            for (int j = 1; j < text2.length(); j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    //相等的情况下需要从text1[0 ~ i-1]以及text2[0 ~ j-1]转移而来，
                    // 这样就确保转移前的状态，也就是dp[i-1,j-1]，不包含text1[i]以及text2[j]，
                    // 相当于text1[i]以及text2[j]是作为双方过往相等的子串的新元素
                    // 如果是从dp[i-1,j]或者dp[i,j-1]亦或者两者的min, max什么的转移而来，则不能保证这一点
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[text1.length() - 1][text2.length() - 1];
    }


}
