package indi.simon.learning.leetcode.并查集;

import java.util.*;

/**
 * Created by Chen Zhuo on 2023/12/24.
 */
public class Quiz1135_hinted {

    public static void main(String[] args) {
        Quiz1135_hinted quiz1135Hinted = new Quiz1135_hinted();
        int res = quiz1135Hinted.minimumCost(3, new int[][]{{1, 2, 5}, {1, 3, 6}, {2, 3, 1}});
        System.out.println(res);
    }

    //todo:
    // 1. 将所有的边按照权重从小到大排序。
    // 2. 取一条权重最小的边。
    // 3. 使用并查集（union-find）数据结构来判断加入这条边后是否会形成环。若不会构成环，则将这条边加入最小生成树中。
    // 4. 检查所有的结点是否已经全部联通，这一点可以通过目前已经加入的边的数量来判断。若全部联通，则结束算法；否则返回步骤 2.
    public int minimumCost(int n, int[][] connections) {

        if (connections.length < n - 1) {
            return -1;
        }
        //将所有的边，按照权重排序，从权重最小者开始遍历
        Arrays.sort(connections, Comparator.comparingInt(o -> o[2]));

        //构建并查集
        Map<Integer, Integer> map = new HashMap<>();
        //初始化所有节点的根指向自己
        for (int i = 1; i <= n; i++) {
            map.put(i, i);
        }

        //结果集的所有边
        List<Integer> edges = new ArrayList<>();

        //遍历所有连接
        for (int[] edge : connections) {
            int city1 = edge[0];
            int city2 = edge[1];

            int city1Root = map.get(city1);
            while (city1Root != map.get(city1Root)) {
                city1Root = map.get(city1Root);
            }

            int city2Root = map.get(city2);
            while (city2Root != map.get(city2Root)) {
                city2Root = map.get(city2Root);
            }

            if (city1Root != city2Root) {
                edges.add(edge[2]);
                //todo: 此处注意，如果判断出两个节点不属于同一个集合，那么需要将节点2所在集合的根指向节点1（或者其根也可以）
                // 那么这个节点2的根下的所有节点都依附于第一个集合，如果仅仅将节点2指向节点1，那么后续判断时，原来节点2所在集合中其余的节点
                // 会被认为仍然不属于节点1所在集合，从而导致误判（实际上已经通过节点2联通起来了）
                map.put(city2Root, city1);
            }

            if(edges.size() >= n-1){
                break;
            }
        }

        if (edges.size() < n - 1) {
            return -1;
        }

        int sumCost = 0;
        for (int cost : edges) {
            sumCost += cost;
        }

        return sumCost;

    }
}
