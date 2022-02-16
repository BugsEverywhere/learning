package indi.simon.learning.leetcode.gogo20220214;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz454 {

    public static void main(String[] args) {

    }

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> first2RowSumCountMap = new HashMap<>();
        //将第一个第二个数组各项的和作为key，和相同的组合出现次数作为value放入map
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                first2RowSumCountMap.merge(nums1[i] + nums2[j], 1, Integer::sum);
            }
        }

        int combinationCount = 0;

        //遍历nums3和nums4
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                if (first2RowSumCountMap.containsKey(-(nums3[i] + nums4[j]))) {
                    combinationCount = combinationCount + first2RowSumCountMap.get(-(nums3[i] + nums4[j]));
                }
            }
        }

        return combinationCount;
    }


}
