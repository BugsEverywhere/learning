package indi.simon.learning.复习.组合相关;

import java.util.ArrayList;
import java.util.List;

public class Quiz78_无重复元素_元素不可重复使用_求所有子集 {

    public static void main(String[] args) {
        Quiz78_无重复元素_元素不可重复使用_求所有子集 quiz = new Quiz78_无重复元素_元素不可重复使用_求所有子集();
        List<List<Integer>> res = quiz.subsets(new int[]{1, 2, 3});
        System.out.println(res);
    }

    private List<List<Integer>> res;

    private List<Integer> path;

    int[] nums;

    public List<List<Integer>> subsets(int[] nums) {
        this.res = new ArrayList<>();
        this.path = new ArrayList<>();
        this.nums = nums;
        dfs(0, path);
        return res;
    }

    private void dfs(int i, List<Integer> path){
        //todo: 这里记住，一定要遍历到底才加入结果，不然会有重复
        if(i >= nums.length){
            res.add(new ArrayList<>(path));
        }

        if(i >= nums.length){
            return;
        }

        //选i
        path.add(nums[i]);
        dfs(i + 1, path);
        path.remove(path.size() - 1);

        //不选i
        dfs(i + 1, path);
    }


}
