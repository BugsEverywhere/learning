package indi.simon.learning.leetcode.gogo20220328;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz57 {

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 2}, {3, 5},  {8, 10}, {12, 16}};
        int[] arr1 = new int[]{6, 7};
        Quiz57 quiz57 = new Quiz57();
        int[][] res = quiz57.insert(arr, arr1);
        System.out.println(res);
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }

        List<int[]> resList = new ArrayList<>();

        boolean loaded = false;
        for (int i = 0; i < intervals.length; ) {
            if (intervals[i][1] < newInterval[0]) {
                resList.add(intervals[i]);
                i++;
            } else if (intervals[i][0] > newInterval[1]) {
                if (!loaded) {
                    resList.add(newInterval);
                    loaded = true;
                }
                resList.add(intervals[i]);
                i++;
            } else if (intervals[i][0] <= newInterval[0] && newInterval[1] >= intervals[i][1]) {
                newInterval[0] = intervals[i][0];
                newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
                i++;
            } else if (intervals[i][1] >= newInterval[1] && intervals[i][0] >= newInterval[0]) {
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                newInterval[1] = intervals[i][1];
                i++;
            } else if (intervals[i][1] >= newInterval[1] && intervals[i][0] <= newInterval[0]) {
                //newInterval被完整吃掉
                resList.add(intervals[i]);
                loaded = true;
                i++;
            } else if (intervals[i][0] >= newInterval[0] && intervals[i][1] <= newInterval[1]) {
                //newInterval完整吃掉别人
                i++;
            }
        }

        if (!loaded) {
            resList.add(newInterval);
        }

        int[][] res = new int[resList.size()][2];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }

        return res;
    }

}
