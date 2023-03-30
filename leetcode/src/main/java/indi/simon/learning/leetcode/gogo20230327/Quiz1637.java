package indi.simon.learning.leetcode.gogo20230327;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1637 {

    public static void main(String[] args) {
        Quiz1637 quiz1637 = new Quiz1637();
        int res = quiz1637.maxWidthOfVerticalArea(new int[][]{{3, 1}, {9, 0}, {1, 0}, {1, 4}, {5, 3}, {8, 8}});
        System.out.println(res);
    }

    public int maxWidthOfVerticalArea(int[][] points) {

        Arrays.sort(points, Comparator.comparingInt(o -> o[0]));

        int maxGap = 0;

        for (int i = 0; i < points.length - 1; i++) {
            int gap = points[i + 1][0] - points[i][0];
            if (gap > maxGap) {
                maxGap = gap;
            }
        }

        return maxGap;
    }

}
