package indi.simon.learning.leetcode.动态规划;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 拓扑排序+动态规划，这里的trick是，使用一个记录有向无环图各个节点入度的数组，将入度为0的节点offer到一个队列里，
// 并while循环从该队列拿出入度为0的节点，并遍历其子序列。因此本解法说是拓扑排序，其实并没有排序，只是需要使用一个List的数组将有向无环图构造出来。
// 使用队列记录入度为0的节点（其中需要动态更新队列，每次遍历完某节点需要检查其入度是否减为0，并加入队列），层层遍历有向无环图的解法需要记一下
public class Quiz2050_reviewed {

    public static void main(String[] args) {
        Quiz2050_reviewed quiz2050 = new Quiz2050_reviewed();
        int res = quiz2050.minimumTime(3, new int[][]{{1, 3}, {2, 3}}, new int[]{3, 2, 5});
        System.out.println(res);
    }

    public int minimumTime(int n, int[][] relations, int[] time) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        //记录节点i的入度
        int[] indeg = new int[n];
        for (int[] e : relations) {
            int a = e[0] - 1, b = e[1] - 1;
            //a的一个子课程是b
            g[a].add(b);
            //b入度+1
            ++indeg[b];
        }

        Deque<Integer> q = new ArrayDeque<>();
        //修课程i的最大耗时
        int[] f = new int[n];
        int ans = 0;
        //将没有入度的节点先考虑一遍，这些节点可能在DAG中（此时为起始节点），也可能不在DAG中（亦即不与其他任何课程产生联系），
        // 因为这些节点是可以同时修的，取其中最大值来初始化ans。同时可以初始化修完这些课程本身的耗时，就是他们自己的耗时
        for (int i = 0; i < n; ++i) {
            int v = indeg[i], t = time[i];
            if (v == 0) {
                q.offer(i);
                f[i] = t;
                ans = Math.max(ans, t);
            }
        }
        //开始对DAG中入度为0的节点进行BFS
        while (!q.isEmpty()) {
            int i = q.pollFirst();
            //轮一遍该节点的所有子课程
            for (int j : g[i]) {
                //状态转移，修课程j的耗时是其所有父亲课程的状态转移而来，需要求max
                f[j] = Math.max(f[j], f[i] + time[j]);
                //顺带更新ans，等所有课程都轮完之后，最后ans就是耗时最长的课程的耗时
                ans = Math.max(ans, f[j]);
                //该子课程从父课程i处的入度考虑过一遍了，将其入度减1，如果他的入度为0了，那么加入队列，后头会轮到他
                if (--indeg[j] == 0) {
                    q.offer(j);
                }
            }
        }
        return ans;
    }


}
