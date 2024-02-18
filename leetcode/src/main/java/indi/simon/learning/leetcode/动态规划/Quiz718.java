package indi.simon.learning.leetcode.动态规划;

/**
 * Created by Chen Zhuo on 2024/2/18.
 */
public class Quiz718 {

    public static void main(String[] args) {

    }

    public int findLength(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        // dp[i][j] represents the max prefix length of nums1[i~end] and nums2[j~end]
        int[][] dp = new int[n][m];

        // init last row
        for (int j = m - 1; j >= 0; j--) {
            //todo: 二维dp的初始化一定要注意这里第二个的判断条件，别搞错数组了
            if (nums2[j] == nums1[n - 1] || (j < m - 1 && dp[n - 1][j + 1] == 1)) {
                dp[n - 1][j] = 1;
            }
        }

        // init last col
        for (int i = n - 1; i >= 0; i--) {
            if (nums1[i] == nums2[m - 1] || (i < n - 1 && dp[i + 1][m - 1] == 1)) {
                dp[i][m - 1] = 1;
            }
        }

        // status transfer
        int maxRes = 0;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                //todo: 此处状态转移注意，不需要考虑dp[i][j+1]和dp[i+1][j]的情况，这与Quiz516不同
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j + 1] + 1);
                }
                maxRes = Math.max(maxRes, dp[i][j]);
            }
        }

        return maxRes;
    }
}
