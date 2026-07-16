package indi.simon.learning.复习.动态规划.回文;

/**
 * 给你一个字符串 s，找到 s 中最长的 回文 子串。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 * <p>
 * 输入：s = "cbbd"
 * 输出："bb"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 */
public class Quiz5_最长回文子串 {


    public String longestPalindrome(String s) {
        // 特殊用例判断
        int n = s.length();
        if (n < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        // dp[i][j] 表示 s[i, j] 是否是回文串
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && i + 1 <= j - 1 && dp[i + 1][j - 1]) {
                    //i和j不紧邻，那么他们构成一个回文串的前提是i+1与j-1也是回文串且i和j相等
                    dp[i][j] = true;
                } else if (i + 1 == j) {
                    //i和j紧邻，那么他们相等的话就构成一个长度为2的回文串
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                }

                // 更新记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }


}
