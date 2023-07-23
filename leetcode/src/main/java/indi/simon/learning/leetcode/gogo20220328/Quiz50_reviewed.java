package indi.simon.learning.leetcode.gogo20220328;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 快速幂与机械算幂体现了O(logN)和O(N)的区别
public class Quiz50_reviewed {

    public static void main(String[] args) {
        Quiz50_reviewed quiz50 = new Quiz50_reviewed();
        double res = quiz50.myPow(2, -2147483648);
        System.out.println(res);
    }

    public double myPow(double x, int n) {
        return myPowInternal(x, n);
    }

    //todo: 为了防止类型溢出，用一个long来套一下，不过真实面试场景无所谓。反正记住，一旦涉及到对某个负数取反，
    // 而这个负整数有可能是-2147483648，也就是Integer.MIN_VALUE，那么取反之前一定要将其类型转换成long，不然结果不符合预期
    // 在这里的话，如果n=-2147483648，而n本身是int类型，那么在对他取反操作时n仍然是他本身没变（因为溢出），没有变成 +2147483648，
    // 这样导致第一层递归计算的就是x的 -2147483648/2 = -1073741824 次幂，这是能算出来的，因为对int类型的 -1073741824 取反是能成功的，
    // 但是算出来结果（为0）返回给外层使用时就不符合预期了，变成了1/0=∞，实际上第一层递归应该返回∞，结果为1/∞=0
    private double myPowInternal(double x, long n) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (n == 2) {
            return x * x;
        }
        if (n > 0) {
            if (n % 2 == 0) {
                return myPowInternal(myPowInternal(x, n / 2), 2);
            } else {
                return myPowInternal(myPowInternal(x, n / 2), 2) * x;
            }
        } else {
            long k = -n;
            double purePowRes;
            if (k % 2 == 0) {
                purePowRes = myPowInternal(myPowInternal(x, k / 2), 2);
            } else {
                purePowRes = myPowInternal(myPowInternal(x, k / 2), 2) * x;
            }
            return 1 / purePowRes;
        }
    }


    /**
     * @param x
     * @param n
     * @return
     */
    public double myPowOfficial(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

}
