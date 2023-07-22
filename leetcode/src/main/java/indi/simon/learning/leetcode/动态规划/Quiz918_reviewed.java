package indi.simon.learning.leetcode.动态规划;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz918_reviewed {

    public static void main(String[] args) {

    }

    public int maxSubarraySumCircularOfficial(int[] nums) {
        int n = nums.length;
        //用来记录[0,i]区间内的最大连续子数组和，后续有用
        int[] leftMax = new int[n];
        leftMax[0] = nums[0];
        //从左往右遍历，记录下遍历的和
        int leftSum = nums[0];
        //代表在处理nums[i]时，前面的数组元素能对他做的贡献，如果pre是正的，那么就将pre与nums[i]相加，作用于下一个nums[i+1]，
        // 如果是负的，那么就舍弃，因为无论如何对nums[i]是副作用，对num[i+1]仅保留nums[i]
        int preAcc = nums[0];
        //当前最大连续元素和
        int res = nums[0];
        //先从左往右遍历，做两件事情：
        // 1.找出正常下标顺序下的最大子数组和，记录在res，这和Quiz53没啥区别
        // 2.记录下从0到每一个i的前缀区间内的最大前缀和，注意一定是最大前缀和，比如[4,-2,-1,5,6]的最大前缀和是[4,4,4,6,12]，这主要是为了在下一步从右往左遍历时，将每一个后缀和与当前最大前缀和相加来看是否构成答案
        for (int i = 1; i < n; i++) {
            preAcc = Math.max(preAcc + nums[i], nums[i]);
            res = Math.max(res, preAcc);
            leftSum += nums[i];
            leftMax[i] = Math.max(leftMax[i - 1], leftSum);
        }

        // 从右到左，处理首尾相连的子数组的情况，
        int rightSum = 0;
        for (int i = n - 1; i > 0; i--) {
            rightSum += nums[i];
            res = Math.max(res, rightSum + leftMax[i - 1]);
        }
        return res;
    }


}
