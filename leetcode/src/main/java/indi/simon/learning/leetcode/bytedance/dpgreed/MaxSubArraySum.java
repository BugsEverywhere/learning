package indi.simon.learning.leetcode.bytedance.dpgreed;

public class MaxSubArraySum {

    public static void main(String[] args) {

        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int res = maxSubArrayInternal(nums);
        System.out.println(res);
    }

    private static int maxSubArrayInternal(int[] nums) {
        int sum = nums[0];
        int preSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (preSum > 0) {
                preSum += nums[i];
            } else {
                preSum = nums[i];
            }
            if (sum < preSum) {
                sum = preSum;
            }
        }
        return sum;
    }

}
