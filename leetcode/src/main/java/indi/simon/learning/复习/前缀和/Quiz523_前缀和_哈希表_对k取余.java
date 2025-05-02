package indi.simon.learning.复习.前缀和;


import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 前缀和+哈希表，这种模式适用于很多“连续子数组之和”的场景，在此题中，如果某个前缀和对k取余在之前（假设为i）出现过，
// 那么从i+1到当前位置的子数组之和是很定可以被k整除的，也就是这个子数组对k取余为0
public class Quiz523_前缀和_哈希表_对k取余 {

    public static void main(String[] args) {
        Quiz523_前缀和_哈希表_对k取余 quiz523Hinted = new Quiz523_前缀和_哈希表_对k取余();
        boolean res = quiz523Hinted.checkSubarraySum(new int[]{5, 0, 0, 0}, 3);
        System.out.println(res);
    }

    public boolean checkSubarraySum(int[] nums, int k) {

        //登记簿
        Map<Integer, Integer> remainderMap = new HashMap<>();
        //todo: 哨兵，前缀和为0时，虚拟index为-1
        remainderMap.put(0, -1);

        int prefixSum = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            prefixSum += num;
            int remainder = prefixSum % k;

            if (remainderMap.containsKey(remainder) && i - remainderMap.get(remainder) >= 2) {
                return true;
            } else if (!remainderMap.containsKey(remainder)) {
                //todo: 需要注意，这里仅在登记簿中没有该余数的情况下put，其他情况不能更新了该余数对应的index
                remainderMap.put(remainder, i);
            }
        }
        return false;
    }

}
