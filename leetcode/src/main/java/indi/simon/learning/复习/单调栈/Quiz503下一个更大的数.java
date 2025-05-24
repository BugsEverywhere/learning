package indi.simon.learning.复习.单调栈;

import java.util.Arrays;
import java.util.Stack;

import static java.util.Arrays.fill;

/**
 * Created by Chen Zhuo on 2024/2/24.
 */
public class Quiz503下一个更大的数 {

    public static void main(String[] args) {
        Quiz503下一个更大的数 quiz503 = new Quiz503下一个更大的数();
        int[] res = quiz503.nextGreaterElementsOfficial(new int[]{1, 2, 3, 4, 3});
        System.out.println(Arrays.toString(res));
    }


    //官方单调栈
    public int[] nextGreaterElementsOfficial(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        //todo: 栈中保存的是下标，从前往后遍历的时候，如果当前下标的数比栈顶下标的数大，则将ret[栈顶下标]赋值为当前下标数，
        // 否则将当前下标压栈，因此，如果数组中某个序列是单调递减，则栈中是该单调递减序列的下标，因此是单调栈，
        // 例如：[1,2,3,4,3]，前三者是正序，到了i=3和4时，出现倒序，那么stack就会将下标3和4压栈，分别对应数组中的4和3
        Stack<Integer> stack = new Stack<>();
        //todo: 因为是环形数组，只需要从前往后遍历2轮即可
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                ret[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                ret[stack.pop()] = nums[i];
            }
            stack.push(i);
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
