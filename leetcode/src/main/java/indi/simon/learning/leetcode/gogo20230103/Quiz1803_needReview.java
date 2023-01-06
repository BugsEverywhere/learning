package indi.simon.learning.leetcode.gogo20230103;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1803_needReview {

    public static void main(String[] args) {
        Quiz1803_needReview quiz1803NeedReview = new Quiz1803_needReview();
        int res = quiz1803NeedReview.countPairs(new int[]{9, 8, 4, 2, 1}, 5, 14);
        System.out.println(res);
    }

    public int countPairs(int[] nums, int low, int high) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int xor = nums[i] ^ nums[j];
                if (xor >= low && xor <= high) {
                    res++;
                }
            }
        }

        return res;
    }


}
