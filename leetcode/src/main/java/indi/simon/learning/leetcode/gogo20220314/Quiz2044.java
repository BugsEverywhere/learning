package indi.simon.learning.leetcode.gogo20220314;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2044 {

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5};
        Quiz2044 quiz2044 = new Quiz2044();
        int res = quiz2044.countMaxOrSubsets(nums);
        System.out.println(res);
    }

    private int maxValSoFar;
    private int maxValCount;

    public int countMaxOrSubsets(int[] nums) {
        maxValCount = 0;
        maxValSoFar = Integer.MIN_VALUE;
        countMaxOrSubsetsInternal(nums, 0, 0x00000000);
        return maxValCount;
    }

    private void countMaxOrSubsetsInternal(int[] nums, int index, int orValSoFar) {
        if (index >= nums.length) {
            return;
        }

        int orValThisRound = orValSoFar | nums[index];

        if (orValThisRound > maxValSoFar) {
            maxValSoFar = orValThisRound;
            maxValCount = 1;
        } else if (orValThisRound == maxValSoFar) {
            maxValCount++;
        }

        //该位或上
        countMaxOrSubsetsInternal(nums, index + 1, orValThisRound);

        //该位跳过
        countMaxOrSubsetsInternal(nums, index + 1, orValSoFar);

    }
}
