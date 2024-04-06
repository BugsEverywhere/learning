package indi.simon.learning.leetcode.回溯组合_防重复;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Zhuo on 2024/4/6.
 */
public class Quiz216数组写法 {

    //todo: 本题的数组写法，也就是假设候选集是一个数组
    private List<List<Integer>> res;
    private List<Integer> path;
    private int target;
    private int[] candidates;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        this.candidates = candidates;
        this.target = target;
        this.path = new ArrayList<>();
        this.res = new ArrayList<>();
        dfs(0, 0);
        return res;
    }

    private void dfs(int soFar, int i) {
        if (soFar == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        if (i >= candidates.length) {
            return;
        }

        if (soFar > target) {
            return;
        }

        //todo: 元素不可重复的话，这里相较于Quiz39的元素可重复，就不需要用for循环遍历了，对当前元素仅仅两种选择
        //选它
        path.add(candidates[i]);
        dfs(soFar + candidates[i], i + 1);
        path.remove(path.size() - 1);

        //不选它
        dfs(soFar, i + 1);
    }

    //=====================================================================================
    // todo: 确实也可以写成for循环递归,如下所示，但是应试的话就逼自己记住前面这种写法就行。这里列出来for循环递归的方法只是想表达
    //  两种递归求组合的方式并无本质区别，无非是前一种（选/不选）的方式更简单，写起来更不容易出错，而且结合元素有重复且不允许有相同组合的情况，
    //  这种写法更方便记忆。for循环递归的方式就专门用于写元素可重复使用的场景

//    private List<List<Integer>> res2;
//    private List<Integer> path2;
//    private int target2;
//    private int[] candidates2;
//
//    public List<List<Integer>> combinationSum2V2(int[] candidates, int target) {
//        this.candidates2 = candidates;
//        this.target2 = target;
//        this.path2 = new ArrayList<>();
//        this.res2 = new ArrayList<>();
//        //todo: 注意这里初始i从-1开始
//        dfs(0, -1);
//        return res;
//    }
//
//    private void dfs2(int soFar, int i) {
//        if (soFar == target) {
//            res.add(new ArrayList<>(path));
//            return;
//        }
//
//        if (i >= candidates.length) {
//            return;
//        }
//
//        if (soFar > target) {
//            return;
//        }
//
//        for (int j = i + 1; j < candidates.length; j++) {
//            path.add(candidates[j]);
//            dfs(soFar + candidates[j], j);
//            path.remove(path.size() - 1);
//        }
//
//    }
}
