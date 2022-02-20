package indi.simon.learning.leetcode.gogo20220214;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz717 {

    public static void main(String[] args) {
        Quiz717 quiz717 = new Quiz717();
        int[] bits = new int[]{1, 1, 0};
        boolean res = quiz717.isOneBitCharacter(bits);
        System.out.println(res);
    }

    public boolean isOneBitCharacter(int[] bits) {
        return checkAtCertainPosition(0, bits);
    }

    private boolean checkAtCertainPosition(int position, int[] bits) {
        if (position >= bits.length) {
            return false;
        }

        if (position == bits.length - 1) {
            return bits[position] == 0;
        }

        if (bits[position] == 0) {
            //是一个一位数
            return checkAtCertainPosition(position + 1, bits);
        } else {
            //是一个两位数
            return checkAtCertainPosition(position + 2, bits);
        }

    }

}
