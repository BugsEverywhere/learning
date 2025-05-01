package indi.simon.learning.复习.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by Chen Zhuo on 2025/4/22.
 */
public class Quiz200 {

    private int[] xDir;
    private int[] yDir;

    private char[][] grid;

    public static void main(String[] args) {
        Quiz200 quiz200 = new Quiz200();
        char[][] grid = new char[][]{{'0', '1', '0'}, {'1', '0', '1'}, {'0', '1', '0'}};

        int res = quiz200.numIslands(grid);
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
