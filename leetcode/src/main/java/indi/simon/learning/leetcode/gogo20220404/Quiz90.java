package indi.simon.learning.leetcode.gogo20220404;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz90 {

    public static void main(String[] args) {
        Quiz90 quiz90 = new Quiz90();
        int[] nums = new int[]{1, 2, 2};
        List<List<Integer>> res = quiz90.subsetsWithDup(nums);
        System.out.println(res);
    }

    private List<List<Integer>> res;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new ArrayList<>();
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int num : nums) {
            numCountMap.merge(num, 1, Integer::sum);
        }

        List<Integer> numsNoDup = new ArrayList<>(numCountMap.keySet());
        subsetsWithDupInternal(numsNoDup, numCountMap, 0, new ArrayList<>());
        return res;
    }

    private void subsetsWithDupInternal(List<Integer> numsNoDup, Map<Integer, Integer> numCountMap, int index, List<Integer> path) {
        if (index >= numsNoDup.size()) {
            res.add(new ArrayList<>(path));
            return;
        }

        int num = numsNoDup.get(index);
        int count = numCountMap.get(num);
        //遍历将该数取N次的所有情形
        for (int i = 0; i <= count; i++) {
            for (int j = 1; j <= i; j++) {
                path.add(num);
            }
            subsetsWithDupInternal(numsNoDup, numCountMap, index + 1, path);
            for (int j = 1; j <= i; j++) {
                path.remove(path.size() - 1);
            }
        }
    }


}
