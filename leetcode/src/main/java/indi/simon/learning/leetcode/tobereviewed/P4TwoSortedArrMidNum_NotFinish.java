package indi.simon.learning.leetcode.tobereviewed;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P4TwoSortedArrMidNum_NotFinish {

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2};
        int[] arr2 = new int[]{-1, 3};

        double result = findMedianSortedArrays(arr1, arr2);
        System.out.println(result);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        int totalLength = nums1.length + nums2.length;
        int i = 0;
        int j = 0;

        if (nums1.length != 0 && nums2.length != 0) {
            while (i + j < totalLength) {
                if (nums1[i] < nums2[j]) {
                    stack.push(nums1[i]);
                    i = i + 1 >= nums1.length ? nums1.length - 1 : i + 1;
                } else if (nums1[i] > nums2[j]) {
                    stack.push(nums2[j]);
                    j = j + 1 >= nums2.length ? nums2.length - 1 : j + 1;
                } else {
                    stack.push(nums1[i]);
                    stack.push(nums2[j]);
                    i = i + 1 >= nums1.length ? nums1.length - 1 : i + 1;
                    j = j + 1 >= nums2.length ? nums2.length - 1 : j + 1;
                }
                if (stack.size() >= (totalLength / 2) && totalLength % 2 == 0) {
                    int lastInStack = stack.pop();
                    int nextAddToStack;
                    if(nums1[i] > nums2[j]){
                        nextAddToStack = nums1[i];
                    } else {
                        nextAddToStack = nums2[j];
                    }
                    double result = ((double)(lastInStack + nextAddToStack)) /2;
                    return result;
                } else if (stack.size() >= ((totalLength / 2) + 1) && totalLength % 2 == 1) {
                    return stack.pop();
                }
            }
        } else if (nums1.length == 0) {
            return nums2.length % 2 == 0 ? ((double) (nums2[nums2.length / 2] + nums2[nums2.length / 2 - 1])) / 2 : nums2[nums2.length / 2];
        } else {
            return nums1.length % 2 == 0 ? ((double) (nums1[nums1.length / 2] + nums1[nums1.length / 2 - 1])) / 2 : nums1[nums1.length / 2];
        }
        return -1;
    }

}


//此解法仍然是O(M+N)复杂度的，因为是O((M+N)/2)，不过要达到log(M+N)的太难了，基本上不会考