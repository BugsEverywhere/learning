package indi.simon.learning.leetcode.gogo20230417;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1043 {

    public static void main(String[] args) {
        Quiz1043 quiz1043 = new Quiz1043();
        int res = quiz1043.maxSumAfterPartitioning(new int[]{1, 4, 1, 5, 7, 3, 6, 1, 9, 9, 3}, 4);
        System.out.println(res);
    }

    public int maxSumAfterPartitioning(int[] arr, int k) {
        //dp[i]代表从前往后分组，在i处能得到的最大总和
        int[] dp = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            if (i >= k) {
                //计算该位置在不同窗口大小下的窗口和，与窗口的前一个位置的dp[i]值相加，取最大，状态转移
                int width = k;
                while (width > 0) {
                    //找出i-width到i中间的最大值
                    int max = Integer.MIN_VALUE;
                    for (int j = i - width + 1; j <= i; j++) {
                        max = Math.max(max, arr[j]);
                    }
                    //求出窗口大小为k时的窗口总和
                    int sum = width * max;
                    //状态转移
                    dp[i] = Math.max(dp[i], dp[i - width] + sum);
                    width--;
                }
            } else if (i == 0) {
                dp[i] = arr[0];
            } else {
                //前期i还没有大于窗口长度的情况下，直接所有数抬到窗口内最大值即可
                int max = Integer.MIN_VALUE;
                for (int j = 0; j <= i; j++) {
                    max = Math.max(max, arr[j]);
                }
                int sum = max * (i + 1);
                dp[i] = sum;
            }
        }
        return dp[arr.length - 1];
    }

}
