package indi.simon.learning.leetcode.动态规划;

/**
 * Created by Chen Zhuo on 2023/11/7.
 */
public class Quiz1746_reviewed {

    public static void main(String[] args) {
        Quiz1746_reviewed quiz1746Reviewed = new Quiz1746_reviewed();
        int res = quiz1746Reviewed.maxSumAfterOperation(new int[]{-5, -4, 1, 9});
        System.out.println(res);
    }

    public int maxSumAfterOperation(int[] nums) {
        //从头开始遍历数组
        // 用于记录将某个数平方后的从头到现在最大和
        int maxSumWithPow = 0;
        // 用于记录原始的从头到现在的最大值，也就是不将任何数进行平方
        int realMaxSum = 0;
        // 用于记录最终结果
        int max = 0;
        //开始遍历
        for (int i = 0; i < nums.length; i++) {
            //maxSumWithPow只可能从以往的maxSumWithPow与nums[i]相加，以及以往的realMaxSum与nums[i]平方相加两种状态转移而来
            maxSumWithPow = Math.max(maxSumWithPow + nums[i], realMaxSum + nums[i] * nums[i]);
            // realMaxSum只可能是从以往的realMaxSum加上nums[i]转移而来
            // 因为是记录真实的最大子数组和，那么最不济就是之前的子数组和最大仍然是负值，
            // 那么就一个数都不加进来呗，断臂求生，此时和为0，因为最终的结果肯定是一个非负数
            // 这样能保证该realMaxSum能真实记录到i以前的子数组最大值的情况，妙啊妙
            realMaxSum = Math.max(nums[i] + realMaxSum, 0);
            max = Math.max(maxSumWithPow, max);
        }
        return max;
    }


}
