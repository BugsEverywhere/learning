package indi.simon.learning.复习.排列相关;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
//todo: 排列问题相对于组合问题来说，一个特点是，每一个答案的长度是固定的，都是n==nums.length，
// 我们想象有一个长度为n的数组，我们需要使用nums中的元素对其进行填充，那么递归的用处就是每递归一层
// 往数组尾部填充一个数，直到填充完整个数组，将结果记录下来。那么问题就在于每一层递归中如何确定需要填充的数。
// 由于本题是无重复元素，那么在每一层直接for循环遍历所有数，注意是从头遍历所有数，简单判断该数在之前是否添加过即可。
// 技巧：
// 1. 排列问题！for循环递归，每一轮从0开始for。
public class Quiz46无重复元素_元素不可重复使用_求全排列 {

    public static void main(String[] args) {
        int[] test = new int[]{1, 2, 3};
        Quiz46无重复元素_元素不可重复使用_求全排列 quiz = new Quiz46无重复元素_元素不可重复使用_求全排列();
        List<List<Integer>> result = quiz.permute(test);
        System.out.println(result);
    }

    private List<List<Integer>> res;

    private List<Integer> path;

    private int[] nums;

    private int n;

    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        n = nums.length;
        this.nums = nums;
        dfs();
        return res;
    }

    private void dfs() {
        if (path.size() == n) {
            res.add(new ArrayList<>(path));
            return;
        }

        //todo: 每一层递归从0开始for
        for (int num : nums) {
            if(path.contains(num)){
                continue;
            }
            path.add(num);
            dfs();
            path.remove(path.size() - 1);
        }
    }

}