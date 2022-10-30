package indi.simon.learning.leetcode.gogo20221024;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz934_reviewed {

    public static void main(String[] args) {
        Quiz934_reviewed quiz934_reviewed = new Quiz934_reviewed();
        int res = quiz934_reviewed.shortestBridgeOfficial(new int[][]{
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        });
        System.out.println(res);
    }

    //todo: official BFS
    public int shortestBridgeOfficial(int[][] grid) {
        int n = grid.length;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        List<int[]> island = new ArrayList<>();
        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    grid[i][j] = -1;
                    //首先第一次BFS，探索得到整个岛屿，存入island，并且还能将整个岛屿标记为-1
                    while (!queue.isEmpty()) {
                        int[] cell = queue.poll();
                        int x = cell[0], y = cell[1];
                        island.add(cell);
                        for (int k = 0; k < 4; k++) {
                            int nx = x + dirs[k][0];
                            int ny = y + dirs[k][1];
                            if (nx >= 0 && ny >= 0 && nx < n && ny < n && grid[nx][ny] == 1) {
                                queue.offer(new int[]{nx, ny});
                                grid[nx][ny] = -1;
                            }
                        }
                    }
                    //将所有island1中的点位加入队列，方便后续第二次BFS
                    for (int[] cell : island) {
                        queue.offer(cell);
                    }
                    //记录圈儿数的step
                    int step = 0;
                    while (!queue.isEmpty()) {
                        //每一轮队列的大小，代表此圈儿中需要探索的点位的个数，这些点位都处于同一圈儿，比如初始的所有island1的点位
                        int sz = queue.size();
                        //遍历同一圈儿中的每个点位，只将他周围等于0的点位放入队列，以便进行下一轮BFS
                        for (int k = 0; k < sz; k++) {
                            int[] cell = queue.poll();
                            int x = cell[0], y = cell[1];
                            //遍历该点位的四个方向
                            for (int d = 0; d < 4; d++) {
                                int nx = x + dirs[d][0];
                                int ny = y + dirs[d][1];
                                //边界判断，如果越界了就无须考虑
                                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                                    if (grid[nx][ny] == 0) {
                                        //如果该方向的邻居点位是0，加入队列
                                        queue.offer(new int[]{nx, ny});
                                        grid[nx][ny] = -1;
                                    } else if (grid[nx][ny] == 1) {
                                        //如果该方向的邻居点位是1，直接返回当前圈儿数
                                        return step;
                                    }
                                }
                            }
                        }
                        //每一圈结束了要记得更新圈儿数
                        step++;
                    }
                }
            }
        }
        return 0;
    }


}
