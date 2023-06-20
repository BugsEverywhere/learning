package indi.simon.learning.leetcode.gogo20230619;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1254 {

    public static void main(String[] args) {
        Quiz1254 quiz1254 = new Quiz1254();
        int res = quiz1254.closedIsland(new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 0}
        });
        System.out.println(res);
    }

    public int closedIsland(int[][] grid) {
        int cnt = 0;
        int[] xDir = new int[]{-1, 0, 1, 0};
        int[] yDir = new int[]{0, -1, 0, 1};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0 || i == 0 || i == grid.length - 1 || j == 0 || j == grid[0].length - 1) {
                    continue;
                }
                boolean effect = true;
                Queue<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[]{i, j});
                while (queue.size() > 0) {
                    int[] node = queue.poll();
                    grid[node[0]][node[1]] = 1;
                    for (int k = 0; k < 4; k++) {
                        int row = node[0] + xDir[k];
                        int col = node[1] + yDir[k];
                        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
                            continue;
                        }
                        if (grid[row][col] == 0 && row != 0 && row != grid.length - 1 && col != 0 && col != grid[0].length - 1) {
                            queue.add(new int[]{row, col});
                        } else if (grid[row][col] == 0 && (row == 0 || row == grid.length - 1 || col == 0 || col == grid[0].length - 1)) {
                            effect = false;
                        }
                    }
                }
                if (effect) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

}
