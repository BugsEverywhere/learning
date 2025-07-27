package indi.simon.learning.复习.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by Chen Zhuo on 2025/4/22.
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1：
 *
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 示例 2：
 *
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 */
public class Quiz200_岛屿数量 {

    private int[] xDir;
    private int[] yDir;

    private char[][] grid;

    public static void main(String[] args) {
        Quiz200_岛屿数量 quiz200岛屿数量 = new Quiz200_岛屿数量();
        char[][] grid = new char[][]{{'0', '1', '0'}, {'1', '0', '1'}, {'0', '1', '0'}};

        int res = quiz200岛屿数量.numIslands(grid);
        System.out.println(res);
    }

    public int numIslands(char[][] grid) {
        xDir = new int[]{-1, 0, 1, 0};
        yDir = new int[]{0, -1, 0, 1};

        this.grid = grid;
        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    bfs(i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private void bfs(int i, int j) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{i, j});
        int currI = i;
        int currJ = j;
        while (queue.size() > 0) {
            int[] currIndexArr = queue.poll();
            currI = currIndexArr[0];
            currJ = currIndexArr[1];
//            grid[currI][currJ] = 'x';
            for (int m = 0; m < 4; m++) {
                if (xDir[m] == yDir[m]) {
                    continue;
                }
                int newX = currI + xDir[m];
                int newY = currJ + yDir[m];

                if (newX < 0 || newX >= grid.length || newY < 0 || newY >= grid[0].length) {
                    continue;
                }
                if (grid[newX][newY] == '1') {
                    queue.offer(new int[]{newX, newY});
                    grid[newX][newY] = 'x';
                }

            }
        }
    }


}
