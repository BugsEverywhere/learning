package indi.simon.learning.leetcode.组合相关;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Chen Zhuo on 2024/4/6.
 */
//todo: 对比Quiz216的无重复元素，其实很像，在递归的时候对于某一个元素也是考虑选它和不选它两种情况。
// 唯一的区别就是，此时选它的情况要分出选1个、选2个，选3个......，所以一个单纯的if变成了一个for循环.
// 这里要注意几个地方:
// 1. 因为有重复元素，所以按照上面的思路，重复元素需要归纳到一起，相同元素在递归过程中一并考虑（用1次、2次、3次...）
//    所以需要构造一个freq二维数组，freq[i][0]为元素本身，freq[i][1]为元素出现次数，此时，相当于使用freq替代了
//    nums数组，因此在递归过程中也就不是拿nums递归了，而是拿freq递归
// 2. 在构造freq时，因为要将不同重复元素聚拢到一起，所以必须对nums排序，这样构造出来的freq在第一个维度也是有序的，
//    这样做的好处是，后续可以剪枝，一旦发现soFar+freq[i][0]比target都大了，可以直接返回，后面的组合不用递归了
// 3. 之所以一定要用freq二维数组，不使用HashMap来记录出现次数是因为数组方便递归，Map毕竟是一个无序的集合，
//    而且使用数组也更加节省空间
public class Quiz40有重复元素_元素不可重复使用_求组合_难 {

    public static void main(String[] args) {
        Quiz40有重复元素_元素不可重复使用_求组合_难 quiz40组合元素不可重复使用内容不可重复 = new Quiz40有重复元素_元素不可重复使用_求组合_难();
        List<List<Integer>> res = quiz40组合元素不可重复使用内容不可重复.combinationSum2(new int[]{1, 1, 2, 6}, 8);
        System.out.println(res);
    }

    //todo: 用于统计candidates中各个数出现的次数，int[0]是数值本身，int[1]是次数
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
        // 考虑元素i，考虑1次，考虑2次...考虑n次
        // 计算freq[i]最多可以被添加几次，剩余可添加次数与库存数取小值
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
