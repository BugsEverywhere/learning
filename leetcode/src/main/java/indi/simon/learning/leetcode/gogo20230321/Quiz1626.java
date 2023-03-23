package indi.simon.learning.leetcode.gogo20230321;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1626 {

    public static void main(String[] args) {
        Quiz1626 quiz1626 = new Quiz1626();
        int res = quiz1626.bestTeamScore(new int[]{1, 3, 7, 3, 2, 4, 10, 7, 5}, new int[]{4, 5, 2, 1, 1, 2, 4, 1, 4});
        System.out.println(res);
    }


    public int bestTeamScore(int[] scores, int[] ages) {

        if (scores.length == 1) {
            return scores[0];
        }

        int[][] ageScoreArr = new int[ages.length][2];
        for (int i = 0; i < ages.length; i++) {
            ageScoreArr[i][0] = ages[i];
            ageScoreArr[i][1] = scores[i];
        }

        //todo: 这里要注意，双排数组按照下表0排序的同时还要按照下表1排序，这样就能确保在后续dp状态转移的时候，i能完全吃掉j，
        // 也就是dp[i]=dp[j]+ageScoreArr[1]是合理的，因为只有这样，比j年龄小(或相等)的队员，分数肯定也都比j小(或相等)，向下兼容
        Arrays.sort(ageScoreArr, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return Integer.compare(o1[0], o2[0]);
            } else {
                return Integer.compare(o1[1], o2[1]);
            }
        });


        //dp[i]代表年龄为ages[i]的队员选入的话能得到的最大分数
        int[] dp = new int[ages.length];
        dp[0] = ageScoreArr[0][1];
        int maxScore = Integer.MIN_VALUE;
        for (int i = 1; i < ages.length; i++) {
            dp[i] = ageScoreArr[i][1];
            for (int j = i - 1; j >= 0; j--) {
                //i的年龄要么大于j的，要么等于j的
                if (ageScoreArr[i][0] > ageScoreArr[j][0] && ageScoreArr[i][1] >= ageScoreArr[j][1]) {
                    //i比j大且i的得分大于等于j的，j和i可以同队
                    dp[i] = Math.max(dp[i], dp[j] + ageScoreArr[i][1]);
                } else if (ageScoreArr[i][0] == ageScoreArr[j][0]) {
                    //i和j同龄
                    dp[i] = Math.max(dp[i], dp[j] + ageScoreArr[i][1]);
                }
            }
            maxScore = Math.max(maxScore, dp[i]);
        }

        return maxScore;
    }


}
