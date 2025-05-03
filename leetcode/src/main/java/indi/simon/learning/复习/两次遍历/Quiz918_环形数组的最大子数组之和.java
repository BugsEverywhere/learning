package indi.simon.learning.复习.两次遍历;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz918_环形数组的最大子数组之和 {

    public static void main(String[] args) {

    }

    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        //记录截止当前元素的最大连续子数组之和，其实就是dp[i]
        int preAcc = nums[0];
        //当前最大连续元素和
        int res = nums[0];

        //初始化res，找出正常数组中的最大子数组和，记录在res，这和Quiz53的dp没啥区别
        for (int i = 1; i < n; i++) {
            preAcc = Math.max(preAcc + nums[i], nums[i]);
            res = Math.max(res, preAcc);
        }

        //下面开始两次遍历，找出环形数组中，首尾相连的情况下的最大和

        //前缀和
        int leftSum = nums[0];
        //用来记录[0,i]区间内的最大前缀和
        int[] leftMaxSum = new int[n];
        leftMaxSum[0] = nums[0];

        for (int i = 1; i < n; i++) {
            leftSum += nums[i];
            leftMaxSum[i] = Math.max(leftMaxSum[i - 1], leftSum);
        }

        //后缀和，同时考虑[0 ~ i-1]的最大前缀和，更新到最终结果
        int rightSum = 0;
        for (int i = n - 1; i > 0; i--) {
            rightSum += nums[i];
            res = Math.max(res, rightSum + leftMaxSum[i - 1]);
        }
        return res;
    }


}
