package indi.simon.learning.leetcode.排列相关;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 相较于求无重复元素的全排列，此题添加了vis辅助数组用来标记数组元素num在排列中是否使用过，而且需要对nums进行排序将
// 重复元素聚拢，这样在每一层递归的for循环中，重复元素都聚在一起。然后标记数组的作用是，每一轮使用该数未被访问过的第一个来填充，
// 举例，某一轮递归中拿到的nums如下所示：1(使用过),1(使用过),1(未使用),2(使用过),2(未使用),2(未使用),2(未使用)，
// 那么在本轮中，for循环到第3个1以及第2个2时，都可以将其纳入排列并往下递归，其余情况都直接continue
public class Quiz47有重复元素_元素不可重复使用_求全排列_难 {

    public static void main(String[] args) {
        Quiz47有重复元素_元素不可重复使用_求全排列_难 quiz47有重复元素求全排列 = new Quiz47有重复元素_元素不可重复使用_求全排列_难();
        int[] nums = new int[]{1, 1, 2};
        List<List<Integer>> res = quiz47有重复元素求全排列.permuteUnique(nums);
        System.out.println(res);
    }

    //todo:记录数组中元素i是否被访问过
    boolean[] vis;

    List<List<Integer>> res = new ArrayList<>();

    List<Integer> path = new ArrayList<>();

    int[] nums;

    public List<List<Integer>> permuteUnique(int[] nums) {
        vis = new boolean[nums.length];
        Arrays.sort(nums);
        this.nums = nums;
        dfs();
        return res;
    }

    public void dfs() {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            path.add(nums[i]);
            vis[i] = true;
            dfs();
            vis[i] = false;
            path.remove(path.size() - 1);
        }
    }

}
