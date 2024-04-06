package indi.simon.learning.leetcode.回溯组合_防重复;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Zhuo on 2024/4/5.
 */
//todo: 递归求组合的问题中，
public class Quiz491_reviewed {

    public static void main(String[] args) {
        Quiz491_reviewed quiz491Reviewed = new Quiz491_reviewed();
        List<List<Integer>> res = quiz491Reviewed.findSubsequences(new int[]{4, 6, 7, 7});
        System.out.println(res);
    }

    List<Integer> path = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        dfs(0, nums);
        return ans;
    }

    public void dfs(int cur, int[] nums) {
        if (cur == nums.length) {
            if (path.size() >= 2) {
                ans.add(new ArrayList<>(path));
            }
            return;
        }
        //考虑cur
        if (path.isEmpty() || nums[cur] >= path.get(path.size() - 1)) {
            path.add(nums[cur]);
            dfs(cur + 1, nums);
            path.remove(path.size() - 1);
        }
        //跳过cur
        if (path.isEmpty() || nums[cur] != path.get(path.size() - 1)) {
            dfs(cur + 1, nums);
        }
    }

}
