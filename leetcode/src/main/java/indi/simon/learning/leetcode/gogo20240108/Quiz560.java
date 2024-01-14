package indi.simon.learning.leetcode.gogo20240108;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2024/1/13.
 */
public class Quiz560 {

    public static void main(String[] args) {
        Quiz560 quiz560 = new Quiz560();
        int res = quiz560.subarraySum(new int[]{1, 1, 1}, 2);
        System.out.println(res);
    }

    public int subarraySum(int[] nums, int k) {
        //前缀和数组
        int[] preSum = new int[nums.length];
        //前缀和等于key的所有下标组成的list为val
        Map<Integer, List<Integer>> map = new HashMap<>();

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            preSum[i] = sum;
            List<Integer> preSumList = map.getOrDefault(sum, new ArrayList<>());
            preSumList.add(i);
            map.put(sum, preSumList);
        }

        //开始校验等于k的子数组个数
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            int o = preSum[i] - k;
            if (o == 0) {
                cnt++;
            }
            if (map.containsKey(o)) {
                List<Integer> preSumList = map.get(o);
                for (Integer singleIndex : preSumList) {
                    if (singleIndex >= i) {
                        break;
                    }
                    cnt++;
                }
            }
        }

        return cnt;
    }

}
