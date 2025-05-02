package indi.simon.learning.复习.前缀和;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2025/5/1.
 */
//todo: 仍然是经典的前缀和 + 哈希表，哈希表中存两数组的差值
public class Quiz1983_前缀和_哈希表_两数组差值 {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 1, 0, 1};
        int[] nums2 = new int[]{0, 1, 1, 0};
        Quiz1983_前缀和_哈希表_两数组差值 quiz1983前缀和哈希表两数组差值 = new Quiz1983_前缀和_哈希表_两数组差值();
        int res = quiz1983前缀和哈希表两数组差值.widestPairOfIndices(nums1, nums2);
        System.out.println(res);
    }

    public int widestPairOfIndices(int[] nums1, int[] nums2) {
        //登记簿，保存两个数组的差值 -> 下标的映射
        Map<Integer, Integer> gapIndexMap = new HashMap<>();
        gapIndexMap.put(0, -1);

        int gapSum = 0;
        int maxWidth = 0;
        for (int i = 0; i < nums1.length; i++) {
            int gap = nums1[i] - nums2[i];
            gapSum += gap;
            if (gapIndexMap.containsKey(gapSum)) {
                maxWidth = Math.max(maxWidth, i - gapIndexMap.get(gapSum));
            } else {
                //todo: 仅在登记簿中没有该余数时put
                gapIndexMap.put(gapSum, i);
            }
        }

        return maxWidth;
    }


}
