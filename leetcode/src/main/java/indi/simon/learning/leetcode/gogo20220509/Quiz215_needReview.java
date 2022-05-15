package indi.simon.learning.leetcode.gogo20220509;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz215_needReview {

    public static void main(String[] args) {

    }

    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

}
