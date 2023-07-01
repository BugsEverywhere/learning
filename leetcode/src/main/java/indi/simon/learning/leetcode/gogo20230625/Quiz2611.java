package indi.simon.learning.leetcode.gogo20230625;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 有点意思，有点博弈的意思在里面，贪心贪在老鼠1相较于老鼠2得分更多的量，而不是老鼠1自己的得分绝对值
public class Quiz2611 {

    public static void main(String[] args) {
        Quiz2611 quiz2611 = new Quiz2611();
        int res = quiz2611.miceAndCheese(new int[]{1, 4, 4, 6, 4}, new int[]{6, 5, 3, 6, 1}, 1);
        System.out.println(res);
    }


    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        //reward[i][0]代表reward1[i]，reward[i][1]代表reward2[i]
        int[][] reward = new int[reward1.length][2];
        for (int i = 0; i < reward1.length; i++) {
            reward[i][0] = reward1[i];
            reward[i][1] = reward2[i];
        }

        //按老鼠1相较于老鼠2得分更多的量降序
        Arrays.sort(reward, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[0] - o2[1], o1[0] - o1[1]);
            }
        });

        int sum = 0;
        int mice1Cnt = 0;
        for (int i = 0; i < reward.length; i++) {
            if (mice1Cnt < k) {
                sum += reward[i][0];
                mice1Cnt++;
            } else {
                sum += reward[i][1];
            }
        }

        return sum;
    }


    //todo: 超时的DP，不过好歹逻辑对了
    public int miceAndCheeseDP(int[] reward1, int[] reward2, int k) {
        if (k == 0) {
            int sum = 0;
            for (int num : reward2) {
                sum += num;
            }
            return sum;
        }

        //dp[i][j]代表截止第i块奶酪，第一只老鼠吃掉其中j个，整体所能得到的最大分数
        int[][] dp = new int[reward1.length][k + 1];
        dp[0][0] = reward2[0];
        dp[0][1] = reward1[0];

        for (int i = 1; i < reward1.length; i++) {
            for (int j = 0; j < k + 1; j++) {
                //到第i个奶酪时（前i+1个），第一只老鼠吃掉了其中的j个
                if (j > i + 1) {
                    break;
                }
                if (j == i + 1) {
                    dp[i][j] = dp[i - 1][j - 1] + reward1[i];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + reward2[i];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j] + reward2[i], dp[i - 1][j - 1] + reward1[i]);
                }
            }
        }

        return dp[reward1.length - 1][k];
    }

}
