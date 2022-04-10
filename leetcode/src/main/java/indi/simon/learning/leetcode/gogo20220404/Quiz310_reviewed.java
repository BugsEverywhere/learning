package indi.simon.learning.leetcode.gogo20220404;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz310_reviewed {

    public static void main(String[] args) {
        Quiz310_reviewed quiz310 = new Quiz310_reviewed();
//        int[][] edges = new int[][]{{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
//        int[][] edges = new int[][]{{1, 0}, {1, 2}, {1, 3}};
        int[][] edges = new int[0][];
        List<Integer> res = quiz310.findMinHeightTrees(1, edges);
        System.out.println(res);

    }

    //=================================================================================BFS，广度优先搜索

    //todo: 思路就是，找出相隔最远的两个节点，然后找出他俩之间的路径，路径的中间一个或者两个（取决于路径是奇数还是偶数）节点即为根节点
    // 这个思路是想到了，但是如何找出距离最远的两个节点没想到好办法。官方题解的思路是，先随便从一个节点触发，找出距离他最远的一个节点x，
    // 再从这个x节点出发找距离他最远的节点y，x和y就肯定是距离最远的俩节点了
    public List<Integer> findMinHeightTreesOfficial(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        List<Integer>[] nodeConnectList = new List[n];
        for (int i = 0; i < n; i++) {
            nodeConnectList[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            nodeConnectList[edge[0]].add(edge[1]);
            nodeConnectList[edge[1]].add(edge[0]);
        }
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        /* 找到与节点 0 最远的节点 x */
        int x = findLongestNode(0, parent, nodeConnectList);
        /* 找到与节点 x 最远的节点 y */
        int y = findLongestNode(x, parent, nodeConnectList);
        /* 求出节点 x 到节点 y 的路径 */
        List<Integer> path = new ArrayList<>();
        parent[x] = -1;
        while (y != -1) {
            path.add(y);
            y = parent[y];
        }
        int m = path.size();
        if (m % 2 == 0) {
            ans.add(path.get(m / 2 - 1));
        }
        ans.add(path.get(m / 2));
        return ans;
    }

    public int findLongestNode(int nodeConcerned, int[] parent, List<Integer>[] nodeConnectList) {
        int nodeCount = nodeConnectList.length;
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[nodeCount];
        queue.offer(nodeConcerned);
        visited[nodeConcerned] = true;
        int node = -1;
        //todo: 广度优先搜索使用队列来找到距离最远的节点，因吹斯汀。
        // 从某个节点触发，其同一层的子节点在队列中紧密排列，随后是其孙子节点紧密排列，队列中poll出的最后一个元素必定是最后一层孙子节点中的任意一个，
        // 这期间把所有节点的父节点记录在parent数组中
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            node = currentNode;
            for (int connectedNode : nodeConnectList[currentNode]) {
                if (!visited[connectedNode]) {
                    visited[connectedNode] = true;
                    parent[connectedNode] = currentNode;
                    queue.offer(connectedNode);
                }
            }
        }
        return node;
    }

    //=============================================================================DFS，深度有限搜索

    public List<Integer> findMinHeightTreesDfsOfficial(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<Integer>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        int[] parent = new int[n];
        /* 找到与节点 0 最远的节点 x */
        Arrays.fill(parent, -1);
        /* 找到与节点 x 最远的节点 y */
        int x = findLongestNodeDfs(0, parent, adj);
        /* 求出节点 x 到节点 y 的路径 */
        int y = findLongestNodeDfs(x, parent, adj);
        List<Integer> path = new ArrayList<Integer>();
        parent[x] = -1;
        while (y != -1) {
            path.add(y);
            y = parent[y];
        }
        int m = path.size();
        if (m % 2 == 0) {
            ans.add(path.get(m / 2 - 1));
        }
        ans.add(path.get(m / 2));
        return ans;
    }

    //todo 深度优先搜索找距离nodeConcerned最远的节点
    public int findLongestNodeDfs(int nodeConcerned, int[] parent, List<Integer>[] nodeConnectList) {
        int n = nodeConnectList.length;
        int[] distance = new int[n];
        Arrays.fill(distance, -1);
        //distance数组装的是nodeConcerned到其余节点的距离，下标为节点编号
        distance[nodeConcerned] = 0;
        dfs(nodeConcerned, distance, parent, nodeConnectList);
        int maxDist = 0;
        int node = -1;
        for (int i = 0; i < n; i++) {
            if (distance[i] > maxDist) {
                maxDist = distance[i];
                node = i;
            }
        }
        return node;
    }

    public void dfs(int nodeConcerned, int[] distance, int[] parent, List<Integer>[] nodeConnectList) {
        //遍历该节点nodeConcerned的子节点
        for (int connectedNode : nodeConnectList[nodeConcerned]) {
            if (distance[connectedNode] < 0) {
                //原始的nodeConcerned到当前connectedNode的距离，是到nodeConcerned的距离加1
                distance[connectedNode] = distance[nodeConcerned] + 1;
                parent[connectedNode] = nodeConcerned;
                dfs(connectedNode, distance, parent, nodeConnectList);
            }
        }
    }

    //=================================================================
    // todo: BFS自己依葫芦画瓢，技能点：在BFS和DFS中使用数组（下标+值）的方式记录到达图中不同节点
    //  的路径（数组值记录上一个节点）或者距离（数组值记录距离某个点的距离）

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer, Set<Integer>> connectMap = new HashMap<>();
        for (int[] edge : edges) {
            Set<Integer> connect0 = connectMap.get(edge[0]);
            Set<Integer> connect1 = connectMap.get(edge[1]);

            if (connect0 == null) {
                connect0 = new HashSet<>();
                connectMap.put(edge[0], connect0);
            }

            if (connect1 == null) {
                connect1 = new HashSet<>();
                connectMap.put(edge[1], connect1);
            }

            connect0.add(edge[1]);
            connect1.add(edge[0]);
        }

        //找到距离0最远的一个点x
        int[] path = new int[n];
        Arrays.fill(path, -1);
        int x = findFarestNode(0, path, connectMap);
        //找到距离x最远的一个点y
        Arrays.fill(path, -1);
        //todo: 这里要记住每次path数组要重置
        int y = findFarestNode(x, path, connectMap);
        //计算x到y的距离
        path[x] = -1;
        List<Integer> realPath = new ArrayList<>();
        realPath.add(y);
        for (int i = y; path[i] != -1; ) {
            realPath.add(path[i]);
            i = path[i];
        }

        List<Integer> res = new ArrayList<>();
        if (realPath.size() % 2 == 0) {
            //路径长度是偶数
            res.add(realPath.get(realPath.size() / 2));
            res.add(realPath.get(realPath.size() / 2 - 1));
        } else {
            //路径长度是奇数
            res.add(realPath.get(realPath.size() / 2));
        }

        return res;
    }

    //todo: 使用path数组来记录路径
    private int findFarestNode(int node, int[] path, Map<Integer, Set<Integer>> connectMap) {
        Set<Integer> connects = connectMap.get(node);
        if (connects == null || connects.size() == 0) {
            return node;
        }

        Queue<Integer> nodeQueue = new ArrayDeque<>();
        nodeQueue.offer(node);
        int curr = -1;
        while (nodeQueue.size() != 0) {
            curr = nodeQueue.poll();
            Set<Integer> connectOfCurr = connectMap.get(curr);
            if (connectOfCurr.size() == 0) {
                continue;
            }
            for (Integer connect : connectOfCurr) {
                if (path[connect] != -1 || connect == node) {
                    continue;
                }
                path[connect] = curr;
                nodeQueue.offer(connect);
            }
        }
        return curr;
    }

}
