package indi.simon.learning.leetcode.gogo20231022;

import java.util.*;

/**
 * Created by Chen Zhuo on 2023/10/22.
 */
//todo: 使用数组来记录root的方式
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
