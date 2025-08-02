package indi.simon.learning.复习.贪心;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.ToIntFunction;

/**
 * @author chenzhuo(zhiyue)
 *
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 *
 * 注意 只在一点上接触的区间是 不重叠的。例如 [1, 2] 和 [2, 3] 是不重叠的。
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 *
 * 输入: intervals = [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 *
 * 输入: intervals = [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 * 提示:
 *
 * 1 <= intervals.length <= 105
 * intervals[i].length == 2
 * -5 * 104 <= starti < endi <= 5 * 104
 */
public class Quiz435_无重叠区间 {

    public static void main(String[] args) {
        Quiz435_无重叠区间 quiz435无重叠区间ReviewedHinted = new Quiz435_无重叠区间();
        int[][] intervals = new int[][]{{1, 10}, {2, 4}, {3, 6}, {7, 8}};
        int res = quiz435无重叠区间ReviewedHinted.eraseOverlapIntervals(intervals);
        System.out.println(res);
    }


    //todo: 贪心解法，思想是，找到最多不重叠的区间个数ans，然后n-ans就是答案。
    // 贪心的点在于，将所有区间按右边界排好序之后，这个所有区间的全集里面，理论上右边界最靠左（也就是最小）的区间肯定应该在最终的不重叠区间里面，
    // 将其考虑进来。然后以它的右边界为界限，所有左右边界均大于该界限的区间组成一个新的区间全集，挑出其中一个右边界最小的区间考虑进来，
    // 而因为所有区间都按右边界排好序了，这个最小的区间可以顺着排好序的区间全集来找，
    // 依次循环。不得不说只能记住这种思考方式，面试想是想不出来的
    public int eraseOverlapIntervals(int[][] intervals) {
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
