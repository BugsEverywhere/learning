package indi.simon.learning.leetcode.tobereviewed;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P62UniquePaths {

    public static void main(String[] args) {
        int result = uniquePaths(7, 3);
        System.out.println(result);
    }

    private static int[][] mem;

    public static int uniquePaths(int m, int n) {
        mem = new int[m][n];
        return uniquePathsInternal(m - 1, n - 1);
    }

    private static int uniquePathsInternal(int i, int j) {
        if (i > 0 && j > 0 && mem[i][j] > 0) {
            return mem[i][j];
        }
        if (i > 0 && j > 0) {
            mem[i][j] = uniquePathsInternal(i, j - 1) + uniquePathsInternal(i - 1, j);
            return mem[i][j];
        } else if (i == 0 && j > 0) {
            mem[i][j] = uniquePathsInternal(i - 1, j);
            return mem[i][j];
        } else if (j == 0 && i > 0) {
            mem[i][j] = uniquePathsInternal(i, j - 1);
            return mem[i][j];
        } else {
            return 1;
        }
    }


}


//todo: 思路：典型的自顶向下的回溯，
// 到(i,j)坐标格子的路径个数等于到(i,j-1)的方式加上(i-1,j)的方式，即f(i,j) = f(i,j-1) + f(i-1,j)