package indi.simon.learning.leetcode.gogo20220808;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1413 {

    public static void main(String[] args) {
        Quiz1413 quiz1413 = new Quiz1413();
        int res = quiz1413.minStartValue(new int[]{1, -2, -3});
        System.out.println(res);
    }

    public int minStartValue(int[] nums) {

        int minAccVal = Integer.MAX_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum < minAccVal) {
                minAccVal = sum;
            }
        }

        if (minAccVal >= 0) {
            return 1;
        }

        return -minAccVal + 1;
    }
}
