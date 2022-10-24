package indi.simon.learning.leetcode.gogo20221024;

import java.util.TreeMap;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz915 {

    public static void main(String[] args) {
        Quiz915 quiz915 = new Quiz915();
        int res = quiz915.partitionDisjoint(new int[]{1, 2});
        System.out.println(res);
    }

    public int partitionDisjoint(int[] nums) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (treeMap.containsKey(num)) {
                treeMap.put(num, treeMap.get(num) + 1);
            } else {
                treeMap.put(num, 1);
            }
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        int maxLeft = nums[0];
        Integer minRight;
        decNum(treeMap, nums[0]);
        if (nums[0] == min) {
            minRight = treeMap.ceilingKey(nums[0]);
        } else {
            minRight = min;
        }
        if (maxLeft <= minRight) {
            return 1;
        }
        int i = 1;
        for (; i < nums.length; i++) {
            maxLeft = Math.max(maxLeft, nums[i]);
            decNum(treeMap, nums[i]);
            minRight = treeMap.ceilingKey(minRight);
            if (maxLeft <= minRight) {
                return i + 1;
            }
        }
        return i;
    }

    private void decNum(TreeMap<Integer, Integer> treeMap, int num) {
        if (treeMap.get(num) > 1) {
            treeMap.put(num, treeMap.get(num) - 1);
        } else {
            treeMap.remove(num);
        }

    }


}
