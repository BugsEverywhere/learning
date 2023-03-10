package indi.simon.learning.leetcode.两次遍历;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz238_hinted {

    public static void main(String[] args) {
        Quiz238_hinted quiz238Hinted = new Quiz238_hinted();
        int[] res = quiz238Hinted.productExceptSelf(new int[]{1, 2, 3, 4});
        System.out.println(res);
    }

    public int[] productExceptSelf(int[] nums) {

        int[] forwardTime = new int[nums.length];
        int[] backwardTime = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                forwardTime[i] = 1;
            } else {
                forwardTime[i] = forwardTime[i - 1] * nums[i - 1];
            }
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            if (i == nums.length - 1) {
                backwardTime[i] = 1;
            } else {
                backwardTime[i] = backwardTime[i + 1] * nums[i + 1];
            }
        }

        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            res[i] = forwardTime[i] * backwardTime[i];
        }

        return res;
    }

}
