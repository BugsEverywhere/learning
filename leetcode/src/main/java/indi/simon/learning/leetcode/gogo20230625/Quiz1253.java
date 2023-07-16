package indi.simon.learning.leetcode.gogo20230625;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1253 {

    public static void main(String[] args) {
        Quiz1253 quiz1253 = new Quiz1253();
        List<List<Integer>> res = quiz1253.reconstructMatrix(9, 2, new int[]{0, 1, 2, 0, 0, 0, 0, 0, 2, 1, 2, 1, 2});
        System.out.println(res);
    }

    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {

        int[][] res = new int[2][colsum.length];

        //先重构双0列和双1列
        int singleOneColCnt = 0;
        int actualUpper = 0;
        int actualLower = 0;
        for (int i = 0; i < colsum.length; i++) {
            if (colsum[i] == 1) {
                res[0][i] = -1;
                res[1][i] = -1;
                singleOneColCnt++;
            } else if (colsum[i] == 0) {
                res[0][i] = 0;
                res[1][i] = 0;
            } else {
                res[0][i] = 1;
                res[1][i] = 1;
                actualUpper++;
                actualLower++;
            }
        }

        //再重构单1列，如果剩下的单1列无法满足upper或者lower，返回false
        if (actualUpper > upper || actualLower > lower) {
            return new ArrayList<>();
        }


        if (upper - actualUpper + (lower - actualLower) != singleOneColCnt) {
            return new ArrayList<>();
        }

        List<List<Integer>> finalRes = new ArrayList<>();
        finalRes.add(new ArrayList<>());
        finalRes.add(new ArrayList<>());
        for (int i = 0; i < colsum.length; i++) {
            if (res[0][i] != -1) {
                finalRes.get(0).add(res[0][i]);
                finalRes.get(1).add(res[1][i]);
                continue;
            }
            if (actualUpper < upper) {
                res[0][i] = 1;
                finalRes.get(0).add(1);
                finalRes.get(1).add(0);
                actualUpper++;
            } else {
                res[1][i] = 1;
                finalRes.get(1).add(1);
                finalRes.get(0).add(0);
                actualLower++;
            }
        }

        return finalRes;
    }

}
