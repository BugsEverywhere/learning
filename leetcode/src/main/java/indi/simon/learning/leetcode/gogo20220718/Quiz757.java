package indi.simon.learning.leetcode.gogo20220718;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz757 {

    public static void main(String[] args) {

    }

    public int intersectionSizeTwo(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        int[][] setArray = new int[intervals.length][2];
        Set<Integer> set = new HashSet<>();

        for (int i = intervals.length - 1; i >= 0; i--) {
            int[] intervalI = intervals[i];
            int[] commonInterval = new int[2];
            commonInterval[0] = intervalI[0];
            commonInterval[1] = intervalI[1];

            if (i + 1 < intervals.length && (setArray[i + 1][0] >= intervals[i][0] && setArray[i + 1][0] <= intervals[i][1])) {
                //前一个区间


            }

            for (int j = i - 1; j >= 0; j--) {
                int[] intervalJ = intervals[j];
                if (intervalJ[1] >= intervalI[0] && intervalJ[0] <= intervalI[0]) {
                    //有重叠区间


                }

            }
        }


        return set.size();
    }
}
