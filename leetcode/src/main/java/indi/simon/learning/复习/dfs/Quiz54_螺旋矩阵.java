package indi.simon.learning.复习.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class Quiz54_螺旋矩阵 {

    public static void main(String[] args) {
        Quiz54_螺旋矩阵 quiz = new Quiz54_螺旋矩阵();
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
