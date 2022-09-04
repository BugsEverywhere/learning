package indi.simon.learning.leetcode.gogo20220829;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 第一次提交忘了排序，默认pairs从低到高。第二次提交忘了maxLength默认应该是1，第三次失败属于dp没有初始化为全1，艹
public class Quiz646 {

    public static void main(String[] args) {
        int[][] arr = new int[][]{{-6, 9}, {1, 6}, {8, 10}, {-1, 4}, {-6, -2}, {-9, 8}, {-5, 3}, {0, 3}};
        Quiz646 quiz646 = new Quiz646();
        int res = quiz646.findLongestChain(arr);
        System.out.println(res);
    }

    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(o -> o[0]));

        //dp[i]代表到pairs[i]的最长数对长度
        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);

        int maxLength = 1;
        for (int i = 1; i < pairs.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

}
