package indi.simon.learning.leetcode.gogo20240101;

/**
 * Created by Chen Zhuo on 2024/1/7.
 */
//todo: 值得回顾的一题
public class Quiz2735 {

    public static void main(String[] args) {
        Quiz2735 quiz2735 = new Quiz2735();
        long res = quiz2735.minCost(new int[]{1, 2, 3}, 4);
        System.out.println(res);
    }

        public long minCost(int[] nums, int x) {
            //dp[i][j] 表示nums通过成本x移动i次之后，收集类型j的巧克力迄今为止所需的最小成本
            int[][] dp = new int[nums.length][nums.length];

            long firstRound = 0;
            for (int i = 0; i < nums.length; i++) {
                dp[0][i] = nums[i];
                firstRound += dp[0][i];
            }
            //todo: 需要注意初始值的设定，不能忽略第一行的情况
            long minCost = firstRound;

            for (int i = 1; i < nums.length; i++) {
                //移动一位，则整体成本加一次x即可，相当于移动该次之后，取每一个巧克力的初始成本
                long thisRoundCost = (long) x * i;
                for (int j = 0; j < nums.length; j++) {
                    //模拟移动，按照题意，如果话费成本x改变一次所有巧克力的类型（往前移动一位），
                    // 相当于将原有的成本数组往后移动一位，即巧克力不动，成本数组动，
                    // 此时，成本数组移动i位之后取原来第j位巧克力的成本，
                    // 就是nums中往后移动了i位对应位置的值，当然要考虑越界之后从头开始
                    int newCostForThisChocolate = j - i < 0 ? j - i + nums.length : j - i;
                    dp[i][j] = Math.min(dp[i - 1][j], nums[newCostForThisChocolate]);
                    thisRoundCost += dp[i][j];
                }
                minCost = Math.min(thisRoundCost, minCost);
            }

            return minCost;
        }

}
