package indi.simon.learning.leetcode.gogo20220919;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz698_needReview {

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        Quiz698_needReview quiz698NeedReview = new Quiz698_needReview();
        boolean res = quiz698NeedReview.canPartitionKSubsets(nums, 4);
        System.out.println(res);
    }

    //todo : 非记忆化回溯，超时
    public boolean canPartitionKSubsets(int[] nums, int k) {
        //登记簿
        Map<Integer, Integer> numCountMap = new HashMap<>();
        int sum = 0;
        //算出总和，顺便把所有数都登记一下
        for (int num : nums) {
            sum += num;
            if (numCountMap.containsKey(num)) {
                numCountMap.put(num, numCountMap.get(num) + 1);
            } else {
                numCountMap.put(num, 1);
            }
        }
        //如果总和不能被k整除，返回false
        if (sum % k != 0) {
            return false;
        }

        return canPartitionKSubsetsInternal(0, numCountMap, sum / k);

    }

    private boolean canPartitionKSubsetsInternal(int thisSideSoFar, Map<Integer, Integer> countMap, int singleSideNeeded) {
        if (countMap.size() == 0) {
            //如果最终登记簿没有数了
            if (thisSideSoFar == 0) {
                return true;
            } else {
                return false;
            }
        }

        for (Map.Entry<Integer, Integer> singleEntry : countMap.entrySet()) {
            if (thisSideSoFar + singleEntry.getKey() == singleSideNeeded) {
                //如果本边长加到此处等于所需要的边长，往下从0递归起新的边长
                Map<Integer, Integer> newMap = new HashMap<>(countMap);
                deductOneNum(newMap, singleEntry.getKey());
                boolean res = canPartitionKSubsetsInternal(0, newMap, singleSideNeeded);
                if (res) {
                    return true;
                }
            } else if (thisSideSoFar + singleEntry.getKey() < singleSideNeeded) {
                //如果本边长加上此数仍然小于所需边长，继续往下递归
                Map<Integer, Integer> newMap = new HashMap<>(countMap);
                deductOneNum(newMap, singleEntry.getKey());
                boolean res = canPartitionKSubsetsInternal(thisSideSoFar + singleEntry.getKey(), newMap, singleSideNeeded);
                if (res) {
                    return true;
                }
            }
        }

        return false;
    }

    private void deductOneNum(Map<Integer, Integer> countMap, int num) {
        if (!countMap.containsKey(num)) {
            return;
        }

        if (countMap.get(num) == 1) {
            countMap.remove(num);
        } else {
            countMap.put(num, countMap.get(num) - 1);
        }
    }

}
