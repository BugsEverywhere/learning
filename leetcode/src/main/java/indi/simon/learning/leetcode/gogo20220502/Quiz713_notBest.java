package indi.simon.learning.leetcode.gogo20220502;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz713_notBest {

    public static void main(String[] args) {
        Quiz713_notBest quiz713 = new Quiz713_notBest();
        int[] nums = new int[]{1,2,3};
        int res = quiz713.numSubarrayProductLessThanK(nums, 0);
        System.out.println(res);
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int totalCount = 0;
        for (int i = 0; i < nums.length; i++) {
            int j = i;
            int timesRes = nums[j];
            while (timesRes < k) {
                totalCount++;
                j++;
                if (j >= nums.length) {
                    break;
                }
                timesRes = timesRes * nums[j];
            }
        }
        return totalCount;
    }
}
