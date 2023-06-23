package indi.simon.learning.leetcode.gogo20230619;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz452 {

    public static void main(String[] args) {
        Quiz452 quiz452 = new Quiz452();
        int res = quiz452.findMinArrowShots(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}});
        System.out.println(res);
    }

    public int findMinArrowShots(int[][] points) {

        Arrays.sort(points, Comparator.comparingInt(o -> o[0]));

        int currMinRight = points[0][1];
        int arrowCnt = 1;
        for (int i = 0; i < points.length; i++) {
            if (points[i][0] <= currMinRight) {
                currMinRight = Math.min(points[i][1], currMinRight);
            } else {
                arrowCnt++;
                currMinRight = points[i][1];
            }
        }

        return arrowCnt;
    }

}
