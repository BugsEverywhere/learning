package indi.simon.learning.leetcode.gogo20220711;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz565 {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 4, 0, 3, 1, 6, 2};
        Quiz565 quiz565 = new Quiz565();
        int res = quiz565.arrayNesting(arr);
        System.out.println(res);
    }

    public int arrayNesting(int[] nums) {
        Set<Set<Integer>> combinations = new HashSet<>();
        int maxComSize = 0;
        for (int i = 0; i < nums.length; i++) {
            boolean alreadyChecked = false;
            for (Set<Integer> singleCom : combinations) {
                if (singleCom.contains(nums[i])) {
                    alreadyChecked = true;
                    break;
                }
            }
            if (!alreadyChecked) {
                Set<Integer> singleCom = new HashSet<>();
                arrayNestingInternal(i, nums, singleCom);
                combinations.add(singleCom);
                maxComSize = Math.max(maxComSize, singleCom.size());
            }
        }

        return maxComSize;
    }

    private void arrayNestingInternal(int i, int[] nums, Set<Integer> combination) {
        if (combination.contains(nums[i])) {
            return;
        } else {
            combination.add(nums[i]);
        }
        arrayNestingInternal(nums[i], nums, combination);
    }
}
