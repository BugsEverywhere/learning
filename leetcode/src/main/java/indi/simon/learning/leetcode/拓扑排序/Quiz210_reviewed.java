package indi.simon.learning.leetcode.拓扑排序;


import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 有向图的拓扑排序
public class Quiz210_reviewed {

    public static void main(String[] args) {
        Quiz210_reviewed quiz210NeedReview = new Quiz210_reviewed();
        int[] res = quiz210NeedReview.findOrder(4, new int[][]{{1, 3}, {0, 2}, {2, 3}});
        System.out.println(res);
    }

    // 存储有向图
    List<List<Integer>> edges;
    // 标记每个节点的状态：0=未搜索，1=搜索中，2=已完成
    int[] visited;
    // 用数组来模拟栈，下标 n-1 为栈底，0 为栈顶
    int[] result;
    // 判断有向图中是否有环
    boolean noRing = true;
    // 栈下标
    int index;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //先把图初始化一下，一共numCourses个节点
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        //状态数组初始化
        visited = new int[numCourses];
        //存储结果的栈初始化
        result = new int[numCourses];
        index = numCourses - 1;
        //绘制有向图
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        // 每次挑选一个「未搜索」的节点，开始进行深度优先搜索
        for (int i = 0; i < numCourses && noRing; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        if (!noRing) {
            return new int[0];
        }
        // 如果没有环，那么就有拓扑排序
        return result;
    }

    public void dfs(int u) {
        // 将节点标记为「搜索中」
        visited[u] = 1;
        // 搜索其指向的相邻节点
        // 只要发现有环，立刻停止搜索
        for (int v : edges.get(u)) {
            if (visited[v] == 0) {
                // 如果「未搜索」那么搜索该相邻节点
                dfs(v);
                if (!noRing) {
                    return;
                }
            } else if (visited[v] == 1) {
                // 如果「搜索中」说明找到了环
                noRing = false;
                return;
            }
        }
        // 将节点标记为「已完成」
        visited[u] = 2;
        // 将节点入栈
        result[index--] = u;
    }

}
