package indi.simon.learning.leetcode.gogo20220718;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz494_needReview {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 1, 1};
        Quiz494_needReview quiz494NeedReview = new Quiz494_needReview();
        int res = quiz494NeedReview.findTargetSumWays(nums, 3);
        System.out.println(res);
    }

    //DP解法=============================================================================





    //自己的递归解法=======================================================================

    private int count = 0;

    public int findTargetSumWays(int[] nums, int target) {
        findTargetSumWaysInternal(nums, 0, 0, target);
        return count;
    }

    private void findTargetSumWaysInternal(int[] nums, int index, int sum, int target) {
        if (index == nums.length) {
            if (sum == target) {
                count++;
            }
            return;
        }

        //正号递归
        findTargetSumWaysInternal(nums, index + 1, sum + nums[index], target);

        //负号递归
        findTargetSumWaysInternal(nums, index + 1, sum - nums[index], target);

    }


}
