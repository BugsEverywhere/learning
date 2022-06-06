package indi.simon.learning.leetcode.gogo20220530;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 典型的0-1背包问题，给定对某种性质的限制，遍历的元素包含这种性质（比如背包问题中的重量，这里的0或者1的个数），
// 然后问你最多能放几个所遍历的元素（个数最值问题），或者某个特性，比如重量或者价值，最大能达到多少（特性最值问题）
// 1. 对于个数最值问题，dp数组为整数数组，维度为元素维度1+限制维度个数，拿本题举例，那么就是1+2=3个维度，所以要定义一个三维int数组，
//    其中dp[i][j][k]代表前i个元素中，在j个限制1与k个限制2的情况下，所能添加的最大元素个数。
// 2. 对于特性最值问题，分两种情况：
//         a. 所需要知道最值的特性就是限制维度本身，比如经典背包问题，限制重量不超过w，求能装入的最大总重量totalWeight.
//         b. 所需要知道最值的特性不是限制维度，比如升级版背包问题，限制重量不超过w，求能装入的最大总价值totalValue.
//    对于问题a，比较简单，dp数组就是一个二维布尔类型即可，其中dp[i][j]表示装到第i个元素时，是否有可能装入j的重量，有就为true，没有为false
//    对于问题b，则需要将dp数组定义为一个整数数组，其维度为元素维度1+限制维度个数。以本题为例，假如所需要求的不是最大子集个数，而是元素相加最大和，那么就同样需要定义
//    一个三维整数数组，因为一个元素维度+两个限制维度。那么此时dp[i][j][k]就代表，当装入第i个元素，并且在j个限制1与k个限制2的情况时，所能达到的最大特性值
// 综上，构造完dp数组后，再基于该数组做状态继承和状态转移即可
public class Quiz474 {

    public static void main(String[] args) {
        String[] arr = new String[]{"10", "0", "1"};
        Quiz474 quiz474 = new Quiz474();
        int res = quiz474.findMaxForm(arr, 1, 1);
        System.out.println(res);
    }


    public int findMaxForm(String[] strs, int m, int n) {

        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];

        for (int i = 1; i <= strs.length; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    int zeroCount = 0;
                    int oneCount = 0;
                    for (int index = 0; index < strs[i - 1].length(); index++) {
                        if (strs[i - 1].charAt(index) == '0') {
                            zeroCount++;
                        } else {
                            oneCount++;
                        }
                    }
                    if (j < zeroCount || k < oneCount) {
                        //加上本i字符串的0和1就超了，因此只能不加，状态继承
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        //加上本i字符串，状态转移
                        int inherit = dp[i - 1][j][k];
                        int transfer = dp[i - 1][j - zeroCount][k - oneCount] + 1;
                        int origin = dp[i][j][k];
                        dp[i][j][k] = Math.max(Math.max(inherit, transfer), origin);
                    }

                }
            }
        }

        return dp[strs.length][m][n];
    }


}
