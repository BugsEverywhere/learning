package indi.simon.learning.复习.动态规划;


/**
 * @author chenzhuo(zhiyue)
 */
// todo: 如果dp是多维的，比如此题中的二维dp数组，一定要注意其初始状态是怎样，后续往哪个方向推进，有的时候初始状态是0行0列，
//  那么后续就是从左上角往右下角推进，进行状态转移。此题中是对角线是初始状态，且定义的i<=j，那么注定了是在对角线右上方做文章，
//  那么状态转移推进的方向肯定是i从大往小，j从小往大
public class Quiz516_最长回文子序列长度 {

    public static void main(String[] args) {
        Quiz516_最长回文子序列长度 quiz516 = new Quiz516_最长回文子序列长度();
        int res = quiz516.longestPalindromeSubseq("bbbab");
        System.out.println(res);
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for(int i = 0; i < n;i++){
            dp[i][i] = 1;
        }

        for(int i = n-1;i >= 0;i--){
            for(int j = i + 1; j < n;j++){
                if(s.charAt(i) == s.charAt(j)){
                    //如果i和j字符相同，需要转移
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    //如果i和j字符不相同，则直接继承之前的大者
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }

        }
        return dp[0][n-1];
    }

}
