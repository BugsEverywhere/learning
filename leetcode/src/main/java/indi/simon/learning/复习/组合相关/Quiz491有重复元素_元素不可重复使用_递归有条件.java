package indi.simon.learning.复习.组合相关;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Zhuo on 2024/4/5.
 */
//todo: 这道题相较于传统的求组合的题有几个不一样的地方：
//  1. 其要求的是满足某个条件的子序列，那么这就决定了不能对原数组排序，以前用来对付重复元素的魔法失效了
//  2. 在递归考虑选择和不选择数组中的元素时，都带上了限制条件，选择的限制条件是，他必须大于等于path中最后一个元素，
//     而又因为nums中存在重复元素，所以在不改变nums元素顺序的前提下，只能通过控制递归条件来做到去重，这里的做法是，
//     控制不选择的递归，也就是说，如果有多个重复元素，那么假如该重复元素之前被选择过（由于这里的path是保证有序的，
//     所以只需要判断path中最后一个元素是否是该重复元素即可），那么就不考虑本重复元素的“不选择”分支，也就是说，
//     假如是4,6,7,7这个nums，第一个7(不选择)和第一个7(选择)两个分支都考虑的情况下，省去了第二个7(不选择)的分支，
//     因为这是重复的分支.
// 记住：
//     1. 递归到底才判断path合不合格
//     2. “不选择”特权仅针对path中首次出现的x
public class Quiz491有重复元素_元素不可重复使用_递归有条件 {

    public static void main(String[] args) {
        Quiz491有重复元素_元素不可重复使用_递归有条件 quiz491Reviewed = new Quiz491有重复元素_元素不可重复使用_递归有条件();
        List<List<Integer>> res = quiz491Reviewed.findSubsequences(new int[]{6, 7, 7, 7});
        System.out.println(res);
    }

    List<Integer> path = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();
    private int[] nums;

    public List<List<Integer>> findSubsequences(int[] nums) {
        this.nums = nums;
        dfs(0);
        return ans;
    }

    public void dfs(int i) {
        if (i == nums.length) {
            //todo: 递归到底才判断是否加入答案
            if (path.size() >= 2) {
                ans.add(new ArrayList<>(path));
            }
            return;
        }
        //考虑i
        if (path.isEmpty() || nums[i] >= path.get(path.size() - 1)) {
            path.add(nums[i]);
            dfs(i + 1);
            path.remove(path.size() - 1);
        }
        //跳过i
        //todo: “不选择”特权仅针对path中首次出现的x，如果path之前加入过x，那本x不跳过
        if (path.isEmpty() || nums[i] != path.get(path.size() - 1)) {
            dfs(i + 1);
        }
    }

}
