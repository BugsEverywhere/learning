package indi.simon.learning.leetcode.gogo20230109;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2293 {

    public static void main(String[] args) {

    }

    public int minMaxGame(int[] nums) {
        int[] newNums = null;
        int n = nums.length;
        while (n > 1) {
            newNums = new int[n / 2];
            for (int i = 0; i < newNums.length; i++) {
                newNums[i] = i % 2 == 0 ? Math.min(nums[2 * i], nums[2 * i + 1]) : Math.max(nums[2 * i], nums[2 * i + 1]);
            }
            nums = newNums;
            n = nums.length;
        }
        if (newNums == null) {
            return nums[0];
        }
        return newNums[0];
    }

}
