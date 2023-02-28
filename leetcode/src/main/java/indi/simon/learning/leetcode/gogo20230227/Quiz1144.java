package indi.simon.learning.leetcode.gogo20230227;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1144 {

    public static void main(String[] args) {

    }

    public int movesToMakeZigzag(int[] nums) {

        int oddCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                continue;
            }
            int leftGap = 0;
            if (nums[i - 1] <= nums[i]) {
                leftGap = nums[i] - nums[i - 1] + 1;
            }
            int rightGap = 0;
            if (i + 1 < nums.length && nums[i + 1] <= nums[i]) {
                rightGap = nums[i] - nums[i + 1] + 1;
            }
            oddCount += Math.max(leftGap, rightGap);
        }

        int evenCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 != 0) {
                continue;
            }
            int leftGap = 0;
            if (i - 1 >= 0 && nums[i - 1] <= nums[i]) {
                leftGap = nums[i] - nums[i - 1] + 1;
            }
            int rightGap = 0;
            if (i + 1 < nums.length && nums[i + 1] <= nums[i]) {
                rightGap = nums[i] - nums[i + 1] + 1;
            }
            evenCount += Math.max(leftGap, rightGap);
        }


        return Math.min(oddCount, evenCount);
    }

}
