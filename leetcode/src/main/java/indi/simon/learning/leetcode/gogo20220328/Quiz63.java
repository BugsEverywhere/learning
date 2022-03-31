package indi.simon.learning.leetcode.gogo20220328;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz63 {

    public static void main(String[] args) {
        int[][] obstacleGrid = new int[][]{{0, 0}};
        Quiz63 quiz63 = new Quiz63();
        int res = quiz63.uniquePathsWithObstacles(obstacleGrid);
        System.out.println(res);
    }

    private Integer[][] mem;

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        mem = new Integer[obstacleGrid.length][obstacleGrid[0].length];
        return uniquePathsWithObstaclesInternal(obstacleGrid, 0, 0);
    }

    private int uniquePathsWithObstaclesInternal(int[][] obstacleGrid, int m, int n) {
        if (m == obstacleGrid.length - 1 && n == obstacleGrid[0].length - 1 && obstacleGrid[m][n] == 0) {
            //todo: 第一次提交忘记上面第三个边界条件
            //todo: 第二次提交把m和n搞反了
            //到终点了
            return 1;
        }

        if (obstacleGrid[m][n] == 1) {
            return 0;
        }
        //todo: 第三次提交忘记这个边界条件

        if (mem[m][n] != null) {
            return mem[m][n];
        }


        int rightStepCount = 0;
        if (n + 1 < obstacleGrid[0].length && obstacleGrid[m][n + 1] == 0) {
            rightStepCount = uniquePathsWithObstaclesInternal(obstacleGrid, m, n + 1);
        }

        int downStepCount = 0;
        if (m + 1 < obstacleGrid.length && obstacleGrid[m + 1][n] == 0) {
            downStepCount = uniquePathsWithObstaclesInternal(obstacleGrid, m + 1, n);
        }

        mem[m][n] = rightStepCount + downStepCount;
        return mem[m][n];
    }
}
