package indi.simon.learning.leetcode.回溯组合_防重复;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Chen Zhuo on 2024/4/6.
 */
//todo: 不可重复的组合，分为2种：
// 1. 组合元素不可重复使用，组合内容不能重复
// 2. 组合元素不可重复使用，组合内容可以重复
// 两者的区别是，假如数组中有两个1和一个7，和一个6，那么前者的意思是之能有一个{1,7}，也允许有{1,1,6}，即两个1同在一个组合中
// 后者的意思是两个1可以分别用一次（但是仍然不能用多次），有2个{1,7}，后者稍微简单一些
// 复习的时候先看一下组合内容可以重复的写法
//
//
public class Quiz40组合元素不可重复使用_候选集有重复元素 {

    public static void main(String[] args) {
        Quiz40组合元素不可重复使用_候选集有重复元素 quiz40组合元素不可重复使用内容不可重复 = new Quiz40组合元素不可重复使用_候选集有重复元素();
        List<List<Integer>> res = quiz40组合元素不可重复使用内容不可重复.combinationSum2(new int[]{1, 1, 2, 6}, 8);
        System.out.println(res);
    }

    //todo 内容不可重复写法========================================
    //用于统计candidates中各个数出现的次数，int[0]是数值本身，int[1]是次数
    List<int[]> freq = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> sequence = new ArrayList<>();

    int target;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //先排序，这样freq数组里面的元素就是从小到大的排序了，这是为了后头方便剪枝，其实不排序也行，不剪枝只是会慢一些而已
        Arrays.sort(candidates);
        this.target = target;
        for (int num : candidates) {
            int size = freq.size();
            if (freq.isEmpty() || num != freq.get(size - 1)[0]) {
                freq.add(new int[]{num, 1});
            } else {
                ++freq.get(size - 1)[1];
            }
        }
        dfs(0, 0);
        return ans;
    }

    public void dfs(int i, int soFar) {
        if (soFar == target) {
            ans.add(new ArrayList<>(sequence));
            return;
        }

        //剪枝
        if (i == freq.size() || soFar + freq.get(i)[0] > target) {
            return;
        }

        //todo: 此处是关键，就是将元素i，加1次，加2次，加3次。。。。一并处理，其实类比内容可重复的写法，很像，
        // 这里也有选和不选的两种选择，只不过那边选就是加1次，没有多次而已，这里需要处理多于1次的情况，有个for循环

        //考虑元素i，考虑1次，考虑2次...考虑n次
        //计算freq[i]最多可以被添加几次，剩余可添加次数与库存数取小值
        int most = Math.min((target - soFar) / freq.get(i)[0], freq.get(i)[1]);
        for (int j = 1; j <= most; ++j) {
            sequence.add(freq.get(i)[0]);
            dfs(i + 1, soFar + j * freq.get(i)[0]);
        }
        for (int j = 1; j <= most; ++j) {
            sequence.remove(sequence.size() - 1);
        }

        //不考虑元素i
        dfs(i + 1, soFar);
    }

}
