package indi.simon.learning.leetcode.gogo20240212;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by Chen Zhuo on 2024/2/18.
 */
public class Quiz994 {

    public int orangesRotting(int[][] grid) {
        int freshCnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    freshCnt++;
                }
            }
        }

        if (freshCnt == 0) {
            return 0;
        }

        return bfs(grid, freshCnt);
    }

    private int bfs(int[][] grid, int freshCnt) {
        // containing all rotten oranges
        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                    grid[i][j] = -1;
                }
            }
        }

        int rottenCnt = 0;
        int minutes = 0;

        // bfs from all rotten ones simultaneously
        int[] xDir = new int[]{-1, 1, 0, 0};
        int[] yDir = new int[]{0, 0, -1, 1};
        while (queue.size() > 0) {
            Queue<int[]> newQueue = new ArrayDeque<>();
            while (queue.size() > 0) {
                int[] cell = queue.poll();
                int x = cell[0];
                int y = cell[1];
                for (int k = 0; k < 4; k++) {
                    int newX = x + xDir[k];
                    int newY = y + yDir[k];
                    if (newX < 0 || newX >= grid.length || newY < 0 || newY >= grid[0].length) {
                        continue;
                    }
                    if (grid[newX][newY] == 1) {
                        grid[newX][newY] = 2;
                        rottenCnt++;
                        newQueue.offer(new int[]{newX, newY});
                        grid[newX][newY] = -1;
                    } else if (grid[newX][newY] == 2) {
                        newQueue.offer(new int[]{newX, newY});
                        grid[newX][newY] = -1;
                    }
                }
            }
            queue = newQueue;
            if (queue.size() != 0) {
                minutes++;
            }
        }

        if (freshCnt == rottenCnt) {
            return minutes;
        } else {
            return -1;
        }
    }
}
