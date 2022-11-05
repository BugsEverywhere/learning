package indi.simon.learning.leetcode.gogo20221031;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz754_needReview {

    public static void main(String[] args) {
        Quiz754_needReview quiz754 = new Quiz754_needReview();
        int res = quiz754.reachNumber(-1000000000);
        System.out.println(res);
    }

    public int reachNumber(int target) {
        target = Math.abs(target);
        int k = 0;
        while (target > 0) {
            k++;
            target -= k;
        }
        return target % 2 == 0 ? k : k + 1 + k % 2;
    }

}
