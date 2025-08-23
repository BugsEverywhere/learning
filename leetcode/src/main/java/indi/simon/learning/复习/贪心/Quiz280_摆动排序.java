package indi.simon.learning.复习.贪心;

/**
 * 给你一个的整数数组 nums, 将该数组重新排序后使 nums[0] <= nums[1] >= nums[2] <= nums[3]...
 *
 * 输入数组总是有一个有效的答案。
 *
 *
 *
 * 示例 1:
 *
 * 输入：nums = [3,5,2,1,6,4]
 * 输出：[3,5,1,6,2,4]
 * 解释：[1,6,2,5,3,4]也是有效的答案
 * 示例 2:
 *
 * 输入：nums = [6,6,5,6,3,8]
 * 输出：[6,6,5,6,3,8]
 */
public class Quiz280_摆动排序 {

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
