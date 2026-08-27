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

        //val为key课程的后续课程
        Map<Integer, List<Integer>> afterMap = new HashMap<>();

        for (int[] prerequisite : prerequisites) {
            int first = prerequisite[1];
            int after = prerequisite[0];

            List<Integer> afterList = afterMap.getOrDefault(first, new ArrayList<>());
            afterList.add(after);
            afterMap.put(first, afterList);
        }

        for (int i = 1; i <= numCourses; i++) {
            if (!dfs(afterMap, new HashSet<>(), i)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(Map<Integer, List<Integer>> afterMap, Set<Integer> path, int i) {
        //有环
        if (path.contains(i)) {
            return false;
        }

        //已经不存在i的后续课程，说明之前遍历过i，或者本来i就没有后继课程，直接返回
        if (!afterMap.containsKey(i)) {
            return true;
        }

        //取出i的所有后继课程
        List<Integer> afterList = afterMap.get(i);

        //剪枝，可以将i的后继课程都去除了，因为本次递归如果没问题，后面再到i也不会出问题
        afterMap.remove(i);

        for (Integer after : afterList) {
            Set<Integer> newPath = new HashSet<>(path);
            newPath.add(i);
            if (!dfs(afterMap, newPath, after)) {
                return false;
            }
        }
        return true;

    }


}
