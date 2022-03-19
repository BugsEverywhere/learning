package indi.simon.learning.leetcode.gogo20220314;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz35 {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 6};
        Quiz35 quiz35 = new Quiz35();
        int res = quiz35.searchInsert(arr, 7);
        System.out.println(res);
    }

    public int searchInsert(int[] nums, int target) {
        if (nums.length == 1) {
            if (nums[0] >= target) {
                return 0;
            } else if (nums[0] < target) {
                return 1;
            }
        }

        int i = 0;
        int j = nums.length - 1;

        while (i < j) {
            int midIndex = (i + j) / 2;
            if (target == nums[midIndex]) {
                return midIndex;
            } else if (target < nums[midIndex]) {
                j = midIndex - 1;
            } else if (target > nums[midIndex]) {
                i = midIndex + 1;
            }
        }

        if (nums[i] == target) {
            return i;
            //todo: 第一次提交失败是此处忘记考虑最终i==j时，找到的nums[i]就是target的情况，也就是target刚好是左边界或者右边界
            // 同时，标准的二分法一定是while (i < j) 而不是while(i <= j)，后者在跳出循环时会有i或者j越界的边界情况，如果循环后有对i和j的操作，就很不方便
        } else if (nums[i] > target) {
            return i;
        } else {
            return i + 1;
        }

    }
}
