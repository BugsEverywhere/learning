package indi.simon.learning.复习.动态规划;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class Quiz32_最长有效括号字符串长度 {

    public static void main(String[] args) {
        Quiz32_最长有效括号字符串长度 quiz32 = new Quiz32_最长有效括号字符串长度();
        int count = quiz32.longestValidParentheses("()(())");
        System.out.println(count);
    }

    public int longestValidParentheses(String s) {
        int maxans = 0;
        //todo: dp[i]表示以i结尾的最长有效括号长度
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            //todo: 进考虑i是右括号的情况
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    //todo: 如果前一个是左括号，则i-1与i构成一个合法的括号对，那么从i-2处状态转移，前提是
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
                } else if (i - 1 - dp[i - 1] >= 0 && s.charAt(i - 1 - dp[i - 1]) == '(') {
                    //todo: 如果i-1也是右括号，那么dp[i-1]存储的是i-1结尾的最长有效括号字符串长度，可能是0也可能是一个正数x，
                    // 那么往前跳过x个字符，判断i-1往前倒第x个字符是否是左括号，如果是，那么i和i-1-dp[i-1] 构成一个合法括号对，
                    // 那么就是从 i-1-dp[i-1]-1 的位置状态转移。那么就要判断 i-1-dp[i-1]-1 是否>=0
                    dp[i] = dp[i - 1] + ((i - 1 - dp[i - 1] - 1) >= 0 ? dp[i - 1 - dp[i - 1] - 1] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

}
