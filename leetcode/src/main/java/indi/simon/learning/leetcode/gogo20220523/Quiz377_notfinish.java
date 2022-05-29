package indi.simon.learning.leetcode.gogo20220523;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */

//todo: 我这种方法只能算出组合数，不能算出带顺序的排列组合数。看了官方解答，在DP的步骤划分上我除了问题，不能拿入参数组的元素遍历作为步骤，应该将 0 ~ target 的每一个数
public class Quiz377_notfinish {

    public static void main(String[] args) {
        Quiz377_notfinish quiz377Notfinish = new Quiz377_notfinish();
        int[] nums = new int[]{1, 2, 3};
        int res = quiz377Notfinish.combinationSum4(nums, 4);
        System.out.println(res);
    }

    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(target, 0);
        int start = 0;
        int times = 0;
        //初始化dp
        while ((start = nums[0] * times) <= target) {
            dp.put(start, 1);
            times++;
        }

        for (int i = 1; i < nums.length; i++) {
            Map<Integer, Integer> newDp = new HashMap<>();
            Set<Integer> keySet = dp.keySet();
            for (Integer valSoFar : keySet) {
                int thisTimes = 0;
                int val;
                while ((val = thisTimes * nums[i] + valSoFar) <= target) {
                    Integer combinationCount = newDp.get(val);
                    if (combinationCount == null) {
                        if (dp.get(val) == null) {
                            newDp.put(val, 1);
                        } else {
                            newDp.put(val, dp.get(val));
                        }
                    } else {
                        newDp.put(val, combinationCount + 1);
                    }
                    thisTimes++;
                }
            }
            dp = newDp;
        }

        return dp.get(target);
    }
}
