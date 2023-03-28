package indi.simon.learning.leetcode.gogo20230327;


/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz334 {

    public static void main(String[] args) {
        Quiz334 quiz334 = new Quiz334();
        boolean res = quiz334.increasingTriplet(new int[]{2, 1, 5, 0, 4, 6});
        System.out.println(res);
    }

    public boolean increasingTriplet(int[] nums) {

        boolean[] tailLarger = new boolean[nums.length];
        boolean[] headSmaller = new boolean[nums.length];

        int maxTail = Integer.MIN_VALUE;
        int minHead = Integer.MAX_VALUE;

        for (int i = nums.length - 1; i >= 0; i--) {
            if (maxTail > nums[i]) {
                tailLarger[i] = true;
            } else if (maxTail < nums[i]) {
                maxTail = nums[i];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (minHead < nums[i]) {
                headSmaller[i] = true;
            } else if (minHead > nums[i]) {
                minHead = nums[i];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (tailLarger[i] && headSmaller[i]) {
                return true;
            }
        }

        return false;
    }
}
