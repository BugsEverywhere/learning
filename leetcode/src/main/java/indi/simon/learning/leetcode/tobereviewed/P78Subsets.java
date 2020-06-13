package indi.simon.learning.leetcode.tobereviewed;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P78Subsets {

    public static void main(String[] args) {
        int[] testArr = new int[]{1, 2, 3, 4};
        List result = subsets(testArr);
        System.out.println(result);
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        subsetInternal(nums, 0, res, new ArrayList<Integer>());
        return res;
    }

    private static void subsetInternal(int[] nums, int index, List<List<Integer>> res, List<Integer> thread) {
        if (index >= nums.length) {
            res.add(thread);
            return;
        }
        //有当前元素
        subsetInternal(nums, index + 1, res, new ArrayList<>(thread));
        //没有当前元素
        List<Integer> hasMe = new ArrayList<>(thread);
        hasMe.add(nums[index]);
        subsetInternal(nums, index + 1, res, hasMe);
    }


}
