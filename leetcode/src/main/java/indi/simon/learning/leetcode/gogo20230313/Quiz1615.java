package indi.simon.learning.leetcode.gogo20230313;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1615 {

    public static void main(String[] args) {
        Quiz1615 quiz1615 = new Quiz1615();
        int res = quiz1615.maximalNetworkRank(6, new int[][]{{2, 4}});
        System.out.println(res);
    }

    private int maxZhi;
    private boolean[][] mem;

    public int maximalNetworkRank(int n, int[][] roads) {
        maxZhi = 0;
        mem = new boolean[n][n];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] rode : roads) {
            if (graph.containsKey(rode[0])) {
                graph.get(rode[0]).add(rode[1]);
            } else {
                List<Integer> rodesOfThisNode = new ArrayList<>();
                rodesOfThisNode.add(rode[1]);
                graph.put(rode[0], rodesOfThisNode);
            }

            if (graph.containsKey(rode[1])) {
                graph.get(rode[1]).add(rode[0]);
            } else {
                List<Integer> rodesOfThisNode = new ArrayList<>();
                rodesOfThisNode.add(rode[0]);
                graph.put(rode[1], rodesOfThisNode);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i || mem[i][j] || mem[j][i]) {
                    continue;
                }
                int zhiI;
                int zhiJ;
                //todo: 注意要判空
                if (graph.get(i) == null) {
                    zhiI = 0;
                } else {
                    zhiI = graph.get(i).size();
                }
                //todo: 注意要判空
                if (graph.get(j) == null) {
                    zhiJ = 0;
                } else {
                    zhiJ = graph.get(j).size();
                }

                int zhi;
                if (graph.get(i) != null && graph.get(i).contains(j)) {
                    zhi = zhiI - 1 + zhiJ;
                } else {
                    zhi = zhiI + zhiJ;
                }
                maxZhi = Math.max(maxZhi, zhi);
                mem[i][j] = true;
                mem[j][i] = true;
            }
        }

        return maxZhi;
    }

}
