package indi.simon.learning.复习.区间;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Quiz56_合并区间 {

    public static void main(String[] args) {


    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        List<int[]> intervalList = new ArrayList<>();
        int currentLeft = 0;
        int currentRight = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (i == 0) {
                currentLeft = intervals[i][0];
                currentRight = intervals[i][1];
            } else if (intervals[i][0] <= currentRight) {
                currentRight = Math.max(currentRight, intervals[i][1]);
            } else {
                int[] newInterval = new int[]{currentLeft, currentRight};
                intervalList.add(newInterval);
                currentLeft = intervals[i][0];
                currentRight = intervals[i][1];
            }
        }
        int[] newInterval = new int[]{currentLeft, currentRight};
        intervalList.add(newInterval);

        int[][] resArr = new int[intervalList.size()][2];
        return intervalList.toArray(resArr);
    }

}
