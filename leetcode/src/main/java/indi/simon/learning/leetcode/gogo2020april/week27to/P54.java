package indi.simon.learning.leetcode.gogo2020april.week27to;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P54 {

    public static void main(String[] args) {

        List<Integer> result = spiralOrder(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},
                {17, 18, 19, 20},
                {21, 22, 23, 24}});

        assert result != null;
        System.out.println(Arrays.toString(result.toArray()));

    }

    private static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        boolean[][] mem = new boolean[matrix.length][matrix[0].length];

        for (int i = 0, j = 0; i < matrix.length && j < matrix[0].length; ) {
            mem[i][j] = true;
            result.add(matrix[i][j]);
            //todo 下一步怎么移，要观察潜在下一步是否是贴边，每一个分支的最后一个判断条件容易漏掉
            if (j + 1 != matrix[0].length && !mem[i][j + 1] && (i == 0 || mem[i - 1][j])) {
                j++;
            } else if (i + 1 != matrix.length && !mem[i + 1][j] && (j == matrix[0].length - 1 || mem[i][j + 1])) {
                i++;
            } else if (j - 1 >= 0 && !mem[i][j - 1] && (i == matrix.length - 1 || mem[i + 1][j])) {
                j--;
            } else if (i - 1 >= 0 && !mem[i - 1][j] && (j == 0 || mem[i][j - 1])) {
                i--;
            } else {
                break;
            }
        }

        return result;
    }


}
