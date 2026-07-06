package indi.simon.learning.复习.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Chen Zhuo on 2024/2/7.
 */
//todo: 思路简单清晰
public class Quiz994_腐烂的橘子 {

    public static void main(String[] args) {
        Quiz994_腐烂的橘子 quiz994Reviewed = new Quiz994_腐烂的橘子();
        int res = quiz994Reviewed.orangesRotting(new int[][]{
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}});
        System.out.println(res);
    }

    //todo: 因为要统计所有腐烂橘子到所有新鲜橘子的最短距离，那么相当于多中心的BFS层序遍历，直至每一个中心都触达新鲜橘子
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        //记录所有的腐烂橘子的队列
        Queue<int[]> rotQueue = new LinkedList<>();

        // freshCnt 表示新鲜橘子的数量
        int freshCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    freshCnt++;
                } else if (grid[i][j] == 2) {
                    rotQueue.add(new int[]{i, j});
                }
            }
        }

        int round = 0; // round 表示腐烂的轮数，或者分钟数
        while (freshCnt > 0 && !rotQueue.isEmpty()) {
            round++;
            int rotCnt = rotQueue.size();
            //for循环遍历本层腐烂橘子
            for (int i = 0; i < rotCnt; i++) {
                //以单个腐烂橘子为中心，四周遍历一遍
                int[] rotten = rotQueue.poll();
                int r = rotten[0];
                int c = rotten[1];
                if (r - 1 >= 0 && grid[r - 1][c] == 1) {
                    grid[r - 1][c] = 2;
                    freshCnt--;
                    rotQueue.add(new int[]{r - 1, c});
                }
                if (r + 1 < n && grid[r + 1][c] == 1) {
                    grid[r + 1][c] = 2;
                    freshCnt--;
                    rotQueue.add(new int[]{r + 1, c});
                }
                if (c - 1 >= 0 && grid[r][c - 1] == 1) {
                    grid[r][c - 1] = 2;
                    freshCnt--;
                    rotQueue.add(new int[]{r, c - 1});
                }
                if (c + 1 < m && grid[r][c + 1] == 1) {
                    grid[r][c + 1] = 2;
                    freshCnt--;
                    rotQueue.add(new int[]{r, c + 1});
                }
            }
        }

        if (freshCnt > 0) {
            return -1;
        } else {
            return round;
        }
    }


}
