package indi.simon.learning.leetcode.gogo20220328;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz66 {

    public static void main(String[] args) {
        int[] arr = new int[]{9, 9, 9};
        Quiz66 quiz66 = new Quiz66();
        int[] res = quiz66.plusOne(arr);
        System.out.println(res);
    }

    public int[] plusOne(int[] digits) {
        int carry = 1;

        for (int j = digits.length - 1; j >= 0; j--) {
            int originNum = digits[j];
            digits[j] = originNum + carry;
            if (digits[j] >= 10) {
                digits[j] = digits[j] % 10;
                carry = 1;
            } else {
                carry = 0;
            }
        }

        if (carry > 0) {
            int[] res = new int[digits.length + 1];
            System.arraycopy(digits, 0, res, 1, digits.length);
            res[0] = carry;
            return res;
        }

        return digits;
    }
}
