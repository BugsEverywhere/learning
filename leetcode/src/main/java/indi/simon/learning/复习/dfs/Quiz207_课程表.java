package indi.simon.learning.复习.dfs;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 *
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 *
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程 bi 。
 *
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 *
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * 示例 2：
 *
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 * 提示：
 *
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 *
 */
public class Quiz207_课程表 {

    public static void main(String[] args) {
        Quiz207_课程表 quiz207课程表 = new Quiz207_课程表();
        boolean res = quiz207课程表.canFinish(2, new int[][]{{1, 0}, {0, 1}});
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
