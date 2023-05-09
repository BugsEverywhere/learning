package indi.simon.learning.leetcode.gogo20230508;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz845 {

    public static void main(String[] args) {
        Quiz845 quiz845 = new Quiz845();
        int res = quiz845.longestMountain(new int[]{1,2,3,4,5,6,7,8,9});
        System.out.println(res);
    }

    public int longestMountain(int[] arr) {
        //dp[i][0]代表i之前连续上升的个数，dp[i][1]代表i之前连续上升加下降的个数
        int[][] dp = new int[arr.length][2];

        int maxLength = 0;
        for (int i = 1; i < dp.length; i++) {
            if (arr[i] > arr[i - 1]) {
                dp[i][0] = dp[i - 1][0] + 1;
            } else if (arr[i] < arr[i - 1]) {
                if (i - 2 >= 0 && arr[i - 1] < arr[i - 2] && dp[i - 1][1] != 0) {
                    //连续下降
                    dp[i][1] = dp[i - 1][1] + 1;
                } else if (i - 2 >= 0 && arr[i - 1] > arr[i - 2]) {
                    //i-1是山峰，下降的时候要续上之前的连续上升个数
                    dp[i][1] = dp[i - 1][0] + 1;
                } else {
                    dp[i][1] = 0;
                }
            }
            maxLength = Math.max(maxLength, dp[i][1]);
        }

        return maxLength == 0 ? 0 : maxLength + 1;
    }

}
