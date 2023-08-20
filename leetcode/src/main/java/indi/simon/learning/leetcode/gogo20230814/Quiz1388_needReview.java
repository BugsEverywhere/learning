package indi.simon.learning.leetcode.gogo20230814;

/**
 * Created by Chen Zhuo on 2023/8/20.
 */
public class Quiz1388_needReview {

    public static void main(String[] args) {

    }

    public int maxSizeSlices(int[] slices) {
        //round表示可以挑选的披萨的块数
        int round = slices.length / 3;
        //dp[i][j]表示从第0块开始挑，挑选到第i块时，已经挑选了j块的情况的最大值
        int[][] dp = new int[slices.length][slices.length + 1];
        dp[0][1] = slices[0];
        dp[1][1] = slices[1];
        for (int i = 2; i < slices.length; i++) {
            for (int j = i - 2; j >= 0; j--) {
                for (int k = 1; k < slices.length; k++) {
                    dp[i][k] = Math.max(dp[j][k - 1], dp[i][k]);
                }
            }
        }


        return -1;
    }

}
