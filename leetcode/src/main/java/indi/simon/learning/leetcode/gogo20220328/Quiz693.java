package indi.simon.learning.leetcode.gogo20220328;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz693 {

    public static void main(String[] args) {
        Quiz693 quiz693 = new Quiz693();
        boolean res = quiz693.hasAlternatingBits(8);
        System.out.println(res);
    }

    public boolean hasAlternatingBits(int n) {
        return hasAlternatingBitsInternal(n, null);
    }

    private boolean hasAlternatingBitsInternal(int remain, Integer lastBit) {
        if (remain == 0) {
            return true;
        }
        int thisBit = remain % 2;
        if (lastBit != null && lastBit == thisBit) {
            return false;
        } else {
            return hasAlternatingBitsInternal(remain / 2, thisBit);
        }
    }
}
