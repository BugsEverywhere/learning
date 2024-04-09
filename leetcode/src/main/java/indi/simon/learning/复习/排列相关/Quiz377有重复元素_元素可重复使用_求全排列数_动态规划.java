package indi.simon.learning.复习.排列相关;

/**
 * @author chenzhuo(zhiyue)
 */

//todo: 没啥好总结的，几下就行，想到了就是很简单的DP
public class Quiz377有重复元素_元素可重复使用_求全排列数_动态规划 {

    public static void main(String[] args) {
        Quiz377有重复元素_元素可重复使用_求全排列数_动态规划 quiz377Notfinish = new Quiz377有重复元素_元素可重复使用_求全排列数_动态规划();
        int[] nums = new int[]{1, 2, 3};
        int res = quiz377Notfinish.combinationSum4(nums, 4);
        System.out.println(res);
    }

    public int combinationSum4(int[] nums, int target) {
        //dp[i] means the total count of arranges till i, using elements of nums
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i - num < 0) {
                    continue;
                }
                dp[i] += dp[i - num];
            }
        }

        return dp[target];
    }
}
