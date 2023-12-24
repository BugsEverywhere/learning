package indi.simon.learning.leetcode.gogo20231218;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Chen Zhuo on 2023/12/24.
 */
public class Quiz2655 {

    public static void main(String[] args) {

    }

    public int[][] findMaximalUncoveredRanges(int n, int[][] ranges) {

        Arrays.sort(ranges, Comparator.comparingInt(o -> o[0]));

        List<int[]> list = new ArrayList<>();

        int rangeIndex = 0;
        for (int i = 0; i < n; ) {
            if (rangeIndex >= ranges.length) {
                list.add(new int[]{i, n - 1});
                break;
            } else {
                int[] range = ranges[rangeIndex];
                if (i < range[0]) {
                    list.add(new int[]{i, range[0] - 1});
                }
                i = Math.max(i, range[1] + 1);
                rangeIndex++;
            }
        }

        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }
}
