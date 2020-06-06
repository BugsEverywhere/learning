package indi.simon.learning.leetcode.tobereviewed;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P5LongestPalindromeSubString {

    public static void main(String[] args) {

        String test = longestPalindromeDp("babad");
        System.out.println(test);

    }

    public static String longestPalindrome(String s) {
        if (s.length() == 0) {
            return s;
        }
        char[] charArr = s.toCharArray();
        String longestSubString = s.substring(0, 1);
        for (int i = 0; i < charArr.length; i++) {
            if (i > 0 && i + 1 < charArr.length && charArr[i - 1] == charArr[i + 1]) {
                //判断charArr[i]是回文的中间字符
                String longestThisRound;
                int j = 1;
                while (i - j >= 0 && i + j < charArr.length && charArr[i - j] == charArr[i + j]) {
                    j++;
                }
                j--;
                longestThisRound = s.substring(i - j, i + j + 1);
                if (longestThisRound.length() > longestSubString.length()) {
                    longestSubString = longestThisRound;
                }
            }
            if (i + 1 < charArr.length && charArr[i] == charArr[i + 1]) {
                //判断charArr[i]与charArr[i+1]是回文的中间字符
                String longestThisRound;
                int j = 1;
                while (i - j >= 0 && i + j + 1 < charArr.length && charArr[i - j] == charArr[i + j + 1]) {
                    j++;
                }
                j--;
                longestThisRound = s.substring(i - j, i + j + 2);
                if (longestThisRound.length() > longestSubString.length()) {
                    longestSubString = longestThisRound;
                }
            }
        }
        return longestSubString;
    }


    public static String longestPalindromeDp(String s) {
        int n = s.length();
        char[] charArr = s.toCharArray();
        String res = "";
        boolean[][] dp = new boolean[n][n];
        //第一层，从低到高遍历所有长度，必须从低到高
        for (int length = 1; length <= n; length++) {
            //第二层，遍历所有字符，取之为开始字符，j尾字符
            for (int i = 0; i + length - 1 < n; i++) {
                int j = i + length - 1;
                if (length == 1) {
                    dp[i][j] = true;
                } else if (length == 2) {
                    if (charArr[i] == charArr[j]) {
                        dp[i][j] = true;
                    }
                } else {
                    if (dp[i + 1][j - 1] && charArr[i] == charArr[j]) {
                        dp[i][j] = true;
                    }
                }
                //每一轮判断一下
                if (dp[i][j] && length > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }

        return res;
    }

}

//TODO: 思路是分两种情况来遍历整个字符串，当前字符是回文串正中心，以及当前字符和下一个字符加一起是回文串
// 正中心，需要注意的就是边界条件，处理好下标不要数组越界

//todo: 解法二是DP