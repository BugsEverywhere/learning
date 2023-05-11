package indi.simon.learning.leetcode.gogo20230508;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz650 {

    public static void main(String[] args) {
        Quiz650 quiz650 = new Quiz650();
        int res = quiz650.minSteps(8);
        System.out.println(res);
    }

    public int minSteps(int n) {
        //dp[i][0]代表得到i个字符所需的最小操作次数，dp[i][1]代表最后一次操作时，剪贴板中的字符个数
        int[][] dp = new int[n + 1][2];
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i][0] = Integer.MAX_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j][1] == i - j) {
                    //直接再paste一次就能到达i
                    if (dp[i][0] > dp[j][0] + 1) {
                        dp[i][0] = dp[j][0] + 1;
                        dp[i][1] = dp[j][1];
                    }
                } else if (j == i - j) {
                    //需要先复制，再粘贴，因此要两步操作
                    if (dp[i][0] > dp[j][0] + 2) {
                        dp[i][0] = dp[j][0] + 2;
                        dp[i][1] = j;
                    }
                } else {
                    //todo: 要注意这种情况别漏掉
                    //需要粘贴多步
                    if (dp[j][1] != 0 && (i - j) % dp[j][1] == 0) {
                        if (dp[j][1] != 0 && dp[i][0] > dp[j][0] + (i - j) / dp[j][1]) {
                            dp[i][0] = dp[j][0] + (i - j) / dp[j][1];
                            dp[i][1] = dp[j][1];
                        }
                    }
                }
            }
        }

        return dp[n][0];
    }

}
