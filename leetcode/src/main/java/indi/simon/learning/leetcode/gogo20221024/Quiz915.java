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

    //todo: 官方解法，反过来一趟，正过来一趟，很简洁。。。
    public int partitionDisjointOfficial(int[] nums) {
        int n = nums.length;
        int[] minRight = new int[n];
        minRight[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minRight[i] = Math.min(nums[i], minRight[i + 1]);
        }
        int maxLeft = 0;
        for (int i = 0; i < n - 1; i++) {
            maxLeft = Math.max(maxLeft, nums[i]);
            if (maxLeft <= minRight[i + 1]) {
                return i + 1;
            }
        }
        return n - 1;
    }


    //自己的解法，还ok吧
    public int partitionDisjoint(int[] nums) {
        //用于保存所有数字及其个数的map
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        //遍历一遍，把最小数都记下来，后头需要初始化游标右边的最小值
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (treeMap.containsKey(num)) {
                treeMap.put(num, treeMap.get(num) + 1);
            } else {
                treeMap.put(num, 1);
            }
            min = Math.min(min, num);
        }

        //那么动态统计游标左侧的最大值以及右侧的最小值，比较两者即可，如果前者小于等于后者了，就返回当前游标的下标加一
        int maxLeft = nums[0];
        Integer minRight;
        //因为游标从1开始，因此第一个数，也就是下标0的数天然就在左侧，开局就要将他duc一把
        decNum(treeMap, nums[0]);
        if (nums[0] == min) {
            //如果第一个数就是最小的数，那么需要更新minRight为次小的数
            minRight = treeMap.ceilingKey(nums[0]);
        } else {
            //否则minRight直接就取最小数即可
            minRight = min;
        }
        //边界条件，如果起步就中了，直接返回，后面就不用处理这个边界情况了
        if (maxLeft <= minRight) {
            return 1;
        }
        int i = 1;
        for (; i < nums.length; i++) {
            maxLeft = Math.max(maxLeft, nums[i]);
            decNum(treeMap, nums[i]);
            //更新minRight，要么仍然是他自己，要么是比他次小的那个，不得不说ceilingKey，floorKey的用法要牢记
            minRight = treeMap.ceilingKey(minRight);
            if (maxLeft <= minRight) {
                return i + 1;
            }
        }
        return i;
    }

    /**
     * 从treeMap中将一个数的个数减一，使得treeMap动态维护了当前游标右侧所剩数的个数
     *
     * @param treeMap
     * @param num
     */
    private void decNum(TreeMap<Integer, Integer> treeMap, int num) {
        if (treeMap.get(num) > 1) {
            treeMap.put(num, treeMap.get(num) - 1);
        } else {
            treeMap.remove(num);
        }

    }


}
