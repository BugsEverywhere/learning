package indi.simon.learning.复习.图;

import java.util.*;

public class Quiz261_以图判树 {


    public static void main(String[] args) {
        Quiz261_以图判树 quiz = new Quiz261_以图判树();
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        boolean res = quiz.validTree(5, edges);
        System.out.println(res);
    }

    /**
     * 给定编号从 0 到 n - 1 的 n 个结点。给定一个整数 n 和一个 edges 列表，其中 edges[i] = [ai, bi] 表示图中节点 ai 和 bi 之间存在一条无向边。
     * <p>
     * 如果这些边能够形成一个合法有效的树结构，则返回 true ，否则返回 false 。
     * <p>
     * 示例 1：
     * <p>
     * 输入: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
     * 输出: true
     * <p>
     * <p>
     * 示例 2:
     * <p>
     * 输入: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
     * 输出: false
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= n <= 2000
     * 0 <= edges.length <= 5000
     * edges[i].length == 2
     * 0 <= ai, bi < n
     * ai != bi
     * 不存在自循环或重复的边
     *
     * @param n
     * @param edges
     * @return
     */
    public boolean validTree(int n, int[][] edges) {
        //邻接表存储所有节点的相邻节点
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        //注意，因为边是无向边，因此一条边在两个节点都要保存对方节点
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }

        //todo: 起始节点0的父亲初始化为-1，此种搞法相当于永远取0作为树的根节点，使用BFS的方式层层遍历图中的节点，
        // 并记录下每一个节点的父节点为当前节点。
        //同来记录所有节点的父节点的map，2个作用：
        // 1. 记录key节点被遍历过
        // 2. 记录key节点的父节点（来时路径），这样在考虑key节点时跳过父节点
        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(0, -1);
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);

        while (!q.isEmpty()) {
            int node = q.poll();
            List<Integer> neighbours = adjacencyList.get(node);
            //遍历当前节点的所有相邻节点，
            // 如果是自己父亲，则跳过，因为本就打他那儿来
            // 如果不是自己父亲，说明应该是一个全新的未遍历过的节点。因此如果此时parent中已经有了这个相邻节点，则说明有环
            for (int neighbour : neighbours) {
                if (parent.get(node) == neighbour) {
                    continue;
                }
                if (parent.containsKey(neighbour)) {
                    return false;
                }
                //合法的相邻节点，入队列，并记录其父节点为当前节点，相当于登记该节点被遍历过
                q.offer(neighbour);
                parent.put(neighbour, node);
            }
        }

        //最终要记得判断parent的size，每个节点都应该有父节点，防止孤岛节点
        return parent.size() == n;
    }

}
