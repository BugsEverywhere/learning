package indi.simon.learning.复习.贪心;

import java.util.Arrays;

public class Quiz280 {

    public static void main(String[] args) {

    }

    /**
     * 整个方法只用考虑i和i+1
     *
     * 1. 从 0 开始，到 nums.length - 2，迭代遍历 nums 中的每个元素，因为最后一个元素没有下一个可以交换的元素。
     * 2. 检查是否 i 是偶数且 nums[i] > nums[i + 1]。 如果是，交换 nums[i] 和 nums[i + 1]。
     * 3. 检查是否 i 是奇数且 nums[i] < nums[i + 1]。 如果是，交换 nums[i] 和 nums[i + 1]。
     *
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (((i % 2 == 0) && nums[i] > nums[i + 1])
                    || ((i % 2 == 1) && nums[i] < nums[i + 1])) {
                swap(nums, i, i + 1);
            }
        }
    }


    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
