package indi.simon.learning.复习.动态规划;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz300最长递增子序列 {

    public static void main(String[] args) {
        Quiz300最长递增子序列 quiz300最长递增子序列 = new Quiz300最长递增子序列();
        int[] nums = new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6};
        int res = quiz300最长递增子序列.lengthOfLIS(nums);
        System.out.println(res);

    }

    //todo: 比较基础，但是有需要注意的点，所有的DP都需要注意：
    // 1. 如果使用了额外的变量来记录最大最小值，比如这里我用了res，则res一定要初始化成dp的初始状态，而不是简单初始化成0或者MAX_VALUE
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // dp[i] means the length of longest subsequence till i
        int[] dp = new int[n];
        dp[0] = 1;
        //因为dp[0]已经初始化为1了，那么res初始化也一定要初始化成dp[0]
        int res = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] >= nums[i]) {
                    continue;
                } else {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
