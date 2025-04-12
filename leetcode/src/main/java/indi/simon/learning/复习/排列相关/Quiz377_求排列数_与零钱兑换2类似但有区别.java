package indi.simon.learning.复习.排列相关;

/**
 * @author chenzhuo(zhiyue)
 */

public class Quiz377_求排列数_与零钱兑换2类似但有区别 {

    public static void main(String[] args) {
        Quiz377_求排列数_与零钱兑换2类似但有区别 quiz377Notfinish = new Quiz377_求排列数_与零钱兑换2类似但有区别();
        int[] nums = new int[]{1, 2, 3};
        int res = quiz377Notfinish.combinationSum4(nums, 4);
        System.out.println(res);
    }

    public int combinationSum4(int[] nums, int target) {
        //dp[i] means the total count of arranges till i, using elements of nums
        int[] dp = new int[target + 1];
        dp[0] = 1;

        //todo: 求排列数，[2,2,4]与[4,2,2]算两个，因此要先遍历目标target，再遍历元素
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
