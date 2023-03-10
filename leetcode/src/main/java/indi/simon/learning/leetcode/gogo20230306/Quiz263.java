package indi.simon.learning.leetcode.gogo20230306;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz263 {

    public static void main(String[] args) {
        Quiz263 quiz263 = new Quiz263();
        boolean res = quiz263.isUgly(2);
        System.out.println(res);
    }

    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }

        return isUglyInternal(n);
    }

    private boolean isUglyInternal(int x) {
        if (x == 1) {
            return true;
        }

        if (x <= 0) {
            return false;
        }

        boolean res = false;

        if (x % 2 == 0) {
            res = isUglyInternal(x / 2);
            if (res) {
                return res;
            }
        }
        if (x % 3 == 0) {
            res = isUglyInternal(x / 3);
            if (res) {
                return res;
            }
        }
        if (x % 5 == 0) {
            res = isUglyInternal(x / 5);
            if (res) {
                return res;
            }
        }
        return res;
    }


}
