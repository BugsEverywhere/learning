package indi.simon.learning.leetcode.前缀和;


import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 前缀和+哈希表
public class Quiz525_hinted {

    public static void main(String[] args) {
        Quiz525_hinted quiz525Hinted = new Quiz525_hinted();
        int res = quiz525Hinted.findMaxLength(new int[]{0, 1, 1, 0, 1, 1, 1, 0});
        System.out.println(res);
    }

    public int findMaxLength(int[] nums) {
        //key为前缀和，val为该前缀和第一次出现的下标
        Map<Integer, Integer> preSumMap = new HashMap<>();
        //注意此处的处理，因为要考虑没有任何元素的前缀和0，那么此时加一个虚拟的哨兵，下标为-1
        preSumMap.put(0, -1);
        int[] preSum = new int[nums.length];
        int res = 0;
        preSum[0] = nums[0] == 1 ? 1 : -1;
        preSumMap.put(preSum[0], 0);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                preSum[i] = preSum[i - 1] - 1;
            } else {
                preSum[i] = preSum[i - 1] + 1;
            }
            //看看之前是否存在过前缀和为preSum[i]的下标
            if (preSumMap.containsKey(preSum[i])) {
                //如果存在，说明从之前下标到当前i，数组和为0，那么原始数组中在该段内0和1数量相同
                res = Math.max(res, i - preSumMap.get(preSum[i]));
            } else {
                preSumMap.put(preSum[i], i);
            }
        }

        return res;
    }

}
