package indi.simon.learning.复习.动态规划;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class Quiz53_最大子数组和 {

    public static void main(String[] args) {
        int[] testArr = new int[]{-2, -1, -3, -4, -1, -2, -1, -5, -4};
        int max = maxSubArray(testArr);
        System.out.println(max);
    }

    //dp解法
    public static int maxSubArray(int[] nums) {
        int n = nums.length;
        //记录到当前元素为止，最大的连续子数组之和，其实就是dp[i]，dp[0]=nums[0]
        int preAcc = nums[0];
        //整体结果
        int res = nums[0];

        //找出正常下标顺序下的最大子数组和，记录在res
        for (int i = 1; i < n; i++) {
            //状态转移，dp[i-1]到dp[i]的转移
            preAcc = Math.max(preAcc + nums[i], nums[i]);
            res = Math.max(res, preAcc);
        }

        return res;
    }

}