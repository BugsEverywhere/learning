package indi.simon.learning.leetcode.tobereviewed;

public class P29 {

    public static void main(String[] args) {

        int devideRes = divide(10, 3);
        System.out.println(devideRes);

    }

    private static int divide(int dividend, int divisor) {
        boolean positive = false;
        if (dividend == 0) {
            return 0;
        }
        if ((dividend > 0 && divisor > 0) || (divisor < 0 && dividend < 0)) {
            positive = true;
        }
        long dividendComputeVal;
        long divisorComputeVal;
        if (dividend < 0) {
            dividendComputeVal = -(long) dividend;
        } else {
            dividendComputeVal = dividend;
        }

        if (divisor < 0) {
            divisorComputeVal = -(long) divisor;
        } else {
            divisorComputeVal = divisor;
        }

        long res = positive ? divideInternal(dividendComputeVal, divisorComputeVal) : -divideInternal(dividendComputeVal, divisorComputeVal);
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) res;
    }


    private static long divideInternal(long dividend, long divisor) {
        long originalDivisor = divisor;
        if (dividend < divisor) {
            return 0;
        } else if (dividend == divisor) {
            return 1;
        } else {
            long res = 1;
            while (dividend > divisor) {
                divisor += divisor;
                res += res;
            }
            res = res >>> 1;
            divisor = divisor >>> 1;
            res = res + divideInternal(dividend - divisor, originalDivisor);
            return res;
        }

    }

}
