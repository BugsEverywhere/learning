package indi.simon.learning.复习.组合相关;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz77无重复元素_元素不可重复使用_求组合_不同的终止条件 {

    public static void main(String[] args) {
        Quiz77无重复元素_元素不可重复使用_求组合_不同的终止条件 quiz77无重复元素元素不可重复使用求组合不同的终止条件 = new Quiz77无重复元素_元素不可重复使用_求组合_不同的终止条件();
        List<List<Integer>> res = quiz77无重复元素元素不可重复使用求组合不同的终止条件.combine(3, 2);
        System.out.println(res);
    }

    private List<List<Integer>> res;
    private List<Integer> path;
    private int n;
    private int k;

    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        this.n = n;
        this.k = k;
        dfs(1);
        return res;
    }

    private void dfs(int i) {
        if (path.size() == k) {
            //todo: 因为题意是k个数的组合，因此这里加入最终结果集之后，直接return即可，也不用考虑是否遍历到底了，跟78不一样
            res.add(new ArrayList<>(path));
            return;
        }

        if (i > n) {
            return;
        }

        //选它
        path.add(i);
        dfs(i + 1);
        path.remove(path.size() - 1);

        //不选它
        dfs(i + 1);
    }


}
