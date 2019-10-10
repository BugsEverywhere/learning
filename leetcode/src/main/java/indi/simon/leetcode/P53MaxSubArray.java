package indi.simon.leetcode;

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
            maxSubArrayInternal(nums, 0, i + 1);
        } else {
            maxSubArrayInternal(nums, sumSoFar, i + 1);
        }
    }

}

//todo: 思路：递归回溯！