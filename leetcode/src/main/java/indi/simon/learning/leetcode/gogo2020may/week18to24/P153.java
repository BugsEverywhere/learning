package indi.simon.learning.leetcode.gogo2020may.week18to24;

public class P153 {

    public static void main(String[] args) {

    }

    private static int findMin(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
            } else {
                if (nums[i] < nums[i - 1]) {
                    return nums[i];
                }
            }
        }
        return nums[0];
    }


}
