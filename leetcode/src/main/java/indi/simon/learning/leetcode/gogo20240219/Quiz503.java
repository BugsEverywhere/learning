package indi.simon.learning.leetcode.gogo20240219;

import java.util.Deque;
import java.util.LinkedList;

import static java.util.Arrays.fill;

/**
 * Created by Chen Zhuo on 2024/2/24.
 */
public class Quiz503 {

    //官方单调栈
    public int[] nextGreaterElementsOfficial(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        fill(ret, -1);
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < n * 2 - 1; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                ret[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ret;
    }

    //====================================================================

    public int[] nextGreaterElements(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(num, max);
        }

        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == max) {
                res[i] = -1;
            }
            int j = i + 1;
            if (j == nums.length) {
                j = 0;
            }
            while (j != i) {
                if (nums[j] > nums[i]) {
                    res[i] = nums[j];
                    break;
                }
                j++;
                if (j == nums.length) {
                    j = 0;
                }
            }
        }
        return res;
    }
}
