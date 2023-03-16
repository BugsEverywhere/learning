package indi.simon.learning.leetcode.gogo20230313;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz400 {

    public static void main(String[] args) {
        Quiz400 quiz400 = new Quiz400();
        int res = quiz400.findNthDigit(1000000000);
        System.out.println(res);
    }

    public int findNthDigit(int n) {
        int bitCount = 1;
        long totalNumCount = (long) Math.pow(10, bitCount - 1) * 9 * bitCount;
        while (totalNumCount < n) {
            bitCount++;
            totalNumCount += (long) Math.pow(10, bitCount - 1) * 9 * bitCount;
        }

        long remain;
        long mod;
        if (bitCount > 1) {
            totalNumCount -= (long) Math.pow(10, bitCount - 1) * 9 * bitCount;
            remain = (n - totalNumCount) / bitCount;
            mod = (n - totalNumCount) % bitCount;
        } else {
            return n;
        }

        long numWeWant;
        if (remain == 0) {
            numWeWant = (int) Math.pow(10, bitCount - 1);
        } else {
            numWeWant = (int) Math.pow(10, bitCount - 1) - 1 + remain;
        }

        if (mod == 0) {
            String str = Long.toString(numWeWant);
            char singleNum = str.charAt(str.length() - 1);
            return singleNum - '0';
        } else {
            String str = Long.toString(numWeWant + 1);
            char singleNum = str.charAt((int)mod - 1);
            return singleNum - '0';
        }
    }
}
