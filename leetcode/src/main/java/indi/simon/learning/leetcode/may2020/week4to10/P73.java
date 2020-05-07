package indi.simon.learning.leetcode.may2020.week4to10;

import java.util.HashSet;
import java.util.Set;

public class P73 {

    public static void main(String[] args) {

    }

    private static void setZeroes(int[][] matrix) {

        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int[] zeroRow = new int[matrix[0].length];
        Set<Integer> zeroRowRegister = new HashSet<>();
        Set<Integer> zeroColumnRegister = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    zeroRowRegister.add(i);
                    zeroColumnRegister.add(j);
                }
            }
        }

        for (Integer singleRowIndex : zeroRowRegister) {
            System.arraycopy(zeroRow, 0, matrix[singleRowIndex], 0, matrix[singleRowIndex].length);
        }

        for (Integer singleColumnIndex : zeroColumnRegister) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][singleColumnIndex] = 0;
            }
        }

    }

}
