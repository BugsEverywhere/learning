package indi.simon.learning.leetcode.gogo20231022;

import java.util.*;

/**
 * Created by Chen Zhuo on 2023/10/22.
 *
 * 你有一个包含 n 个节点的图。给定一个整数 n 和一个数组 edges ，其中 edges[i] = [ai, bi] 表示图中 ai 和 bi 之间有一条边。
 *
 * 返回 图中已连接分量的数目 。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入: n = 5, edges = [[0, 1], [1, 2], [3, 4]]
 * 输出: 2
 * 示例 2:
 *
 *
 *
 * 输入: n = 5, edges = [[0,1], [1,2], [2,3], [3,4]]
 * 输出:  1
 *
 *
 * 提示：
 *
 * 1 <= n <= 2000
 * 1 <= edges.length <= 5000
 * edges[i].length == 2
 * 0 <= ai <= bi < n
 * ai != bi
 * edges 中不会出现重复的边
 */
//todo: 我愿称之为root坍缩法：使用数组来记录父节点，parents[i]代表i的父亲，
// 遍历每一条边，上溯parent找边的2个节点的各自root，2个root需要只保留一个，需要合并（即坍缩），
// 然后n自减（因为一开始可以认为有n个root），最终遍历完，剩下几个root就是有几个连通图
public class Quiz323 {

    public static void main(String[] args) {
        Quiz323 quiz323 = new Quiz323();
        int res = quiz323.countComponents(10, new int[][]{{5, 6}, {0, 2}, {1, 7}, {5, 9}, {1, 8}, {3, 4}, {0, 6}, {0, 7}, {0, 3}, {8, 9}});
        System.out.println(res);
    }

    public int countComponents(int n, int[][] edges) {
        int[] parents = new int[n];
        Arrays.fill(parents, -1);

        for (int[] edge : edges) {
            int root1 = find(parents, edge[0]);
            int root2 = find(parents, edge[1]);
            if (root1 != root2) {
                parents[root1] = root2;
                n--;
            }
        }
        return n;
    }

    private int find(int[] parents, int x) {
        int root = x;
        while (parents[root] != -1) {
            root = parents[root];
        }
        return root;
    }


}
