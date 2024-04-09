package indi.simon.learning.复习.组合相关;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Chen Zhuo on 2024/4/6.
 */
//todo: 对比Quiz216的无重复元素，其实很像，在递归的时候对于某一个元素也是考虑选它和不选它两种情况。
// 唯一的区别就是，这里要考虑如何去重，那我这里结合Quiz491得到的方法，可以广泛用于有重复元素求组合的场景，技巧如下：
//   1. 递归到i越界才判断path是否要添加，这是因为如果不这么做，会导致去重失败，比如序列1,2,2,2,5，求target==5的组合，
//      如果不递归到越界才判断的话，下标0,1,2就会被当成一个解{1,2,2}放进去，而最终我们希望的{1,2,2}是下标0,2,3构成的
//   2. 为什么是下标0,2,3，来构成这个唯一的{1,2,2}的解，首先，咱们肯定需要一个{1,2,2}的解，nums中一个三个2，我们仅需2个，
//      如果我们限定，需要两个2的时候只取最后两个2的分支，也就是仅考虑第一个2被跳过外加后面两个2被选择的分支，那么就限定了只有这一种
//      双2的情况被最终考虑进来，相当于就是剪枝，被剪掉的情况是第一个2被选择，第二个2跳过，第三个2被选择，以及前两个2被选择，第三个2
//      被跳过的情况。可以把“跳过”（也就是不选择）当成一种特权，我们在此处限定了只有当path之前没有选择过重复元素x的情况下，遇到的第一个
//      x才有权选择跳过，一旦path中加入了x(走到了x被选择的分支)，后续x就无权跳过了。
//      这样就把情况限定成了如下的情况：
//      1）仅包含1个x的组合，这个x一定是来自nums中最后一个x，走到这一步表示他之前的x都递归的“不选择”这条分支
//      2）仅包含2个x的组合，这2个x一定是来自nums中最后两个x，走到这一步表示他俩之前的x都递归的“不选择”这条分支
//      3）。。。。。。
//      在递归过程中，假如走到了中间某个x被选择的分支，那么后续的所有x都只有走被选择分支这条路，才会被最终考虑进来，
//      其他情况（不选择）均被剪枝了
// 记住：
//     1. 递归到底才判断path合不合格
//     2. “不选择”特权仅针对path中首次出现的x
public class Quiz40有重复元素_元素不可重复使用_求组合_难 {

    public static void main(String[] args) {
        Quiz40有重复元素_元素不可重复使用_求组合_难 quiz40组合元素不可重复使用内容不可重复 = new Quiz40有重复元素_元素不可重复使用_求组合_难();
        List<List<Integer>> res = quiz40组合元素不可重复使用内容不可重复.combinationSum2(new int[]{2,5,2,1,2}, 5);
        System.out.println(res);
    }

    List<Integer> path = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();
    private int[] nums;
    private int target;

    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        this.nums = nums;
        this.target = target;
        dfs(0, 0);
        return ans;
    }

    public void dfs(int i, int soFar) {
        if (i == nums.length) {
            //todo: 一定要递归到i越界才判断path是否要添加，因为只有到底才知道所有重复元素是否都考虑完了
            if (soFar == target) {
                ans.add(new ArrayList<>(path));
                return;
            }
            return;
        }
        if (soFar > target) {
            return;
        }
        //选择i
        path.add(nums[i]);
        dfs(i + 1, soFar + nums[i]);
        path.remove(path.size() - 1);

        //不选择i
        //todo: 不选择（跳过）逻辑仅对靠前的重复元素生效，这里的contains判断是复杂度比较高的判断
        if (path.isEmpty() || !path.contains(nums[i])) {
            dfs(i + 1, soFar);
        }
    }

//todo: 优化的做法是，对比Quiz216的无重复元素很像的解法，会快一些，但是略复杂，在递归的时候对于某一个元素也是考虑选它和不选它两种情况。
// 唯一的区别就是，此时选它的情况要分出选1个、选2个，选3个......，所以一个单纯的if变成了一个for循环.
// 这里要注意几个地方:
// 1. 因为有重复元素，所以按照上面的思路，重复元素需要归纳到一起，相同元素在递归过程中一并考虑（用1次、2次、3次...）
//    所以需要构造一个freq二维数组，freq[i][0]为元素本身，freq[i][1]为元素出现次数，此时，相当于使用freq替代了
//    nums数组，因此在递归过程中也就不是拿nums递归了，而是拿freq递归
// 2. 在构造freq时，因为要将不同重复元素聚拢到一起，所以必须对nums排序，这样构造出来的freq在第一个维度也是有序的，
//    这样做的好处是，后续可以剪枝，一旦发现soFar+freq[i][0]比target都大了，可以直接返回，后面的组合不用递归了
// 3. 之所以一定要用freq二维数组，不使用HashMap来记录出现次数是因为数组方便递归，Map毕竟是一个无序的集合，
//    而且使用数组也更加节省空间

    //todo: 用于统计candidates中各个数出现的次数，int[0]是数值本身，int[1]是次数
    List<int[]> freq = new ArrayList<>();
    List<List<Integer>> ans2 = new ArrayList<>();
    List<Integer> path2 = new ArrayList<>();

    int target2;

    public List<List<Integer>> combinationSum2V2(int[] candidates, int target) {
        //先排序，这样freq数组里面的元素就是从小到大的排序了，这是为了后头方便剪枝，其实不排序也行，不剪枝只是会慢一些而已
        Arrays.sort(candidates);
        this.target2 = target;
        for (int num : candidates) {
            int size = freq.size();
            if (freq.isEmpty() || num != freq.get(size - 1)[0]) {
                freq.add(new int[]{num, 1});
            } else {
                ++freq.get(size - 1)[1];
            }
        }
        dfs2(0, 0);
        return ans2;
    }

    public void dfs2(int i, int soFar) {
        if (soFar == target2) {
            ans2.add(new ArrayList<>(path2));
            return;
        }

        //剪枝
        if (i == freq.size() || soFar + freq.get(i)[0] > target2) {
            return;
        }

        //todo: 此处是关键，就是将元素i，加1次，加2次，加3次。。。。一并处理，其实类比内容可重复的写法，很像，
        // 这里也有选和不选的两种选择，只不过那边选就是加1次，没有多次而已，这里需要处理多于1次的情况，有个for循环
        // 考虑元素i，考虑1次，考虑2次...考虑n次
        // 计算freq[i]最多可以被添加几次，剩余可添加次数与库存数取小值
        int most = Math.min((target2 - soFar) / freq.get(i)[0], freq.get(i)[1]);
        for (int j = 1; j <= most; ++j) {
            path2.add(freq.get(i)[0]);
            dfs2(i + 1, soFar + j * freq.get(i)[0]);
        }
        for (int j = 1; j <= most; ++j) {
            path2.remove(path2.size() - 1);
        }

        //不考虑元素i
        dfs2(i + 1, soFar);
    }



}
