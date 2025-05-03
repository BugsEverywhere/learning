package indi.simon.learning.复习.动态规划;

/**
 * Created by Chen Zhuo on 2024/2/3.
 */
//todo: 动态规划得转移很精妙，还有初始化时的哨兵
public class Quiz72_字符串word1变到word2 {

    public static void main(String[] args) {
        Quiz72_字符串word1变到word2 quiz72Hinted = new Quiz72_字符串word1变到word2();
        int res = quiz72Hinted.minDistance("", "a");
        System.out.println(res);
    }


    public int minDistance(String word1, String word2) {

        int n1 = word1.length();
        int n2 = word2.length();

        //dp[i][j]表示从word1从0到i的子串，转换到word2从0到j的子串，所需最小步数
        int[][] dp = new int[n1 + 1][n2 + 1];

        //  初始化，注意，这里安排了哨兵
        // 第0行表示word1的虚无，什么都没有，即从word1的""转换到word2的[""]、[0~0]、[0~1]、[0~2]......
        // 第0列表示word2的虚无，什么都没有，即从word1的[""]、[0~0]、[0~1]、[0~2].....转换到word2的""
        // 第一行
        for (int j = 1; j <= n2; j++) {
            dp[0][j] = dp[0][j - 1] + 1;
        }
        // 第一列
        for (int i = 1; i <= n1; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }


        //状态转移
        for (int i = 1; i < n1 + 1; i++) {
            for (int j = 1; j < n2 + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    //如果word1的第i个字符跟word2的第j个在字符相同，则不用做任何变化，dp[i][j]状态可继承自dp[i-1][j-1]
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //这里就是状态转移，dp[i][j]可以从dp[i-1][j]，dp[i][j-1]，dp[i-1][j-1]三种状态转移而来，对应可以进行的三种操作
                    //1. dp[i-1][j]+1对应删除字符操作，因为dp[i-1][j]代表word1的[0,i-1]子串变到word2[0,j]子串的最小步数，那么word1的[0,i]子串只需要删除一个word1[i]，就能变到word2[0,j]了
                    //2. dp[i][j-1]+1对应增加字符操作,同理不赘述
                    //3. dp[i-1][j-1]对应字符替换操作，同理不赘述
                    //三种前一个状态取最小值来转移即可
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        return dp[n1][n2];
    }

}
