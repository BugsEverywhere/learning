package indi.simon.learning.leetcode.gogo20230807;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz581 {

    public static void main(String[] args) {
        Quiz581 quiz581 = new Quiz581();
        int res = quiz581.findUnsortedSubarray(new int[]{1, 2, 3, 4});
        System.out.println(res);
    }

    public int findUnsortedSubarray(int[] nums) {

        int[] arrCopy = new int[nums.length];
        System.arraycopy(nums, 0, arrCopy, 0, nums.length);
        Arrays.sort(nums);
        int diffMin = Integer.MAX_VALUE;
        int diffMax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != arrCopy[i]) {
                diffMin = Math.min(i, diffMin);
                diffMax = Math.max(i, diffMax);
            }
        }

        if (diffMin == Integer.MAX_VALUE) {
            return 0;
        }
        return diffMax - diffMin + 1;
    }


}
