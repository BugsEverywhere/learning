package indi.simon.learning.leetcode.gogo20220404;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz762 {

    public static void main(String[] args) {

    }

    public int countPrimeSetBits(int left, int right) {
        int allOne = 0xFFFFFFFF;
        int count = 0;
        for (int i = left; i <= right; i++) {
            int afterAnd = i & allOne;
            String binaryStr = Integer.toBinaryString(afterAnd);
            int zhishuCount = 0;
            for (char c : binaryStr.toCharArray()) {
                if (c == '1') {
                    zhishuCount++;
                }
            }
            if (isPrime(zhishuCount)) {
                count++;
            }
        }
        return count;
    }


    private boolean isPrime(int n) {
        int i = 2;
        for (; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        if (n == i) {
            return true;
        } else {
            return false;
        }
    }
}
