package indi.simon.learning.leetcode.gogo20220221;


import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1994_notfinish {

    public static void main(String[] args) {
        Quiz1994_notfinish quiz1994 = new Quiz1994_notfinish();
        int[] arr = new int[]{18, 28, 2, 17, 29, 30, 15, 9, 12};
        int res = quiz1994.numberOfGoodSubsets(arr);
        System.out.println(res);
    }

    //第一个key为数组下标，第二个key为乘积，value为该乘积，该下标情况下好子集个数
    private Map<Integer, Map<Long, Integer>> mem;

    public int numberOfGoodSubsets(int[] nums) {
        int times = 1;
        for (int num : nums) {
            times = num * times;
        }
        mem = new HashMap<>();
        return (int) (isGoodSubset(nums, 1L, 0, new HashMap<>()) % (Math.pow(10, 9) + 7));
    }

    private int isGoodSubset(int[] nums, Long multiply, int currentIndex, Map<Integer, Integer> consideredElementCountMap) {
        if (currentIndex >= nums.length) {
            //递归到最后了
            if (isGoodNum(multiply, consideredElementCountMap) && consideredElementCountMap.size() != 0) {
                int goodSubsetCount = 1;
                for (Map.Entry<Integer, Integer> entry : consideredElementCountMap.entrySet()) {
                    goodSubsetCount = goodSubsetCount * entry.getValue();
                }
                return goodSubsetCount;
            } else {
                return 0;
            }
        }

        if (mem.containsKey(currentIndex) && mem.get(currentIndex).containsKey(multiply)) {
            //来过，后面的不用看了
            return mem.get(currentIndex).get(multiply);
        }

        //将下标为currentIndex的数考虑进该子集，并考察下一个数
        consideredElementCountMap.merge(nums[currentIndex], 1, Integer::sum);
        int considered = isGoodSubset(nums, multiply * nums[currentIndex], currentIndex + 1, consideredElementCountMap);

        //不考虑下标为currentIndex的数，跳过他
        int notConsidered = isGoodSubset(nums, multiply, currentIndex + 1, consideredElementCountMap);

        int possibilitySum = considered + notConsidered;
        if (mem.containsKey(currentIndex)) {
            mem.get(currentIndex).put(multiply, possibilitySum);
        } else {
            mem.put(currentIndex, new HashMap<>());
            mem.get(currentIndex).put(multiply, possibilitySum);
        }
        return possibilitySum;
    }


    /**
     * 考察整数a是否为好"数"
     *
     * @param a
     * @return
     */
    private boolean isGoodNum(long a, Map<Integer, Integer> consideredElementCountMap) {
        //todo:没有更好的办法来处理
        return false;
    }

    /**
     * 判断数字a是否为质数
     *
     * @param a
     * @return
     */
    private boolean isPrimeNum(long a) {
        if (a == 1 || a == 2) {
            return true;
        }
        for (int i = 2; i < a; i++) {
            if (a % i == 0) {
                return false;
            }
        }
        return true;
    }

}
