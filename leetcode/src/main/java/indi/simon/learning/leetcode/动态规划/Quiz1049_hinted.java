package indi.simon.learning.leetcode.动态规划;

/**
 * Created by Chen Zhuo on 2024/2/7.
 */
//todo: 难的不是背包问题，难的是想到这事一个背包问题，需要想到这个问题是将一堆石头[a,b,c,d,e,f,g]分成a+b-c+d+g-f......，
// 这样就相当于把这堆石头分成了两堆，加起来的一堆，减去的一堆，然后就是看怎么让这两堆的差值最小，假设最终的两堆有一堆较小
// 为smaller，较大一堆为bigger，那么就是求bigger-smaller的最小值，也就是total-2*smaller的最小值
public class Quiz1049_hinted {

    public static void main(String[] args) {
        Quiz1049_hinted quiz1049Hinted = new Quiz1049_hinted();
        int res = quiz1049Hinted.lastStoneWeightII(new int[]{2, 7, 4, 1, 8, 1});
        System.out.println(res);
    }

    //我依葫芦画瓢的DP
    public int lastStoneWeightII(int[] stones) {

        int totalWeight = 0;
        for (int i = 0; i < stones.length; i++) {
            totalWeight += stones[i];
        }

        //dp[i][j] represents if we can reach weight j
        // under the condition that we pick less than or equals to first i stones
        //todo: 这里注意背包问题的列数，要是target加一，因为第0列总是表示什么都不选，weight（或者value）是0的情况
        int n = totalWeight / 2 + 1;
        boolean[][] dp = new boolean[stones.length][n];
        dp[0][0] = true;
        if (stones[0] < dp[0].length) {
            dp[0][stones[0]] = true;
        }

        for (int i = 1; i < stones.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                //状态继承，不挑选stone[i]的情况
                dp[i][j] = dp[i - 1][j];
                //状态转移，挑选stone[i]的情况
                if (j - stones[i] >= 0) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - stones[i]];
                }
            }
        }

        int maxWeGot = 0;
        for (int j = n - 1; j >= 0; j--) {
            if (dp[stones.length - 1][j]) {
                maxWeGot = j;
                break;
            }
        }

        return totalWeight - 2 * maxWeGot;
    }

    //官方的DP
    public int lastStoneWeightIIOfficial(int[] stones) {
        int sum = 0;
        for (int weight : stones) {
            sum += weight;
        }
        int n = stones.length, m = sum / 2;
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= m; ++j) {
                if (j < stones[i]) {
                    dp[i + 1][j] = dp[i][j];
                } else {
                    dp[i + 1][j] = dp[i][j] || dp[i][j - stones[i]];
                }
            }
        }
        for (int j = m;; --j) {
            if (dp[n][j]) {
                return sum - 2 * j;
            }
        }
    }

}
