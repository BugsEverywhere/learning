package indi.simon.learning.复习.dfs;

import java.util.*;

public class Quiz261_以图判树_needReview {

    /**
     * 给定编号从 0 到 n - 1 的 n 个结点。给定一个整数 n 和一个 edges 列表，其中 edges[i] = [ai, bi] 表示图中节点 ai 和 bi 之间存在一条无向边。
     *
     * 如果这些边能够形成一个合法有效的树结构，则返回 true ，否则返回 false 。
     *
     * 示例 1：
     *
     * 输入: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
     * 输出: true
     *
     *
     * 示例 2:
     *
     * 输入: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
     * 输出: false
     *
     *
     * 提示：
     *
     * 1 <= n <= 2000
     * 0 <= edges.length <= 5000
     * edges[i].length == 2
     * 0 <= ai, bi < n
     * ai != bi
     * 不存在自循环或重复的边
     *
     *
     * @param n
     * @param edges
     * @return
     */
    public boolean validTree(int n, int[][] edges) {

        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }

        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(0, -1);
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            for (int neighbour : adjacencyList.get(node)) {
                if (parent.get(node) == neighbour) {
                    continue;
                }
                if (parent.containsKey(neighbour)) {
                    return false;
                }
                stack.push(neighbour);
                parent.put(neighbour, node);
            }
        }

        return parent.size() == n;
    }

}
