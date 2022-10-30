package indi.simon.learning.leetcode.gogo20221024;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1822 {

    public static void main(String[] args) {

    }

    public int arraySign(int[] nums) {
        int minusCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                return 0;
            } else if (nums[i] < 0) {
                minusCount++;
            }
        }
        if (minusCount % 2 == 0) {
            return 1;
        } else {
            return -1;
        }
    }

}
