package indi.simon.learning.leetcode.动态规划;

/**
 * Created by Chen Zhuo on 2024/2/3.
 */
//todo: 动态规划得转移很精妙，还有初始化时的哨兵
public class Quiz72_reviewed {

    public static void main(String[] args) {
        Quiz72_reviewed quiz72Hinted = new Quiz72_reviewed();
        int res = quiz72Hinted.minDistance("", "a");
        System.out.println(res);
    }


    public int minDistanceDp(String word1, String word2) {

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
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //dp[i - 1][j]即删除word1本字符，删除完之后，只需要word1[0~i-1] -> word2[0~j]的步数就能得到word1[0~i]->word2[0~j]，
                    //dp[i][j- 1]即在word1此处插入新字符，基于word1[0~i] -> word2[0~j-1]，插入一个字符就得到了word1[0~i]->word2[0~j],
                    //dp[i-1][j-1]即平替，原来word1[0~i-1]->word2[0~j-1]，需要多少步，那么平替i->j，就能得到word1[0~i]->word2[0~j]
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        return dp[n1][n2];
    }


    //=========================================================超时的递归
    private int[][] mem;

    public int minDistance(String word1, String word2) {
        if (word1.equals(word2)) {
            return 0;
        }
        if (word1.length() == 0) {
            return word2.length();
        }
        if (word2.length() == 0) {
            return word1.length();
        }
        mem = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < mem.length; i++) {
            for (int j = 0; j < mem[0].length; j++) {
                mem[i][j] = Integer.MAX_VALUE;
            }
        }
        return minDisInternal(word1, word2, 0, 0, 0);
    }

    private int minDisInternal(String word1, String word2, int i, int j, int currDis) {
        if (i == word1.length() && j == word2.length()) {
            mem[i][j] = currDis;
            return currDis;
        }

        if (mem[i][j] < currDis) {
            return mem[i][j];
        }

        int res = Integer.MAX_VALUE;

        if (i < word1.length() && j < word2.length() && word1.charAt(i) == word2.charAt(j)) {
            //不作操作，比较双方下一个字符
            res = Math.min(res, minDisInternal(word1, word2, i + 1, j + 1, currDis));
        }

        //删除word1的当前word2字符
        if (i + 1 <= word1.length()) {
            res = Math.min(res, minDisInternal(word1, word2, i + 1, j, currDis + 1));
        }

        //word1中插入当前word2字符
        if (j + 1 <= word2.length()) {
            res = Math.min(res, minDisInternal(word1, word2, i, j + 1, currDis + 1));
        }

        //修改word1当前字符为word2当前字符
        if (i + 1 <= word1.length() && j + 1 <= word2.length()) {
            res = Math.min(res, minDisInternal(word1, word2, i + 1, j + 1, currDis + 1));
        }

        mem[i][j] = Math.min(res, mem[i][j]);

        return res;
    }

}
