package indi.simon.learning.leetcode.gogo20230515;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1335 {

    public static void main(String[] args) {
        Quiz1335 quiz1335 = new Quiz1335();
        int res = quiz1335.minDifficulty(new int[]{11, 111, 22, 222, 33, 333, 44, 444}, 6);
        System.out.println(res);
    }

    public int minDifficulty(int[] jobDifficulty, int d) {
        if (d > jobDifficulty.length) {
            return -1;
        }
        //dp[i][j]代表第i天完成到jobDifficulty的下标j时的最小难度总和
        int[][] dp = new int[d + 1][jobDifficulty.length];
        //第一天如果完成到i，那么当天的难度应该是前i项的最大值，因此填充完第一天的所有可能难度
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < jobDifficulty.length; i++) {
            max = Math.max(max, jobDifficulty[i]);
            dp[1][i] = max;
        }

        for (int i = 2; i <= d; i++) {
            for (int j = 0; j < jobDifficulty.length; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                //记录从第k+1项任务到j项任务难度最大的一个，也就是第i天从k+1个任务做到第j个任务结束，难度应该是多大
                int maxK2J = jobDifficulty[j];
                //k表示前一天完成到jobDifficulty[k]
                //todo:这里要注意k遍历到i-2即可，比如说第2天肯定不能从jobDifficulty[0]开始做起，肯定至少也是从jobDifficulty[1]开始做起，
                // 此时看的状态应该是第一天的jobDifficulty[0]，所以当i==2时，前一天的jobDifficulty只需要往前遍历到jobDifficulty[0]即可，就是i-2
                for (int k = j - 1; k >= i - 2; k--) {
                    maxK2J = Math.max(maxK2J, jobDifficulty[k + 1]);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + maxK2J);
                }
            }
        }

        return dp[d][jobDifficulty.length - 1];
    }

}
