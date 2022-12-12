package indi.simon.learning.leetcode.gogo20221121;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz808_needReview {

    public static void main(String[] args) {
        Quiz808_needReview quiz808NeedReview = new Quiz808_needReview();
        double res = quiz808NeedReview.soupServings(0);
        System.out.println(res);
    }

    //todo: 官方记忆化搜索
    private double[][] memo;
    public double soupServingsOfficialMemSearch(int n) {
        n = (int) Math.ceil((double) n / 25);
        if (n >= 179) {
            return 1.0;
        }
        memo = new double[n + 1][n + 1];
        return dfs(n, n);
    }
    public double dfs(int a, int b) {
        if (a <= 0 && b <= 0) {
            return 0.5;
        } else if (a <= 0) {
            return 1;
        } else if (b <= 0) {
            return 0;
        }
        if (memo[a][b] == 0) {
            memo[a][b] = 0.25 * (dfs(a - 4, b) + dfs(a - 3, b - 1) + dfs(a - 2, b - 2) + dfs(a - 1, b - 3));
        }
        return memo[a][b];
    }

    //todo:官方DP
    public double soupServingsOfficialDp(int n) {
        n = (int) Math.ceil((double) n / 25);
        if (n >= 179) {
            return 1.0;
        }
        double[][] dp = new double[n + 1][n + 1];
        dp[0][0] = 0.5;
        for (int i = 1; i <= n; i++) {
            dp[0][i] = 1.0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[Math.max(0, i - 4)][j] + dp[Math.max(0, i - 3)][Math.max(0, j - 1)] + dp[Math.max(0, i - 2)][Math.max(0, j - 2)] + dp[Math.max(0, i - 1)][Math.max(0, j - 3)]) / 4.0;
            }
        }
        return dp[n][n];
    }


    //todo: 超时，第一次提交超内存，焯！
    public double soupServings(int n) {
        return soupServingsInternal(n, n, 1);
    }

    private double soupServingsInternal(int leftA, int leftB, int round) {
        double res = 0.0;

        //计算本轮第一种方式对概率的贡献
        if (leftA > 100 && leftB > 0) {
            res += soupServingsInternal(leftA - 100, leftB, round + 1);
        } else if (leftA <= 100 && leftB > 0) {
            res += Math.pow(0.25, round);
        } else if (leftA <= 100 && leftB <= 0) {
            //一起用完
            res += Math.pow(0.25, round) / 2;
        }

        //计算本轮第二种方式对概率的贡献
        if (leftA > 75 && leftB > 25) {
            res += soupServingsInternal(leftA - 75, leftB - 25, round + 1);
        } else if (leftA <= 75 && leftB > 25) {
            //A先用完
            res += Math.pow(0.25, round);
        } else if (leftA <= 75 && leftB <= 25) {
            //一起用完
            res += Math.pow(0.25, round) / 2;
        }

        //计算本轮第三种方式对概率的贡献
        if (leftA > 50 && leftB > 50) {
            res += soupServingsInternal(leftA - 50, leftB - 50, round + 1);
        } else if (leftA <= 50 && leftB > 50) {
            //A先用完
            res += Math.pow(0.25, round);
        } else if (leftA <= 50 && leftB <= 50) {
            //一起用完
            res += Math.pow(0.25, round) / 2;
        }

        //计算本轮第四种方式对概率的贡献
        if (leftA > 25 && leftB > 75) {
            res += soupServingsInternal(leftA - 25, leftB - 75, round + 1);
        } else if (leftA <= 25 && leftB > 75) {
            //A先用完
            res += Math.pow(0.25, round);
        } else if (leftA <= 25 && leftB <= 75) {
            //一起用完
            res += Math.pow(0.25, round) / 2;
        }

        return res;
    }


}
