package indi.simon.learning.leetcode.gogo20220502;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz198 {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 9, 3, 1};
        Quiz198 quiz198 = new Quiz198();
        int res = quiz198.rob(nums);
        System.out.println(res);

    }

    private Integer[] mem;

    public int rob(int[] nums) {
        mem = new Integer[nums.length];
        return robInternal(nums, 0);
    }

    private int robInternal(int[] nums, int index) {
        if (mem[index] != null) {
            return mem[index];
        }

        int maxAfter = 0;
        //遍历抢完这家之后的所有情况
        for (int i = index + 2; i < nums.length; i++) {
            int rob = robInternal(nums, i);
            maxAfter = Math.max(maxAfter, rob);
        }
        int robThisOneMaxAmount = maxAfter + nums[index];

        maxAfter = 0;
        //遍历不抢这家之后的所有情况
        for (int i = index + 1; i < nums.length; i++) {
            int rob = robInternal(nums, i);
            maxAfter = Math.max(maxAfter, rob);
        }

        mem[index] = Math.max(maxAfter, robThisOneMaxAmount);
        return mem[index];
    }

}
