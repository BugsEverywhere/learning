package indi.simon.learning.leetcode.gogo20221226;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz207 {

    public static void main(String[] args) {
        Quiz207 quiz207 = new Quiz207();
        boolean res = quiz207.canFinish(2, new int[][]{{1, 0}, {0, 1}});
        System.out.println(res);
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> preCourseMap = new HashMap<>();

        for (int[] pair : prerequisites) {
            if (preCourseMap.containsKey(pair[0])) {
                preCourseMap.get(pair[0]).add(pair[1]);
            } else {
                List<Integer> preCourseList = new ArrayList<>();
                preCourseList.add(pair[1]);
                preCourseMap.put(pair[0], preCourseList);
            }
        }

        for (int i = 0; i < numCourses; i++) {
            if (!preCourseMap.containsKey(i)) {
                continue;
            }
            if (!dfs(preCourseMap, new HashSet<>(), i)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(Map<Integer, List<Integer>> preCourseMap, Set<Integer> path, int i) {
        if (path.contains(i)) {
            return false;
        }
        path.add(i);
        List<Integer> pre = preCourseMap.get(i);
        if (pre == null) {
            return true;
        }
        preCourseMap.remove(i);
        for (Integer j : pre) {
            if (!dfs(preCourseMap, new HashSet<>(path), j)) {
                return false;
            }
        }
        return true;
    }


}
