package indi.simon.learning.leetcode.gogo20231009;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by Chen Zhuo on 2023/10/14.
 */
public class Quiz286 {

    public static void main(String[] args) {
        Quiz286 quiz286 = new Quiz286();
        int[][] arr = new int[][]{
                {-1, 0, 2147483647, 2147483647, 0, 0, 2147483647, 2147483647, -1, 0, 0, -1, -1, 2147483647, -1, -1, 2147483647, 2147483647, 2147483647, 0, 0, -1, 2147483647, -1, -1, -1, 0, 2147483647, 0, 0, 0, -1, 2147483647, 0, 0, 2147483647, -1, -1, 0, 0, -1, 0, 2147483647, 2147483647, -1, 2147483647}, {0, -1, 0, -1, 0, 0, 0, -1, -1, 0, -1, 2147483647, 2147483647, -1, 2147483647, -1, 2147483647, -1, -1, 2147483647, 0, -1, 2147483647, 0, 2147483647, 0, -1, 2147483647, 0, -1, -1, 2147483647, 0, 2147483647, 0, 2147483647, -1, 2147483647, 2147483647, 2147483647, -1, 0, -1, 2147483647, 0, 2147483647}, {2147483647, -1, -1, 2147483647, 0, 0, 0, 2147483647, -1, 0, -1, -1, 2147483647, 2147483647, 2147483647, 2147483647, -1, 2147483647, 0, 0, 2147483647, 0, -1, 0, 0, 2147483647, 2147483647, 0, 0, 2147483647, -1, -1, 0, 2147483647, 0, 2147483647, -1, -1, 2147483647, 2147483647, 0, -1, 2147483647, -1, -1, -1}, {2147483647, -1, -1, 2147483647, 0, -1, -1, -1, -1, -1, 0, -1, -1, 2147483647, 0, -1, -1, -1, 2147483647, 2147483647, -1, 2147483647, -1, -1, -1, 0, -1, -1, 0, -1, 0, -1, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 0, 0, 2147483647, 2147483647, 2147483647, 2147483647, 0, 2147483647, 2147483647}, {2147483647, 2147483647, 2147483647, -1, -1, 2147483647, 2147483647, -1, 0, 2147483647, -1, 0, 0, 2147483647, 2147483647, 0, 2147483647, -1, -1, 2147483647, 0, 0, 0, -1, 2147483647, 0, 2147483647, -1, 2147483647, 0, 0, -1, 0, 2147483647, -1, 0, -1, -1, 2147483647, -1, 0, 0, -1, -1, -1, -1}, {-1, -1, 2147483647, 2147483647, 0, 2147483647, 0, 0, 2147483647, 2147483647, 0, -1, 0, 2147483647, -1, 2147483647, 2147483647, 0, 2147483647, 2147483647, -1, 0, 0, 2147483647, -1, -1, 0, -1, 2147483647, 0, 0, 0, 0, 0, 2147483647, 0, 0, -1, 0, 0, -1, 0, -1, 0, 0, -1}
//                {2147483647, -1, 0, 2147483647},
//                {2147483647, 2147483647, 2147483647, -1},
//                {2147483647, -1, 2147483647, -1},
//                {0, -1, 2147483647, 2147483647}
        };
        quiz286.wallsAndGatesV2(arr);
        System.out.println(arr);
    }


    public void wallsAndGatesV2(int[][] rooms) {
        int n = rooms.length;
        int m = rooms[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rooms[i][j] != 0) {
                    continue;
                }
                Queue<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[]{i, j, 0});
                int[] xDir = new int[]{-1, 1, 0, 0};
                int[] yDir = new int[]{0, 0, -1, 1};
                while (queue.size() > 0) {
                    int[] node = queue.poll();
                    int x = node[0];
                    int y = node[1];
                    int thisLayer = node[2];
                    for (int l = 0; l < 4; l++) {
                        int newX = x + xDir[l];
                        int newY = y + yDir[l];
                        int newLayer = thisLayer + 1;
                        if (newX < n && newY < m && newX >= 0 && newY >= 0 && rooms[newX][newY] > newLayer) {
                            rooms[newX][newY] = newLayer;
                            queue.offer(new int[]{newX, newY, newLayer});
                        }
                    }
                }
            }
        }
    }


    public void wallsAndGates(int[][] rooms) {
        int n = rooms.length;
        int m = rooms[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rooms[i][j] == 0 || rooms[i][j] == -1) {
                    continue;
                }
                dfs(rooms, i, j, new boolean[n][m]);
            }
        }
    }

    private int dfs(int[][] rooms, int i, int j, boolean[][] path) {
        int n = rooms.length;
        int m = rooms[0].length;
        if (rooms[i][j] == 0) {
            return 0;
        } else if (rooms[i][j] == -1) {
            return -1;
        }

        int min = rooms[i][j];
        if (i + 1 < n && !path[i + 1][j]) {
            path[i + 1][j] = true;
            int downRes = dfs(rooms, i + 1, j, path);
            path[i + 1][j] = false;
            if (downRes >= 0 && downRes < Integer.MAX_VALUE) {
                min = Math.min(min, downRes + 1);
            }
        }
        if (i - 1 >= 0 && !path[i - 1][j]) {
            path[i - 1][j] = true;
            int upRes = dfs(rooms, i - 1, j, path);
            path[i - 1][j] = false;
            if (upRes >= 0 && upRes < Integer.MAX_VALUE) {
                min = Math.min(min, upRes + 1);
            }
        }
        if (j - 1 >= 0 && !path[i][j - 1]) {
            path[i][j - 1] = true;
            int leftRes = dfs(rooms, i, j - 1, path);
            path[i][j - 1] = false;
            if (leftRes >= 0 && leftRes < Integer.MAX_VALUE) {
                min = Math.min(min, leftRes + 1);
            }
        }
        if (j + 1 < m && !path[i][j + 1]) {
            path[i][j + 1] = true;
            int rightRes = dfs(rooms, i, j + 1, path);
            path[i][j + 1] = false;
            if (rightRes >= 0 && rightRes < Integer.MAX_VALUE) {
                min = Math.min(min, rightRes + 1);
            }
        }
        rooms[i][j] = min;
        return min;
    }
}
