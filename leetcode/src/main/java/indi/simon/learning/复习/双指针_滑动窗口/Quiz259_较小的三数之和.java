package indi.simon.learning.复习.双指针_滑动窗口;

import java.util.Arrays;

public class Quiz259_较小的三数之和 {

    /**
     * 给定一个长度为 n 的整数数组和一个目标值 target ，寻找能够使条件 nums[i] + nums[j] + nums[k] < target 成立的三元组  i, j, k 个数（0 <= i < j < k < n）。
     *
     *
     *
     * 示例 1：
     *
     * 输入: nums = [-2,0,1,3], target = 2
     * 输出: 2
     * 解释: 因为一共有两个三元组满足累加和小于 2:
     *      [-2,0,1]
     *      [-2,0,3]
     * 示例 2：
     *
     * 输入: nums = [], target = 0
     * 输出: 0
     * 示例 3：
     *
     * 输入: nums = [0], target = 0
     * 输出: 0
     *
     *
     * 提示:
     *
     * n == nums.length
     * 0 <= n <= 3500
     * -100 <= nums[i] <= 100
     * -100 <= target <= 100
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 1, -1, -1};
        Quiz259_较小的三数之和 quiz = new Quiz259_较小的三数之和();
        int res = quiz.threeSumSmaller(nums, -1);
        System.out.println(res);
    }

    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int i = 0;
        int res = 0;
        while (i < n) {
            //todo: 注意这里不需要加速，因为题给的nums中有负数，所以不要多事儿搞加速
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum < target) {
                    res += k - j;
                    j++;
                } else {
                    k--;
                }
            }
            i++;
        }
        return res;
    }


}
