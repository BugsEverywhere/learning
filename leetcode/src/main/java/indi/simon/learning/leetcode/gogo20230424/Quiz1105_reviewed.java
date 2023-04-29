package indi.simon.learning.leetcode.gogo20230424;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 永远要记住，递归是穷举，时间复杂度远远高于n方，能用递归的地方一定要思考怎么才能转为dp，dp好歹是n方，要么三次方要么2次方，总归比穷举要好
public class Quiz1105_reviewed {

    public static void main(String[] args) {
        Quiz1105_reviewed quiz1105Reviewed = new Quiz1105_reviewed();
        int res = quiz1105Reviewed.minHeightShelves(new int[][]{{1, 3}, {2, 4}, {3, 2}}, 6);
        System.out.println(res);
    }

    //todo : 官方DP
    public int minHeightShelvesOfficial(int[][] books, int shelfWidth) {
        int n = books.length;
        //dp[i]代表摆放第i本书之前，书架的最小高度
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1000000);
        dp[0] = 0;
        for (int i = 0; i < n; ++i) {
            int maxHeight = 0, curWidth = 0;
            //往前倒，遍历之前所有可能跟第i本书放在一层的组合
            for (int j = i; j >= 0; --j) {
                curWidth += books[j][0];
                if (curWidth > shelfWidth) {
                    break;
                }
                maxHeight = Math.max(maxHeight, books[j][1]);
                //状态转移，拿本层最大高度，从dp[j]转移而来，并且取最小转移值
                dp[i + 1] = Math.min(dp[i + 1], dp[j] + maxHeight);
            }
        }
        return dp[n];
    }


    //纯纯的递归，超时
    public int minHeightShelves(int[][] books, int shelfWidth) {
        return minHeightShelvesInternal(0, 0, books, shelfWidth);
    }

    private int minHeightShelvesInternal(int currHeight, int bookIndex, int[][] books, int shelfWidth) {
        if (bookIndex == books.length) {
            return currHeight;
        }
        //记录本层的最大高度
        int maxHeight = Integer.MIN_VALUE;
        int widthSum = 0;
        int minHeight = Integer.MAX_VALUE;
        for (int i = bookIndex; i < books.length; i++) {
            widthSum += books[i][0];
            maxHeight = Math.max(maxHeight, books[i][1]);
            if (widthSum <= shelfWidth) {
                //往下递归此刻新起一层
                minHeight = Math.min(minHeightShelvesInternal(currHeight + maxHeight, i + 1, books, shelfWidth), minHeight);
            }
        }
        return minHeight;
    }


}
