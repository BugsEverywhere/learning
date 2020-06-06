package indi.simon.learning.leetcode.tobereviewed;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P10RegularExpression_NotFinish {

    public static void main(String[] args) {

    }

    public boolean isMatch(String text, String pattern) {
        //递归终止条件，pattern和text都减为空字符串了，则返回true匹配成功。
        if (pattern.isEmpty()) {
            return text.isEmpty();
        }
        boolean firstMatch = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            //有*的情况，题目条件是*如果出现，则必然出现在第二位，且不会连续出现
            //或运算符的左边按照不考虑pattern的前两位{x}*，去递归后面的。或运算符的右边按照第一位匹配中，并考虑第二个*去递归
            return (isMatch(text, pattern.substring(2)) ||
                    (firstMatch && isMatch(text.substring(1), pattern)));
        } else {
            //没有*的一般情况递归，一位一位去比较
            return firstMatch && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    //===========================================================================================================================================

    enum Result {
        TRUE, FALSE
    }

    private static Result[][] memo;


    public static boolean isMatchDp(String text, String pattern) {
        memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }

    //自顶向下
    public static boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }
        boolean ans;
        //递归终止，i和j已经同时到了各自的末端
        if (j == pattern.length()) {
            ans = i == text.length();
        } else {
            boolean firstMatch = (i < text.length() &&
                    (pattern.charAt(j) == text.charAt(i) ||
                            pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                ans = (dp(i, j + 2, text, pattern) ||
                        firstMatch && dp(i + 1, j, text, pattern));
            } else {
                ans = firstMatch && dp(i + 1, j + 1, text, pattern);
            }
        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }



    //自底向上，从字符串的末尾开始匹配
    public boolean isMatchDpButtom2Up(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--) {
            for (int j = pattern.length() - 1; j >= 0; j--) {
                boolean firstMatch = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || firstMatch && dp[i + 1][j];
                } else {
                    dp[i][j] = firstMatch && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }


}
