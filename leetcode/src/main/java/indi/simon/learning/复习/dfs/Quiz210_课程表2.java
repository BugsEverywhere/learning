package indi.simon.learning.复习.dfs;


import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 * <p>
 * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
 * <p>
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：[0,1]
 * 解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * 示例 2：
 * <p>
 * 输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * 输出：[0,2,1,3]
 * 解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 * 因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * 示例 3：
 * <p>
 * 输入：numCourses = 1, prerequisites = []
 * 输出：[0]
 * 提示：
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * 所有[ai, bi] 互不相同
 */
//todo: 有向图的拓扑排序
public class Quiz210_课程表2 {

    public static void main(String[] args) {
        Quiz210_课程表2 quiz210NeedReview = new Quiz210_课程表2();
        int[] res = quiz210NeedReview.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        System.out.println(Arrays.toString(res));
    }

    // 存储有向图，即每一个课程的所有后置课程
    private Map<Integer, List<Integer>> after;
    // 用于保存合法课程的栈，栈底（最先入栈的）是最后学习的课程，栈顶是先学习的课程
    private Stack<Integer> stack;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //邻接表
        //key：课程
        //val：该课程的所有后置课程
        after = new HashMap<>();
        for (int[] info : prerequisites) {
            List<Integer> afterCourses = after.getOrDefault(info[1], new ArrayList<>());
            afterCourses.add(info[0]);
            after.put(info[1], afterCourses);
        }
        //存储最终结果的栈初始化
        stack = new Stack<>();
        for (int i = 0; i < numCourses; ++i) {
            // 如果课程i已经搜索过，则跳过
            if (stack.contains(i)) {
                continue;
            }
            List<Integer> path = new ArrayList<>();
            path.add(i);
            boolean courseRes = dfs(i, path);
            path.remove(path.size() - 1);
            if (!courseRes) {
                return new int[0];
            }
        }
        // todo: 此处注意for循环遍历java栈时，是从栈底遍历，因此此处要反过来，因为栈顶元素是要最先开始学习的
        int[] resArr = new int[numCourses];
        for (int i = 0; i < stack.size(); i++) {
            resArr[i] = stack.get(stack.size() - 1 - i);
        }
        return resArr;
    }

    /**
     * 递归搜索每一个课程指向的后置课程
     */
    public boolean dfs(int u, List<Integer> path) {
        // 遍历所有后置课程，只要发现有环，立刻停止搜索
        List<Integer> afterCourses = after.get(u);
        if (afterCourses == null) {
            //当前课程没有后继课程，则直接将课程入栈，返回
            stack.push(u);
            return true;
        }
        //遍历递归所有后继课程
        for (int v : afterCourses) {
            //如果该后置课程出现在本次搜索路径中，说明也是本课程的前置课程，说明有环，走不通
            if (path.contains(v)) {
                return false;
            }
            //如果该后置课程已经入栈过（位于更靠栈底的位置），后续肯定会学习它，则可直接跳过，相当于剪枝
            if (stack.contains(v)) {
                continue;
            }
            path.add(v);
            boolean courseRes = dfs(v, path);
            path.remove(path.size() - 1);
            if (!courseRes) {
                return false;
            }
        }
        //遍历完后继课程，将本课程入栈
        stack.push(u);
        return true;
    }

}
