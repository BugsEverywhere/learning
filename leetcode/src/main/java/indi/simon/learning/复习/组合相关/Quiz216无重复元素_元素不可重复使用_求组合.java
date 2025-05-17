package indi.simon.learning.复习.组合相关;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Zhuo on 2024/4/6.
 */
public class Quiz216无重复元素_元素不可重复使用_求组合 {

    public static void main(String[] args) {

    }

    private int target;

    private List<Integer> path;

    private List<List<Integer>> res;

    public List<List<Integer>> combinationSum3(int k, int n) {
        target = n;
        path = new ArrayList<>();
        res = new ArrayList<>();
        dfs(1, 0, k);
        return res;
    }

    private void dfs(int i, int soFar, int k) {
        if (soFar == target && path.size() == k) {
            //todo: 同理，因为题意是k个数的组合，因此这里加入最终结果集之后，直接return即可，也不用考虑是否遍历到底了，跟78不一样
            res.add(new ArrayList<>(path));
            return;
        }

        if (i >= 10) {
            return;
        }

        if (soFar > target) {
            return;
        }

        if(path.size() > k){
            return;
        }

        //选择i
        path.add(i);
        dfs(i + 1, soFar + i, k);
        path.remove(path.size() - 1);

        //不选择i
        dfs(i + 1, soFar, k);

    }




}
