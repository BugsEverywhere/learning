package indi.simon.learning.leetcode.gogo20220321;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz172 {

    public static void main(String[] args) {

    }

    public int trailingZeroes(int n) {
        if (n >= 3125) {
            int threeOneTwoFive = n / 3125;
            int sixTwoFiveCount = n / 625 - threeOneTwoFive;
            int oneTwoFiveCount = n / 125 - sixTwoFiveCount - threeOneTwoFive;
            int twentyFifthCount = n / 25 - oneTwoFiveCount - sixTwoFiveCount - threeOneTwoFive;
            int fiveCount = n / 5 - twentyFifthCount - oneTwoFiveCount - sixTwoFiveCount - threeOneTwoFive;
            return fiveCount + twentyFifthCount * 2 + oneTwoFiveCount * 3 + sixTwoFiveCount * 4 + threeOneTwoFive * 5;
        } else if (n < 3125 && n >= 625) {
            int sixTwoFiveCount = n / 625;
            int oneTwoFiveCount = n / 125 - sixTwoFiveCount;
            int twentyFifthCount = n / 25 - oneTwoFiveCount - sixTwoFiveCount;
            int fiveCount = n / 5 - twentyFifthCount - oneTwoFiveCount - sixTwoFiveCount;
            return fiveCount + twentyFifthCount * 2 + oneTwoFiveCount * 3 + sixTwoFiveCount * 4;
        } else if (n < 625 && n >= 125) {
            int oneTwoFiveCount = n / 125;
            int twentyFifthCount = n / 25 - oneTwoFiveCount;
            int fiveCount = n / 5 - twentyFifthCount - oneTwoFiveCount;
            return fiveCount + twentyFifthCount * 2 + oneTwoFiveCount * 3;
        } else if (n < 125 && n >= 25) {
            int twentyFifthCount = n / 25;
            int fiveCount = n / 5 - twentyFifthCount;
            return fiveCount + twentyFifthCount * 2;
        } else if (n < 25 && n >= 5) {
            return n / 5;
        } else {
            return 0;
        }
    }
}
