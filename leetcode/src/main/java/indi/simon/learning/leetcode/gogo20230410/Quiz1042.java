package indi.simon.learning.leetcode.gogo20230410;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1042 {

    public static void main(String[] args) {
        Quiz1042 quiz1042 = new Quiz1042();
        int[] res = quiz1042.gardenNoAdj(10000, new int[][]{{1, 2}});
        System.out.println(res);
    }

    public int[] gardenNoAdj(int n, int[][] paths) {
        if (paths.length == 0) {
            int[] res = new int[n];
            Arrays.fill(res, 1);
            return res;
        }
        Map<Integer, List<Integer>> neighborMap = new HashMap<>();
        for (int[] path : paths) {
            List<Integer> joints0 = neighborMap.computeIfAbsent(path[0], k -> new ArrayList<>());
            List<Integer> joints1 = neighborMap.computeIfAbsent(path[1], k -> new ArrayList<>());
            joints0.add(path[1]);
            joints1.add(path[0]);
        }

        int[] res = new int[n];
        for (int i = 1; i <= n; i++) {
            dfs(i, neighborMap, res, new HashSet<>());
        }
        return res;
    }

    private void dfs(int curr, Map<Integer, List<Integer>> neighborMap, int[] answer, Set<Integer> path) {
        if (path.contains(curr) || answer[curr - 1] != 0) {
            return;
        }
        //得到可种的花
        Queue<Integer> availableFlowers = new ArrayDeque<>();
        availableFlowers.add(1);
        availableFlowers.add(2);
        availableFlowers.add(3);
        availableFlowers.add(4);
        List<Integer> neighbors = neighborMap.get(curr);
        if (neighbors == null) {
            //没有邻居，随便种一个
            answer[curr - 1] = 1;
            return;
        }

        for (int neighbor : neighbors) {
            availableFlowers.remove(answer[neighbor - 1]);
        }
        if (availableFlowers.size() == 0) {
            //never touch here
            return;
        }
        //先安排他自己
        answer[curr - 1] = availableFlowers.poll();
        //再安排他邻居们
        path.add(curr);
        for (int neighbor : neighbors) {
            dfs(neighbor, neighborMap, answer, path);
        }
        path.remove(curr);
    }


}
