package indi.simon.learning.leetcode.april2020.week27to;

public class P240 {

    public static void main(String[] args) {

    }

    private static int[][] mem = null;
    private static boolean mark = false;

    public static boolean searchMatrix(int[][] matrix, int target) {

        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        mem = new int[matrix.length][matrix[0].length];

        return searchInternal(0, 0, target, matrix);
    }


    private static boolean searchInternal(int i, int j, int target, int[][] matrix) {

        if (mark) {
            return true;
        }

        if (i >= matrix.length || j >= matrix[0].length) {
            return false;
        }

        if (mem[i][j] == 2) {
            mark = true;
            return true;
        } else if (mem[i][j] == 1) {
            return false;
        }

        boolean result = matrix[i][j] == target || searchInternal(i + 1, j, target, matrix) || searchInternal(i, j + 1, target, matrix);

        if (result) {
            mark = true;
            mem[i][j] = 2;
            return true;
        } else {
            mem[i][j] = 1;
            return false;
        }
    }


}
