package indi.simon.learning.leetcode.gogo20220801;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz542_notfinish {

    public static void main(String[] args) {

    }


    //todo: BFS解法，注意写BFS的时候，一圈套一圈入队，可以先将未访问过的节点都置为某个值，这样就能防止重复入队，这一题我考虑的时候并没有想到这一点，总觉得(i,j)节点的上下左右为第一层，那么如何定义第二层。。。
    // 其实简单的一圈一圈去遍历即可，碰到之前访问过的点直接跳过。另外本题还有一个trick就是从所有的0点开始BFS，也就是队列里面先放入所有的0点，他们肯定是离0最近的点，其次才是他们中的上下左右，以及上下左右的上下左右。
    // 一个点只要被访问过，那么都不用考虑了，不需要考虑他离多个0点哪一个更近的问题，因为BFS保证了他肯定被离最近的一个点先BFS到

    class Solution {
        public int[][] updateMatrix(int[][] matrix) {
            // 首先将所有的 0 都入队，并且将 1 的位置设置成 -1，表示该位置是 未被访问过的 1
            Queue<int[]> queue = new LinkedList<>();
            int m = matrix.length, n = matrix[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == 0) {
                        queue.offer(new int[] {i, j});
                    } else {
                        matrix[i][j] = -1;
                    }
                }
            }

            int[] dx = new int[] {-1, 1, 0, 0};
            int[] dy = new int[] {0, 0, -1, 1};
            while (!queue.isEmpty()) {
                int[] point = queue.poll();
                int x = point[0], y = point[1];
                for (int i = 0; i < 4; i++) {
                    int newX = x + dx[i];
                    int newY = y + dy[i];
                    // 如果四邻域的点是 -1，表示这个点是未被访问过的 1
                    // 所以这个点到 0 的距离就可以更新成 matrix[x][y] + 1。
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n
                            && matrix[newX][newY] == -1) {
                        matrix[newX][newY] = matrix[x][y] + 1;
                        queue.offer(new int[] {newX, newY});
                    }
                }
            }

            return matrix;
        }
    }
}
