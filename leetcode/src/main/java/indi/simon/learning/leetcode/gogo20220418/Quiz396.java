package indi.simon.learning.leetcode.gogo20220418;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz396 {

    public static void main(String[] args) {
        Quiz396 quiz396 = new Quiz396();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int res = quiz396.maxRotateFunction(nums);
        System.out.println(res);
    }

    public int maxRotateFunction(int[] nums) {
        int fZero = 0;
        int pureSum = 0;
        for (int i = 0; i < nums.length; i++) {
            fZero += i * nums[i];
            pureSum += nums[i];
        }

        int max = fZero;
        int lastF = fZero;
        for (int index = 1; index < nums.length; index++) {
            int fIndex = lastF + pureSum - (nums.length) * nums[nums.length - index];
            if (fIndex > max) {
                max = fIndex;
            }
            lastF = fIndex;
        }

        return max;
    }
}
