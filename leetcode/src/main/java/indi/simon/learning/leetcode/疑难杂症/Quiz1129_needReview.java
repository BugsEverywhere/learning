package indi.simon.learning.leetcode.疑难杂症;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1129_needReview {

    public static void main(String[] args) {
        Quiz1129_needReview quiz1129NeedReview = new Quiz1129_needReview();
        int[] res = quiz1129NeedReview.shortestAlternatingPathsOfficial(
                9,
                new int[][]{{2, 1}, {5, 1}, {6, 4}, {1, 0}, {7, 4}, {0, 8}, {7, 8}, {7, 6}, {6, 8}, {3, 1}, {2, 7}, {3, 6}, {8, 3}, {0, 0}, {5, 0}, {8, 1}, {4, 8}, {4, 7}, {8, 0}, {8, 5}},
                new int[][]{{1, 5}, {2, 7}, {2, 0}, {5, 2}, {8, 5}, {1, 7}, {6, 1}, {1, 4}, {4, 1}, {3, 6}, {8, 8}, {7, 6}, {1, 1}, {6, 8}, {2, 8}, {7, 7}, {7, 3}, {1, 2}, {2, 6}});
        System.out.println(res);
    }

    //todo: 官方bfs
    public int[] shortestAlternatingPathsOfficial(int n, int[][] redEdges, int[][] blueEdges) {

        List<Integer>[][] next = new List[2][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                next[i][j] = new ArrayList<>();
            }
        }

        for (int[] edge : redEdges) {
            next[0][edge[0]].add(edge[1]);
        }

        for (int[] edge : blueEdges) {
            next[1][edge[0]].add(edge[1]);
        }

        int[][] dist = new int[2][n]; // 两种类型的颜色最短路径的长度
        for (int i = 0; i < 2; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        Queue<int[]> queue = new ArrayDeque<int[]>();
        dist[0][0] = 0;
        dist[1][0] = 0;
        queue.offer(new int[]{0, 0});
        queue.offer(new int[]{0, 1});
        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int x = pair[0], t = pair[1];
            for (int y : next[1 - t][x]) {
                if (dist[1 - t][y] != Integer.MAX_VALUE) {
                    continue;
                }
                dist[1 - t][y] = dist[t][x] + 1;
                queue.offer(new int[]{y, 1 - t});
            }
        }
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = Math.min(dist[0][i], dist[1][i]);
            if (answer[i] == Integer.MAX_VALUE) {
                answer[i] = -1;
            }
        }
        return answer;
    }


    //todo: 自己的答案，超时
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {

        Map<Integer, List<Integer>> redEdgeMap = new HashMap<>();
        Map<Integer, List<Integer>> blueEdgeMap = new HashMap<>();

        for (int[] redEdge : redEdges) {
            List<Integer> edges = redEdgeMap.getOrDefault(redEdge[0], new ArrayList<>());
            edges.add(redEdge[1]);
            redEdgeMap.put(redEdge[0], edges);
        }

        for (int[] blueEdge : blueEdges) {
            List<Integer> edges = blueEdgeMap.getOrDefault(blueEdge[0], new ArrayList<>());
            edges.add(blueEdge[1]);
            blueEdgeMap.put(blueEdge[0], edges);
        }

        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                res[i] = 0;
                continue;
            }

            res[i] = Integer.MAX_VALUE;
            //先考察从0开始的红色边出发
            List<Integer> edges = redEdgeMap.get(0);
            if (edges != null) {
                for (int node : edges) {
                    Boolean[] redMem = new Boolean[n];
                    redMem[0] = true;
                    int nodeRes = dfs(redEdgeMap, blueEdgeMap, false, 1, node, i, redMem, new Boolean[n]);
                    if (nodeRes == -1) {
                        continue;
                    }
                    res[i] = Math.min(res[i], nodeRes);
                }
            }

            //再考察从0开始的蓝色边出发
            edges = blueEdgeMap.get(0);
            if (edges != null) {
                for (int node : edges) {
                    Boolean[] blueMem = new Boolean[n];
                    blueMem[0] = false;
                    int nodeRes = dfs(redEdgeMap, blueEdgeMap, true, 1, node, i, new Boolean[n], blueMem);
                    if (nodeRes == -1) {
                        continue;
                    }
                    res[i] = Math.min(res[i], nodeRes);
                }
            }

            if (res[i] == Integer.MAX_VALUE) {
                res[i] = -1;
            }
        }
        return res;
    }

    private int dfs(Map<Integer, List<Integer>> redEdgeMap, Map<Integer, List<Integer>> blueEdgeMap, boolean red, int stepSoFar, int start, int target, Boolean[] redMem, Boolean[] blueMem) {
        if (start == target) {
            return stepSoFar;
        } else if ((redMem[start] != null && (redMem[start] && red)) || (blueMem[start] != null && (blueMem[start] && !red))) {
            //来过了
            return -1;
        }
        if (red) {
            redMem[start] = true;
        } else {
            blueMem[start] = true;
        }

        int res = Integer.MAX_VALUE;
        if (red) {
            List<Integer> redEdges = redEdgeMap.get(start);
            if (redEdges == null) {
                redMem[start] = false;
                return -1;
            }
            for (int node : redEdges) {
                int originRes = dfs(redEdgeMap, blueEdgeMap, false, stepSoFar + 1, node, target, redMem, blueMem);
                if (originRes == -1) {
                    continue;
                }
                res = Math.min(originRes, res);
            }
        } else {
            List<Integer> blueEdges = blueEdgeMap.get(start);
            if (blueEdges == null) {
                //去除脚印
                blueMem[start] = false;
                return -1;
            }
            for (int node : blueEdges) {
                int originRes = dfs(redEdgeMap, blueEdgeMap, true, stepSoFar + 1, node, target, redMem, blueMem);
                if (originRes == -1) {
                    continue;
                }
                res = Math.min(originRes, res);
            }
        }

        //去除脚印
        if (red) {
            redMem[start] = false;
        } else {
            blueMem[start] = false;
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }


}
