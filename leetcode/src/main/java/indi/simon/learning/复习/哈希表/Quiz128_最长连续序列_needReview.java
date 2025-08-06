package indi.simon.learning.复习.哈希表;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz128_最长连续序列_needReview {

    public static void main(String[] args) {
        Quiz128_最长连续序列_needReview quiz128最长连续序列 = new Quiz128_最长连续序列_needReview();
        int[] arr = new int[]{0,3,7,2,5,8,4,6,0,1};
        int res = quiz128最长连续序列.longestConsecutive(arr);
        System.out.println(res);
    }

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int res = 1;
        for (int i = 0; i < nums.length; ) {
            int j = i + 1;
            int gap = 1;
            while (j < nums.length) {
                if (nums[j] == nums[j - 1] + 1) {
                    gap++;
                } else if (nums[j] > nums[j - 1] + 1) {
                    break;
                }
                j++;
            }
            res = Math.max(res, gap);
            i = j;
        }
        return res;
    }

}
