package indi.simon.learning.leetcode.两次遍历;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
//todo: 只能记住这种做法
// 1：从后往前找到第一个严格正序对 nums[i]<nums[i+1]
// 2：将nums[i]与[i+1,n-1]之中的ceiling交换，此时有可能这个ceiling就是nums[i+1]
// 3：将(i+1,n-1]正序排序，由于题目要求空间复杂度是O1，而(i+1,n-1]之前已经证明是全逆序的，
//    且将nums[i]与ceiling交换也不影响(i+1,n-1]的逆序关系，所以此处可以无脑对称swap各个元素
public class Quiz31数组字典序下一个排列 {

    public static void main(String[] args) {
        int[] test = new int[]{1, 2, 5, 4, 6, 3, 2, 1};
        Quiz31数组字典序下一个排列 quiz31Reviewed = new Quiz31数组字典序下一个排列();
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
