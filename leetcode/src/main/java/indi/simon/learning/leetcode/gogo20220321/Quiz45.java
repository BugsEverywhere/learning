package indi.simon.learning.leetcode.gogo20220321;


import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz45 {

    public static void main(String[] args) {
        Quiz45 quiz45 = new Quiz45();
//        int[] nums = new int[]{5, 6, 4, 4, 6, 9, 4, 4, 7, 4, 4, 8, 2, 6, 8, 1, 5, 9, 6, 5, 2, 7, 9, 7, 9, 6, 9, 4, 1, 6, 8, 8, 4, 4, 2, 0, 3, 8, 5};
        int[] nums = new int[]{2, 3, 0, 1, 4};
        int jumpRes = quiz45.jump(nums);
        System.out.println(jumpRes);
    }

    private Map<Integer, Integer> mem;

    public int jump(int[] nums) {
        mem = new HashMap<>();
        return jumpInternal(nums, 0, 0);
    }

    private int jumpInternal(int[] nums, int currentPos, int totalSteps) {
        if (currentPos > nums.length - 1) {
            return -1;
        }

        if (currentPos == nums.length - 1) {
            return totalSteps;
        }

        if (mem.containsKey(currentPos)) {
            return totalSteps + mem.get(currentPos);
        }

        int minStepsFromNow = Integer.MAX_VALUE;
        for (int i = nums[currentPos]; i > 0; i--) {
            int jumpRes = jumpInternal(nums, currentPos + i, totalSteps + 1);
            if (jumpRes < 0 || jumpRes == Integer.MAX_VALUE) {
                continue;
            }
            if (jumpRes - totalSteps > 0 && jumpRes - totalSteps < minStepsFromNow) {
                minStepsFromNow = jumpRes - totalSteps;
            }
        }

        if (minStepsFromNow != Integer.MAX_VALUE) {
            mem.put(currentPos, minStepsFromNow);
        }

        return totalSteps + minStepsFromNow;
    }

}
