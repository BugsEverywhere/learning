package indi.simon.learning.leetcode.gogo20230116;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz162 {

    public static void main(String[] args) {

    }

    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i == 0 && nums[i] > nums[i + 1]) {
                return 0;
            }
            if (i == nums.length - 1 && nums[i] > nums[i - 1]) {
                return nums.length - 1;
            }
            if ((i - 1 >= 0 && nums[i] > nums[i - 1]) && (i + 1 < nums.length && nums[i] > nums[i + 1])) {
                return i;
            }
        }

        return -1;
    }

}
