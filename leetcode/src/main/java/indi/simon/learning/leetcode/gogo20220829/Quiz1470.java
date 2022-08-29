package indi.simon.learning.leetcode.gogo20220829;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1470 {

    public static void main(String[] args) {

    }

    public int[] shuffle(int[] nums, int n) {
        int[] res = new int[nums.length];
        int leftIndex = 0;
        int rightIndex = n;
        for (int i = 0; i < 2 * n; i++) {
            if (i % 2 == 0) {
                res[i] = nums[leftIndex];
                leftIndex++;
            } else {
                res[i] = nums[rightIndex];
                rightIndex++;
            }
        }

        return res;
    }

}
