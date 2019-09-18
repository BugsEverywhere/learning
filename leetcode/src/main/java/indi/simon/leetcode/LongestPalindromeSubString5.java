package main.java.indi.simon.leetcode;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class LongestPalindromeSubString5 {

    public static void main(String[] args) {

        String test = longestPalindrome("aaaa");
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
}
