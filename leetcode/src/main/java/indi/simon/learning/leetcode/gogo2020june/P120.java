package indi.simon.learning.leetcode.gogo2020june;

import java.util.Arrays;
import java.util.List;

public class P120 {

    public static void main(String[] args) {

    }

    private static int minimumTotal(List<List<Integer>> triangle) {

        int[] sumArr = new int[triangle.size()];

        for (List<Integer> singleRow : triangle) {
            for (int j = singleRow.size() - 1; j >= 0; j--) {
                if (j == 0) {
                    sumArr[j] = sumArr[j] + singleRow.get(j);
                } else if (j == singleRow.size() - 1) {
                    sumArr[j] = sumArr[j - 1] + singleRow.get(j);
                } else {
                    sumArr[j] = Math.min(sumArr[j], sumArr[j - 1]) + singleRow.get(j);
                }
            }
        }

        Arrays.sort(sumArr);
        return sumArr[0];
    }
}
