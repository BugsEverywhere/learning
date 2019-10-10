package indi.simon.leetcode;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P55CanJump_AlreadyReviewed {

    public static void main(String[] args) {

        int[] testArr = new int[]{};
        boolean result = canJump(testArr);
        System.out.println(result);

    }

    private static boolean result = false;
    private static boolean[] mem;

    public static boolean canJump(int[] nums) {
        mem = new boolean[nums.length];
        if (nums.length == 1) {
            return true;
        }
        canJumpInternal(nums, 0);
        return result;
    }

    private static void canJumpInternal(int[] nums, int i) {
        if (mem[i]) {
            return;
        }
        mem[i] = true;
        for (int j = 1; j <= nums[i]; j++) {
            if (result) {
                return;
            }
            if (i + j < nums.length - 1) {
                canJumpInternal(nums, i + j);
            } else if (i + j == nums.length - 1) {
                result = true;
                return;
            } else if (i + j > nums.length - 1) {
                continue;
            }
        }
    }


}


//todo: 非常有代表性的一道动态规划题，需要注意的主要有以下几点：
// 1. 正确辨识这道题中的顶和底，从哪个方向开始是自顶向下，从哪个方向又是自底向上。
// 2. 我的解法是自顶向下的回溯加MEM，如果没有MEM，单纯使用回溯，是会超时的。
// 3. 自顶向下的DP与自底向上的做法，在理论上复杂度是一致的，只是后者消除了调用栈的开销，会xue微快一点
// 4. 最完美的解法是贪心。



