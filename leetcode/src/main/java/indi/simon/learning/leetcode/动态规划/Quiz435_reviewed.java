package indi.simon.learning.leetcode.动态规划;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.ToIntFunction;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: DP会超时，下方有贪心解法，不过不作要求，真实面试场景能写出DP应该就够了
public class Quiz435_reviewed {

    public static void main(String[] args) {
        Quiz435_reviewed quiz435ReviewedHinted = new Quiz435_reviewed();
        int[][] intervals = new int[][]{{1, 10}, {2, 4}, {3, 6}, {7, 8}};
        int res = quiz435ReviewedHinted.eraseOverlapIntervals(intervals);
        System.out.println(res);
    }

    //todo: 自己的DP，超时，不过官方的DP也超时，跟我思路差不多
    // 需要注意的点，下面列明了
    public int eraseOverlapIntervals(int[][] intervals) {

        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return Integer.compare(o1[0], o2[0]);
            } else {
                return Integer.compare(o1[1], o2[1]);
            }
        });

        //dp[i] means the minimum removed interval cnt before intervals[i]
        int[] dp = new int[intervals.length];
        dp[0] = 0;
        for (int i = 1; i < intervals.length; i++) {
            //inherit
            dp[i] = Integer.MAX_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                //todo: 这里不要搞错了，状态转移的时候，从j转移而来的话，要考虑i和j中间的所有元素，区别只是j本身要不要考虑删除
                if (intervals[j][1] > intervals[i][0]) {
                    //intervals[j] is overlapped with intervals[i], need to remove [j,i)
                    dp[i] = Math.min(dp[i], dp[j] + i - j);
                } else {
                    //intervals[j] is not overlapped with intervals[i], need to remove (j,i)
                    dp[i] = Math.min(dp[i], dp[j] + i - j - 1);
                }
            }
        }
        return dp[intervals.length - 1];
    }



    //todo: 贪心解法，思想是，找到最多不重叠的区间个数ans，然后n-ans就是答案。
    // 贪心的点在于，将所有区间按右边界排好序之后，这个所有区间的全集里面，理论上右边界最靠左（也就是最小）的区间肯定应该在最终的不重叠区间里面，
    // 将其考虑进来。然后以它的右边界为界限，所有左右边界均大于该界限的区间组成一个新的区间全集，挑出其中一个右边界最小的区间考虑进来，
    // 而因为所有区间都按右边界排好序了，这个最小的区间可以顺着排好序的区间全集来找，
    // 依次循环。不得不说只能记住这种思考方式，面试想是想不出来的
    public int eraseOverlapIntervalsGreedy(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, Comparator.comparingInt(new ToIntFunction<int[]>() {
            @Override
            public int applyAsInt(int[] interval) {
                return interval[1];
            }
        }));

        int n = intervals.length;
        int right = intervals[0][1];
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            if (intervals[i][0] >= right) {
                ++ans;
                right = intervals[i][1];
            }
        }
        return n - ans;
    }


}
