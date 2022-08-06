package indi.simon.learning.leetcode.gogo20220801;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1403 {

    public static void main(String[] args) {
        Quiz1403 quiz1403 = new Quiz1403();
        List<Integer> res = quiz1403.minSubsequence(new int[]{});


    }

    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int curSum = 0;
        List<Integer> res = new ArrayList<>();
        for (int j = nums.length - 1; j >= 0; j--) {
            curSum += nums[j];
            res.add(nums[j]);
            if (curSum > sum - curSum) {
                return res;
            }
        }

        return res;
    }

}
