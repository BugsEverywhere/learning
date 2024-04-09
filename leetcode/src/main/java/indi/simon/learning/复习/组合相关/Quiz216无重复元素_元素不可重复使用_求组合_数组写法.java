package indi.simon.learning.复习.组合相关;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Zhuo on 2024/4/6.
 */
// todo: 求组合的问题，确实也可以写成for循环递归,这种解法放在下面，
//  两种递归求组合的方式的区别主要有：
//  1. for循环递归在每一轮递归中肯定肯定会添加一个元素，而【选/不选】递归有可能轮空，
//     所以前者递归的深度会小一些，后者递归深度大一些，通过更大的递归深度来覆盖所有可能情况。
//  2. for循环递归需要考虑递进方式，以及如何与递归终止条件相配合，比如，每一轮递归中，for循环从哪个下标开始？是i还是i+1？
//     如果是从i本身开始，那么递归终止条件是怎样的？会不会导致StackOverFlow。而【选/不选】递归则不用考虑这个问题，所以较为简单。
//  3. 因此，反正就记住，求组合问题，如果元素可重复使用，则使用for循环递归，每一轮for循环从i开始，如果是元素不可重复使用，则使用
//     【选/不选】递归，较为简单。
public class Quiz216无重复元素_元素不可重复使用_求组合_数组写法 {

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
