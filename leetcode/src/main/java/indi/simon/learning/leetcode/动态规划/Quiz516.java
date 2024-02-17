package indi.simon.learning.leetcode.动态规划;


/**
 * @author chenzhuo(zhiyue)
 */
//todo: 虽然想出来了，但是还是有几点需要注意：
// 1. 如果dp是多维的，比如此题中的二维dp数组，一定要注意其初始状态是怎样，后续往哪个方向推进，有的时候初始状态是0行0列，
// 那么后续就是从左上角往右下角推进，进行状态转移。此题中是对角线是初始状态，那么推进的方向就是从对角线往右上角，
// 所以一定注意for循环遍历时推进方向不能出错。
// 2. 多维dp的状态转移，dp[i][j]从推进方向之前的状态转移而来，可能出现从不同方向转移时的转移方式并不相同，比如此题中，
// dp[i][j]从dp[i+1][j]和dp[i][j-1]以及dp[i+1][j-1]三个方向转移来时，是不同的转移方式。须细品。
public class Quiz516 {

    public static void main(String[] args) {
        Quiz516 quiz516Notfinish = new Quiz516();
        int res = quiz516Notfinish.longestPalindromeSubseq("bbbab");
        System.out.println(res);
    }

    public int longestPalindromeSubseq(String s) {

        //dp[i][j] means the longest target subsequence between s[i ~ j]
        int[][] dp = new int[s.length()][s.length()];

        for (int k = 0; k < s.length(); k++) {
            dp[k][k] = 1;
        }

        //status transfer
        for (int k = 1; k < s.length(); k++) {
            for (int i = 0; i < s.length(); i++) {
                int j = i + k;

                if (i + 1 < s.length() && j < s.length()) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j]);
                }

                if (j - 1 >= i && j < s.length()) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }

                if (j < s.length()) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1]);
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                    }
                }
            }
        }

        return dp[0][s.length() - 1];
    }

}
