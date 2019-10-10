package indi.simon.leetcode;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P75SortColors {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 0, 2, 1, 1, 0};
        sortColors(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void sortColors(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            if (nums[i] == 1) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] == 0) {
                        swap(nums, i, j);
                        break;
                    }
                }
            }
            if (nums[i] == 2) {
                boolean alreadSwap = false;
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] == 0) {
                        swap(nums, i, j);
                        alreadSwap = true;
                        break;
                    }
                }
                if (alreadSwap) {
                    continue;
                }
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] == 1) {
                        swap(nums, i, j);
                        break;
                    }
                }
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}


//todo: 思路：双指针