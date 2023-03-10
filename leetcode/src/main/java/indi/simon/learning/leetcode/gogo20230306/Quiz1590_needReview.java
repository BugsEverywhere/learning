package indi.simon.learning.leetcode.gogo20230306;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1590_needReview {

    public static void main(String[] args) {
        Quiz1590_needReview quiz1590NeedReview = new Quiz1590_needReview();
        int res = quiz1590NeedReview.minSubarray(new int[]{1000000000, 1000000000, 1000000000}, 3);
        System.out.println(res);
    }


    //todo: 超时
    public int minSubarray(int[] nums, int p) {

        long sum = 0;
        //leftSum[i]表示nums[i]左侧所有元素的和
        int[] leftSum = new int[nums.length];
        //rightSum[i]表示nums[i]右侧所有元素的和
        int[] rightSum = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (i != 0) {
                leftSum[i] = leftSum[i - 1] + nums[i - 1];
            }
            sum += nums[i];
        }

        if (sum % p == 0) {
            return 0;
        }

        for (int i = nums.length - 2; i >= 0; i--) {
            rightSum[i] = rightSum[i + 1] + nums[i + 1];
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; ) {
            int j = i;
            for (; j < nums.length; j++) {
                if ((leftSum[i] + rightSum[j]) % p == 0 && j - i + 1 != nums.length) {
                    res = Math.min(res, j - i + 1);
                    i = j + 1;
                    break;
                }
            }
            if (j == nums.length) {
                i++;
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

}
