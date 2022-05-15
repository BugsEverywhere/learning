package indi.simon.learning.leetcode.gogo20220509;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz300 {

    public static void main(String[] args) {
        Quiz300 quiz300 = new Quiz300();
        int[] nums = new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6};
        int res = quiz300.lengthOfLIS(nums);
        System.out.println(res);

    }

    public int lengthOfLIS(int[] nums) {
        int[][] dp = new int[nums.length][2];
        dp[0][0] = 1;
        dp[0][1] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int j = i - 1;
            while (j >= 0) {
                if (nums[i] > dp[j][1]) {
                    if (dp[j][0] + 1 > dp[i][0]) {
                        dp[i][0] = dp[j][0] + 1;
                        dp[i][1] = nums[i];
                    }
                }
                j--;
            }

            if (dp[i][0] == 0) {
                dp[i][0] = 1;
                dp[i][1] = nums[i];
            }

        }

        int maxLength = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            maxLength = Math.max(maxLength, dp[i][0]);
        }


        return maxLength;
    }
}
