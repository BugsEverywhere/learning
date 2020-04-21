package indi.simon.learning.leetcode;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P70ClimbStairs {

    public static void main(String[] args) {
        int result = climbStairs(3);
        System.out.println(result);
    }

    private static int[] mem1;

    public static int climbStairs(int n) {
        mem1 = new int[n + 1];
        return climbStairsInternal(n);
    }

    public static int climbStairsInternal(int x) {
        if (mem1[x] > 0) {
            return mem1[x];
        }
        if (x >= 2) {
            mem1[x] = climbStairsInternal(x - 1) + climbStairsInternal(x - 2);
            return mem1[x];
        }
        if (x == 0) {
            mem1[x] = 1;
            return mem1[x];
        }
        mem1[1] = 1;
        return 1;
    }

}
