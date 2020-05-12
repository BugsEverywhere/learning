package indi.simon.learning.leetcode.gogo2020april.week20to26;

import java.util.Arrays;

public class P26 {

    public static void main(String[] args) {
        int[] testArr = new int[]{0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(testArr));
        System.out.println(Arrays.toString(testArr));
    }

    public static int removeDuplicates(int[] nums) {

        int lastVal = 0;
        int lastPos = 0;
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int i = 0;
        while (i < nums.length) {
            if (i == 0) {
                i++;
                lastVal = nums[0];
                continue;
            }
            if (nums[i] != lastVal) {
                lastVal = nums[i];
                swap(lastPos + 1, i, nums);
                lastPos = lastPos + 1;
            }
            i++;
        }

        return lastPos + 1;
    }

    private static void swap(int i, int j, int[] arr) {
        if (i == j) {
            return;
        }
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


}
