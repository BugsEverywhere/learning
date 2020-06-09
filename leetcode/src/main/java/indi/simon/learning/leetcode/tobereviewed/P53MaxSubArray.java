package indi.simon.learning.leetcode.tobereviewed;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P53MaxSubArray {

    public static void main(String[] args) {
        int[] testArr = new int[]{-2,-1,-3,-4,-1,-2,-1,-5,-4};
        int max = maxSubArray(testArr);
        System.out.println(max);
    }

    private static int maxSum = Integer.MIN_VALUE;

    public static int maxSubArray(int[] nums) {
        maxSubArrayInternal(nums, 0, 0);
        return maxSum;
    }

    private static void maxSubArrayInternal(int[] nums, int sumSoFar, int i) {
        sumSoFar = sumSoFar + nums[i];
        if (sumSoFar > maxSum) {
            maxSum = sumSoFar;
        }
        if (i >= nums.length - 1) {
            return;
        }
        if (sumSoFar <= 0) {
            //i之前的和小于零，那么之前的和就没有意义，往下递归的时候就无视之前的和即可
            maxSubArrayInternal(nums, 0, i + 1);
        } else {
            //i之前的和大于零才有意义，才需要往下递归
            maxSubArrayInternal(nums, sumSoFar, i + 1);
        }
    }

}

//todo: 思路：递归回溯！