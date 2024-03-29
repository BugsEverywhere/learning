package indi.simon.learning.leetcode.动态规划;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 感觉有点数论的意思，我的算法（nthUglyNumber）在1548这个神奇的数字上就不work了，因为计算出来的结果还有质因数31。。。他之前都work。但假使对所有数都work，我的复杂度也高了。
// 官方的题解更加合理，定义三个指针p2, p3, p5，分别表示算到当前所使用的2的个数，3的个数，5的个数。每次迭代计算得到合理的dp[i]时，相应的指针往前移动一步。
public class Quiz264_reviewed {

    public static void main(String[] args) {
        Quiz264_reviewed quiz264Notfinish = new Quiz264_reviewed();
        int res = quiz264Notfinish.nthUglyNumberOfficial(1548);
        System.out.println(res);
    }

    //我的成功的dp
    public int nthUglyNumberMyDp(int n) {
        //dp[i]表示第i个丑数
        long[] dp = new long[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;


        for (int i = 2; i <= n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                long times2 = dp[j] * 2;
                long times3 = dp[j] * 3;
                long times5 = dp[j] * 5;

                if (times5 <= dp[i - 1]) {
                    //够不着了，后面不用看了
                    break;
                }

                if (times2 > dp[i - 1]) {
                    dp[i] = Math.min(dp[i], times2);
                } else if (times3 > dp[i - 1]) {
                    dp[i] = Math.min(dp[i], times3);
                } else if (times5 > dp[i - 1]) {
                    dp[i] = Math.min(dp[i], times5);
                }
            }
        }

        return (int) dp[n];
    }


    //我的失败的解答
    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        int lastPointer = 0;
        int x = 0;
        for (int i = 2; i <= n; i++) {
            int minThisRound = Integer.MAX_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                if ((long) dp[j] * 5 <= (long) dp[i - 1]) {
                    break;
                }
                if (dp[j] * 2 > dp[i - 1] && dp[j] * 2 < minThisRound) {
                    lastPointer = j;
                    x = 2;
                    minThisRound = dp[j] * 2;
                }
                if (dp[j] * 3 > dp[i - 1] && dp[j] * 3 < minThisRound) {
                    lastPointer = j;
                    x = 3;
                    minThisRound = dp[j] * 3;
                }
                if (dp[j] * 5 > dp[i - 1] && dp[j] * 5 < minThisRound) {
                    lastPointer = j;
                    x = 5;
                    minThisRound = dp[j] * 5;
                }
            }
            dp[i] = minThisRound;
        }
        System.out.println(lastPointer);
        System.out.println(x);
        return dp[n];
    }


    public int nthUglyNumberOfficial(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n];
    }


}
