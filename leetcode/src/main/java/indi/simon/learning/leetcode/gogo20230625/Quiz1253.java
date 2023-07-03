package indi.simon.learning.leetcode.gogo20230625;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1253 {

    public static void main(String[] args) {
        Quiz1253 quiz1253 = new Quiz1253();
        List<List<Integer>> res = quiz1253.reconstructMatrix(5, 5, new int[]{2, 1, 2, 0, 1, 0, 1, 2, 0, 1});
        System.out.println(res);
    }

    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {

        List<Integer> upperRow = new ArrayList<>();
        List<Integer> lowerRow = new ArrayList<>();

        int upperSum = 0;
        int lowerSum = 0;
        for (int i = 0; i < colsum.length; i++) {
            boolean upperLeft = false;
            if (upperSum < upper) {
                upperLeft = true;
            }
            boolean lowerLeft = false;
            if (lowerSum < lower) {
                lowerLeft = true;
            }

            if (colsum[i] == 0) {
                upperRow.add(0);
                lowerRow.add(0);
            } else if (colsum[i] == 1) {




            } else {
                if (upperLeft && lowerLeft) {
                    upperRow.add(1);
                    lowerRow.add(1);
                    upperSum++;
                    lowerSum++;
                } else {
                    //没有满足的数组
                    return new ArrayList<>();
                }
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        res.add(upperRow);
        res.add(lowerRow);
        return res;
    }

}
