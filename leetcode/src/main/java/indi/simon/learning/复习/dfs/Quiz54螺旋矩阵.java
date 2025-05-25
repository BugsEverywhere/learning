package indi.simon.learning.复习.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quiz54螺旋矩阵 {

    public static void main(String[] args) {
        Quiz54螺旋矩阵 quiz = new Quiz54螺旋矩阵();
        List<Integer> result = quiz.spiralOrder(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},
                {17, 18, 19, 20},
                {21, 22, 23, 24}});

        assert result != null;
        System.out.println(Arrays.toString(result.toArray()));

    }

    private int[] xDir = new int[] { 1, 0, -1, 0 };
    private int[] yDir = new int[] { 0, 1, 0, -1 };

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> path = new ArrayList<>();
        dfs(0, 0, path, matrix, 0);
        return path;
    }

    private void dfs(int i, int j, List<Integer> path, int[][] matrix, int k) {
        path.add(matrix[i][j]);
        matrix[i][j] = Integer.MIN_VALUE;

        int s = k;
        while (true) {
            int newI = i + yDir[s];
            int newJ = j + xDir[s];
            if(legal(newI, newJ, matrix)){
                dfs(newI, newJ, path, matrix, s);
                break;
            } else {
                //todo: 要考虑方向是顺时针悠优先级，所以在原方向走不通时，s要自增
                s++;
                if(s == 4){
                    s = 0;
                }
                if(s == k){
                    break;
                }
            }
        }
    }

    private boolean legal(int i, int j, int[][] matrix) {
        if (i >= matrix.length || i < 0 || j >= matrix[0].length || j < 0) {
            return false;
        }
        if (matrix[i][j] == Integer.MIN_VALUE) {
            return false;
        }
        return true;
    }


}
