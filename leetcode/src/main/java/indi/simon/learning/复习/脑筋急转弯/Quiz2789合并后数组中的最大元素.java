package indi.simon.learning.复习.脑筋急转弯;

/**
 * Created by Chen Zhuo on 2024/4/14.
 */
public class Quiz2789合并后数组中的最大元素 {

    public static void main(String[] args) {

    }

    public long maxArrayValue(int[] nums) {
        long sum = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            sum = nums[i] <= sum ? nums[i] + sum : nums[i];
        }
        return sum;
    }

}
