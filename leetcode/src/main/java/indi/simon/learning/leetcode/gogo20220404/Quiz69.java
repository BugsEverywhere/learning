package indi.simon.learning.leetcode.gogo20220404;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz69 {

    public static void main(String[] args) {
        Quiz69 quiz69 = new Quiz69();
        int res = quiz69.mySqrt(2147395599);
        System.out.println(res);
    }

    public int mySqrt(int x) {

        int small = 0;
        int big = x;
        long mid = 0;
        while (small <= big) {
            mid = (small + big) / 2;
            if (mid * mid < x && ((mid + 1) * (mid + 1)) <= x) {
                small = (int)mid + 1;
            } else if (mid * mid < x && ((mid + 1) * (mid + 1)) > x) {
                return (int)mid;
            } else if (mid * mid > x) {
                big = (int)mid - 1;
            } else {
                return (int)mid;
            }
        }

        return (int)mid;
    }

}
