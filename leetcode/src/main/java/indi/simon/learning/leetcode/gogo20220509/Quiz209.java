package indi.simon.learning.leetcode.gogo20220509;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz209 {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 30, 1, 2, 4, 3};
        Quiz209 quiz209 = new Quiz209();
        int res = quiz209.minSubArrayLen(40, nums);
        System.out.println(res);
    }

    private int minLength = Integer.MAX_VALUE;

    public int minSubArrayLen(int target, int[] nums) {

        int left = 0;
        int right = 0;

        int sum = nums[0];
        while (right < nums.length) {
            if (sum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                if (left == right) {
                    return minLength;
                }
                sum = sum - nums[left];
                left++;
            } else {
                right++;
                if (right < nums.length) {
                    sum = sum + nums[right];
                }
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
