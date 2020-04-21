package indi.simon.learning.leetcode;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P48MatrixTransform {

    public static void main(String[] args) {
        int[][] test = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(test);
        System.out.println(Arrays.deepToString(test));
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        //行转列
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(matrix, i, j, j, i);
            }
        }
        //对调中轴线两边的列
        int midLine = n / 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < midLine; j++) {
                swap(matrix, i, j, i, n - 1 - j);
            }
        }
    }

    private static void swap(int[][] matrix, int rowIndexA, int columnIndexA, int rowIndexB, int columnIndexB) {
        int tmp = matrix[rowIndexA][columnIndexA];
        matrix[rowIndexA][columnIndexA] = matrix[rowIndexB][columnIndexB];
        matrix[rowIndexB][columnIndexB] = tmp;
    }

}


//todo: 思路是，先行转列，然后调换列的次序，so easy~