package indi.simon.learning.leetcode.gogo20231022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2023/10/22.
 */
public class Quiz325 {

    public static void main(String[] args) {

    }

    public int maxSubArrayLen(int[] nums, int k) {
        int[] preSum = new int[nums.length];
        //记录下前缀和为key的最早一个下标
        Map<Integer, Integer> preSumIndex = new HashMap<>();
        preSumIndex.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                preSum[i] = nums[i];
            } else {
                preSum[i] = preSum[i - 1] + nums[i];
            }

            if (!preSumIndex.containsKey(preSum[i])) {
                preSumIndex.put(preSum[i], i);
            }
        }

        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            int gap = preSum[i] - k;
            if (preSumIndex.containsKey(gap)) {
                maxLength = Math.max(maxLength, i - preSumIndex.get(gap));
            }
        }

        return maxLength;
    }

}
