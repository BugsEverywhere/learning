package indi.simon.learning.leetcode.gogo2020may.week11to17;

public class P283 {

    public static void main(String[] args) {

    }

    private static void moveZeroes(int[] nums) {
        int[] arr = new int[nums.length];
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                arr[j] = nums[i];
                j++;
            }
        }
        System.arraycopy(arr, 0, nums, 0, nums.length);
    }


}
