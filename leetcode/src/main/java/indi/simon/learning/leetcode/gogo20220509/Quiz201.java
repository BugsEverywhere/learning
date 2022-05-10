package indi.simon.learning.leetcode.gogo20220509;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz201 {

    public static void main(String[] args) {
        Quiz201 quiz201 = new Quiz201();
        int res = quiz201.rangeBitwiseAnd(1, 2147483647);
        System.out.println(res);
    }

    public int rangeBitwiseAnd(int left, int right) {

        String leftBinStr = Integer.toBinaryString(left);
        String rightBinStr = Integer.toBinaryString(right);

        int res = 0;
        for (int i = 0; i < leftBinStr.length(); i++) {
            char leftC = leftBinStr.charAt(leftBinStr.length() - i - 1);
            char rightC = rightBinStr.charAt(rightBinStr.length() - i - 1);
            boolean thisBit = oneBit((int) Math.pow(2, i), right - left, leftC, rightC);
            if (thisBit) {
                res = res | 1 << i;
            }
        }
        return res;
    }

    private boolean oneBit(int cycle, int gap, char leftC, char rightC) {
        if (gap >= cycle) {
            return false;
        } else {
            if (leftC == rightC && leftC == '1') {
                return true;
            } else {
                return false;
            }
        }
    }

}
