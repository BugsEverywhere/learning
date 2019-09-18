package main.java.indi.simon.leetcode;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class NumRevert7 {

    public static void main(String[] args) {

        int result = reverse(1534236469);

        System.out.println(result);

    }

    public static int reverse(int x) {

        boolean mark;
        mark = x >= 0;
        x = Math.abs(x);
        long tmp = (long) x;
        int i = 0;
        int bitCount = 0;
        while ((int) (tmp / Math.pow(10, i)) != 0) {
            bitCount++;
            i++;
        }
        int[] bitArr = new int[bitCount];

        for (int j = 1; j <= bitCount; j++) {
            int thisBit = Math.abs((int) (tmp / Math.pow(10, bitCount - j)));
            bitArr[j - 1] = thisBit;
            tmp = tmp - thisBit * (int) Math.pow(10, bitCount - j);
        }
        int y = 0;
        int result = 0;

        while (y < bitCount) {
            if (result + bitArr[y] * Math.pow(10, y) > Integer.MAX_VALUE) {
                return 0;
            }
            result += bitArr[y] * (int) Math.pow(10, y);
            y++;
        }
        if (!mark && result * -1 >= Integer.MIN_VALUE) {
            result = result * -1;
        } else if (result * -1 < Integer.MIN_VALUE) {
            return 0;
        }

        return result;
    }

}
