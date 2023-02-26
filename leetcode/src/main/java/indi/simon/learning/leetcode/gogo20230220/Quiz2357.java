package indi.simon.learning.leetcode.gogo20230220;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2357 {

    public static void main(String[] args) {
        Quiz2357 quiz2357 = new Quiz2357();
        int res = quiz2357.minimumOperations(new int[]{0});
        System.out.println(res);
    }

    public int minimumOperations(int[] nums) {
        Arrays.sort(nums);
        if (nums[nums.length - 1] == 0) {
            return 0;
        }

        int res = 0;
        int lastNum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 || nums[i] == lastNum) {
                continue;
            }
            lastNum = nums[i];
            res++;
        }

        return res;
    }

}
