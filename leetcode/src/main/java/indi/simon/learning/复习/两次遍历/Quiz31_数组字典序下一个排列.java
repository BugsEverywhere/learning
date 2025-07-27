package indi.simon.learning.复习.两次遍历;

import java.util.Arrays;

/**
 * 整数数组的一个 排列 就是将其所有成员以序列或线性顺序排列。
 *
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 *
 * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
 * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
 * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 *
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 *
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 *
 */
//todo: 只能记住这种做法
// 1：从后往前找到第一个严格正序对 nums[i]<nums[i+1]
// 2：将nums[i]与[i+1,n-1]之中刚好比自己大的数交换，假设这个数是nums[k]，i < k < n
// 3：将(i+1,n-1]正序排序，由于题目要求空间复杂度是O1，而(i+1,n-1]之前已经证明是全逆序的，
//    且将nums[i]与nums[k]交换也不影响数组(i+1,n-1]的逆序关系，所以此处可以无脑对称swap各个元素
public class Quiz31_数组字典序下一个排列 {

    public static void main(String[] args) {
        int[] test = new int[]{1, 2, 5, 4, 6, 3, 2, 1};
        Quiz31_数组字典序下一个排列 quiz31Reviewed = new Quiz31_数组字典序下一个排列();
        quiz31Reviewed.nextPermutation(test);
        System.out.println(Arrays.toString(test));
    }

    public void nextPermutation(int[] nums) {
        //从后往前找到第一个正序对
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        //如果成功找到了这个正序对，假设一个小数一个大数，需要再从后往前找到第一个比小数大的数，将其与小数交换
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= i && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        //交换后再对原小数位置之后的元素按正序排序，由于之前从后往前遍历到i+1为止，都是逆序的，所以可以无脑对称swap i+1以后的元素
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

}
