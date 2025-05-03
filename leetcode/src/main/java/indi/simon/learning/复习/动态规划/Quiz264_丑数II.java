package indi.simon.learning.复习.动态规划;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz264_丑数II {

    public static void main(String[] args) {
        Quiz264_丑数II quiz264Notfinish = new Quiz264_丑数II();
        int res = quiz264Notfinish.nthUglyNumber(1548);
        System.out.println(res);
    }

    public int nthUglyNumber(int n) {
        //dp[i]代表第i个丑数
        long[] dp = new long[n+1];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[1] = 1;

        for(int i = 2;i <= n;i++){
            for(int j = i-1;j >= 1;j--){
                if(dp[j]*5 > dp[i-1]){
                    dp[i] = Math.min(dp[j]*5,dp[i]);
                }
                if(dp[j]*3 > dp[i-1]){
                    dp[i] = Math.min(dp[j]*3,dp[i]);
                }
                if(dp[j]*2 > dp[i-1]){
                    dp[i] = Math.min(dp[j]*2,dp[i]);
                }
            }
        }

        return (int)dp[n];
    }


}
