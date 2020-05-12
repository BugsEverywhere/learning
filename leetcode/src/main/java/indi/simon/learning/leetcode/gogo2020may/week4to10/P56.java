package indi.simon.learning.leetcode.gogo2020may.week4to10;

import java.util.*;

public class P56 {

    public static void main(String[] args) {


    }

    private static int[][] merge(int[][] intervals) {

        if (intervals.length == 0 || intervals[0].length == 0) {
            return intervals;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        List<int[]> sortedList = new ArrayList<>();
        int lastLeftBorder = Integer.MIN_VALUE;
        int lastRightBorder = Integer.MIN_VALUE;

        for (int i = 0; i < intervals.length; i++) {
            //todo 循环开始时要记得初始边界设定
            if (i == 0) {
                lastLeftBorder = intervals[i][0];
                lastRightBorder = intervals[i][1];
                continue;
            }
            if (intervals[i][1] <= lastRightBorder) {
                continue;
            }
            if (intervals[i][0] <= lastRightBorder) {
                lastRightBorder = intervals[i][1];
                continue;
            }
            sortedList.add(new int[]{lastLeftBorder, lastRightBorder});
            lastLeftBorder = intervals[i][0];
            lastRightBorder = intervals[i][1];
        }
        //todo 循环结束了也要记得收尾
        sortedList.add(new int[]{lastLeftBorder, lastRightBorder});

        int[][] result = new int[sortedList.size()][2];
        return sortedList.toArray(result);
    }

}
