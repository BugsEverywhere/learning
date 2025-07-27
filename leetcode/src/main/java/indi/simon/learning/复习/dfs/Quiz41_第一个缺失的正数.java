package indi.simon.learning.复习.dfs;

/**
 * @author chenzhuo(zhiyue)
 *
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 *
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 * 示例 1：
 *
 * 输入：nums = [1,2,0]
 * 输出：3
 * 解释：范围 [1,2] 中的数字都在数组中。
 * 示例 2：
 *
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * 解释：1 在数组中，但 2 没有。
 * 示例 3：
 *
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 * 解释：最小的正数 1 没有出现。
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 */
public class Quiz41_第一个缺失的正数 {

    public static void main(String[] args) {
        Quiz41_第一个缺失的正数 quiz41 = new Quiz41_第一个缺失的正数();
        int[] nums = new int[]{2, 1};
        int res = quiz41.firstMissingPositive(nums);
        System.out.println(res);
    }

    public int firstMissingPositive(int[] nums) {
        //todo: 只有一个元素的特殊情况，进来就判断一下
        if (nums.length == 1) {
            if (nums[0] == 1) {
                return 2;
            } else {
                return 1;
            }
        }

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                swapDfs(i, nums[i], nums);
            }
        }

        for (int i = 1; i < n; i++) {
            //todo: 只要i位置不是i，则这就是缺失的第一个正数
            if (nums[i] != i) {
                return i;
            }
        }

        //todo: 防止n被转移到nums[0]藏起来了，要判断一下0位置是否是n
        return nums[0] == n ? n + 1 : n;
    }

    /**
     * 递归将i位置的元素（正数），移动到nums[i]位置
     *
     * @param i
     * @param targetPos
     * @param nums
     */
    private void swapDfs(int i, int targetPos, int[] nums) {
        //todo: i和targetPos都有可能越界，要判断一下
        if (i >= nums.length || targetPos >= nums.length) {
            return;
        }
        int tmp = nums[i];
        nums[i] = nums[targetPos];
        nums[targetPos] = tmp;
        //todo:
        // 第一个条件确保nums[i]也是一个正数，不然没必要地柜下去。
        // 第二、三个条件为了防止stackOverFlow
        if (nums[i] > 0 && i != targetPos && nums[i] != nums[targetPos]) {
            swapDfs(i, nums[i], nums);
        }
    }
}
