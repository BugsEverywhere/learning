package indi.simon.learning.leetcode.gogo20221003;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1800 {

    public static void main(String[] args) {
        Quiz1800 quiz1800 = new Quiz1800();
        int[] nums = new int[]{10, 20, 30, 5, 10, 50};
        int res = quiz1800.maxAscendingSum(nums);
        System.out.println(res);
    }

    public int maxAscendingSum(int[] nums) {

        int maxSum = 0;

        boolean start;
        for (int i = 0; i < nums.length; ) {
            int accSum = 0;
            int j = i;
            start = true;
            for (; j < nums.length; j++) {
                if (j == 0 || start || nums[j] > nums[j - 1]) {
                    accSum += nums[j];
                    start = false;
                } else {
                    break;
                }
            }

            i = j;
            maxSum = Math.max(maxSum, accSum);
        }

        return maxSum;
    }


}
