package indi.simon.learning.leetcode.tobereviewed;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P64MinPathSum {

    public static void main(String[] args) {
        int[][] test = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int result = minPathSum1(test);
        System.out.println(result);

    }

    //=============================================================自底向上的回溯+MEM

    private static int[][] mem;
    private static boolean[][] mem1;

    public static int minPathSum(int[][] grid) {
        mem = new int[grid.length][grid[0].length];
        mem1 = new boolean[grid.length][grid[0].length];
        minPathSumInternal(0, 0, grid, 0);
        return mem[grid.length - 1][grid[0].length - 1];
    }

    private static void minPathSumInternal(int i, int j, int[][] grid, int sumSoFar) {
        if (i >= grid.length || j >= grid[0].length) {
            return;
        }
        int resultIj = sumSoFar + grid[i][j];
        if (mem1[i][j] && mem[i][j] > resultIj) {
            //来过，但是发现了更小的路径
            mem[i][j] = resultIj;
            //往下走
            minPathSumInternal(i, j + 1, grid, resultIj);
            //往右走
            minPathSumInternal(i + 1, j, grid, resultIj);
        } else if (!mem1[i][j]) {
            //来都没来过
            mem1[i][j] = true;
            mem[i][j] = resultIj;
            //往下走
            minPathSumInternal(i, j + 1, grid, resultIj);
            //往右走
            minPathSumInternal(i + 1, j, grid, resultIj);
        } else {
            //来过，但是没有之前的小
            return;
        }
    }

    //===========================================================动态规划

    private static int[][] mem2;

    public static int minPathSum1(int[][] grid) {
        mem2 = new int[grid.length][grid[0].length];

        mem2[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            mem2[i][0] = grid[i][0] + mem2[i - 1][0];
        }

        for (int j = 1; j < grid[0].length; j++) {
            mem2[0][j] = grid[0][j] + mem2[0][j - 1];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                mem2[i][j] = Math.min(mem2[i - 1][j] + grid[i][j], mem2[i][j - 1] + grid[i][j]);
            }
        }

        return mem2[grid.length - 1][grid[0].length - 1];
    }


}
