package indi.simon.learning.leetcode.gogo20220530;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz435_hinted {

    public static void main(String[] args) {
        Quiz435_hinted quiz435Hinted = new Quiz435_hinted();
        int[][] intervals = new int[][]{{1, 2}, {3,4}, {2,3},{1,3}};
        int res = quiz435Hinted.eraseOverlapIntervals(intervals);
        System.out.println(res);
    }

    public int eraseOverlapIntervals(int[][] intervals) {

        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));

        int[] dp = new int[intervals.length];
        dp[0] = 1;

        for (int i = 1; i < intervals.length; i++) {
            int maxIntervalCount = 0;
            for (int j = 0; j < i; j++) {
                if (!isOverlap(intervals[i], intervals[j])) {
                    //如果与之前某个区间没有重叠，则用该区间的状态加1之后与当前最值比较
                    maxIntervalCount = Math.max(dp[j] + 1, maxIntervalCount);
                } else {
                    //如果与之前某个区间重叠了，则继承该区间状态，与最值比较
                    maxIntervalCount = Math.max(dp[j], maxIntervalCount);
                }
            }
            dp[i] = maxIntervalCount;
        }

        return intervals.length - dp[intervals.length - 1];
    }

    private boolean isOverlap(int[] a, int[] b) {
        if (b[0] >= a[0]) {
            return b[0] < a[1];
        } else {
            return b[1] > a[0];
        }
    }


}
