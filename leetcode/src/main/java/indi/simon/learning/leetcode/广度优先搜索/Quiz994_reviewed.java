package indi.simon.learning.leetcode.广度优先搜索;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Chen Zhuo on 2024/2/7.
 */
//todo: 思路简单清晰
public class Quiz994_reviewed {

    public static void main(String[] args) {
        Quiz994_reviewed quiz994Reviewed = new Quiz994_reviewed();
        int res = quiz994Reviewed.orangesRotting(new int[][]{
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}});
        System.out.println(res);
    }

    //todo: 因为要统计所有腐烂橘子到所有新鲜橘子的最短距离，那么相当于多中心的BFS层序遍历，直至每一个中心都触达新鲜橘子
    public int orangesRotting(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();

        int count = 0; // count 表示新鲜橘子的数量
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if (grid[r][c] == 1) {
                    count++;
                } else if (grid[r][c] == 2) {
                    queue.add(new int[]{r, c});
                }
            }
        }

        int round = 0; // round 表示腐烂的轮数，或者分钟数
        while (count > 0 && !queue.isEmpty()) {
            round++;
            int n = queue.size();
            //层序遍历必然在while中有自己的for循环，用来遍历本层
            for (int i = 0; i < n; i++) {
                int[] rotten = queue.poll();
                int r = rotten[0];
                int c = rotten[1];
                if (r-1 >= 0 && grid[r-1][c] == 1) {
                    grid[r-1][c] = 2;
                    count--;
                    queue.add(new int[]{r-1, c});
                }
                if (r+1 < M && grid[r+1][c] == 1) {
                    grid[r+1][c] = 2;
                    count--;
                    queue.add(new int[]{r+1, c});
                }
                if (c-1 >= 0 && grid[r][c-1] == 1) {
                    grid[r][c-1] = 2;
                    count--;
                    queue.add(new int[]{r, c-1});
                }
                if (c+1 < N && grid[r][c+1] == 1) {
                    grid[r][c+1] = 2;
                    count--;
                    queue.add(new int[]{r, c+1});
                }
            }
        }

        if (count > 0) {
            return -1;
        } else {
            return round;
        }
    }


}
