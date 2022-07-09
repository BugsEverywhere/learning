package indi.simon.learning.leetcode.gogo20220627;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz377 {

    public static void main(String[] args) {

    }

    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);

        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        //dp[i]代表数字i可以由nums中的
        int[] dp = new int[target + 1];
        for (int i = 1; i <= target; i++) {


        }


        return -1;
    }
}
