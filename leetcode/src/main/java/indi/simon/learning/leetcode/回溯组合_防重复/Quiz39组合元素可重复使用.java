package indi.simon.learning.leetcode.回溯组合_防重复;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Zhuo on 2024/4/6.
 */
//todo: 由于元素可重复使用，相当于数组中的每一个数都有无限个，因此一般这种题会强调数组中元素本来就不重复，
// 不然也不用给出可重复使用的条件。如果没有强调这一点，也就是数组中可能有重复元素，或者面试的时候对方模棱两可，
// 那么记得将数组处理成无重复元素的样子，再做后续的计算
public class Quiz39组合元素可重复使用 {

    public static void main(String[] args) {
        Quiz39组合元素可重复使用 quiz39组合元素可重复使用内容不可重复 = new Quiz39组合元素可重复使用();
        List<List<Integer>> res = quiz39组合元素可重复使用内容不可重复.combinationSum(new int[]{1, 1, 2, 6}, 2);
        System.out.println(res);
    }

    private List<List<Integer>> res;

    private List<Integer> path;

    private int[] candidates;

    private int target;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        this.candidates = candidates;
        this.target = target;
        dfs(0, 0);
        return res;
    }

    private void dfs(int soFar, int i) {
        if (soFar == target) {
            //todo: 注意要新new一个List，不能直接添加path
            res.add(new ArrayList<>(path));
            return;
        }

        if (i >= candidates.length || soFar > target) {
            return;
        }

        //todo: 因为元素可无限重复，所以这里要用for循环遍历，从自身开始。
        // 不然的话，如果携写成（选/不选）的模式，不方便往前递进
        for (int j = i; j < candidates.length; j++) {
            //todo: path加元素，结束了扫尾，注意不要加错了！是加j不是i
            path.add(candidates[j]);
            dfs(soFar + candidates[j], j);
            path.remove(path.size() - 1);
        }

    }


}
