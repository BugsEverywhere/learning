package indi.simon.learning.leetcode.gogo20220627;


import java.util.HashSet;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 只能记住这个dp的规律，属于元素可重复利用的背包问题，同样的还有322，518
public class Quiz377_hinted {

    public static void main(String[] args) {
        Quiz377_hinted quiz377Hinted = new Quiz377_hinted();
        int[] nums = new int[]{1, 2, 3};
        int res = quiz377Hinted.combinationSum4(nums, 5);
        System.out.println(res);
    }

    public int combinationSum4(int[] nums, int target) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        //dp[i]代表数字i可以由nums中的数字组合而成的组合个数
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < i; j++) {
                if (set.contains(i - j)) {
                    dp[i] += dp[j];
                }
            }
        }

        return dp[target];
    }
}
