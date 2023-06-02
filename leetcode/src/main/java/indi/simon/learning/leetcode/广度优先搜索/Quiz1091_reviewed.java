package indi.simon.learning.leetcode.广度优先搜索;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: BFS自己写出来的，还是要表扬。但是为啥这种场景BFS比DFS效率高？
public class Quiz1091_reviewed {

    public static void main(String[] args) {
        Quiz1091_reviewed quiz1091NeedReview = new Quiz1091_reviewed();
        int res = quiz1091NeedReview.shortestPathBinaryMatrix(new int[][]{
                {0}
        });
        System.out.println(res);
    }


    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1) {
            return -1;
        }
        if (grid.length == 1 && grid[0].length == 1 && grid[0][0] == 0) {
            return 1;
        }
        Queue<int[]> queue = new ArrayDeque<>();
        int[] cross = new int[]{-1, 0, 1};
        int[] vertical = new int[]{-1, 0, 1};
        //node[0]和node[1]是坐标，node[2]是层标
        queue.add(new int[]{0, 0, 1});

        int minStep = Integer.MAX_VALUE;
        while (queue.size() > 0) {
            int[] node = queue.poll();
            for (int i = 0; i < cross.length; i++) {
                for (int j = 0; j < vertical.length; j++) {
                    //todo: 这里注意不是判断i==0&&j==0，而是cross[i]和vertical[j]，下标数组的使用要注意不能粗心
                    if (cross[i] == 0 && vertical[j] == 0) {
                        //自己跳过
                        continue;
                    }
                    int x = node[0] + cross[i];
                    int y = node[1] + vertical[j];

                    if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
                        //越界情况跳过
                        continue;
                    }
                    if (grid[x][y] == 0) {
                        int[] newNode = new int[]{x, y, node[2] + 1};
                        //有效路径
                        queue.add(newNode);
                        //擦除路径
                        grid[x][y] = 1;
                        if (x == grid.length - 1 && y == grid[0].length - 1) {
                            //到达目的地了
                            minStep = Math.min(newNode[2], minStep);
                        }
                    }
                }
            }
        }
        return minStep == Integer.MAX_VALUE ? -1 : minStep;
    }


}
