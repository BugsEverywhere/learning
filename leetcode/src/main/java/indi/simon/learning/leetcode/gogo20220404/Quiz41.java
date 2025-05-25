package indi.simon.learning.leetcode.gogo20220404;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz41 {

    public static void main(String[] args) {
        Quiz41 quiz41 = new Quiz41();
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
