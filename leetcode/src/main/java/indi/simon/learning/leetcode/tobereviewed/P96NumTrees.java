package indi.simon.learning.leetcode.tobereviewed;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P96NumTrees {

    public static void main(String[] args) {

        int result = numTrees(4);
        System.out.println(result);
    }

    private static int[][][] mem;


    public static int numTrees(int n) {
        mem = new int[n+1][n+1][n+1];
        Set<Integer> fathers = new HashSet<>();
        int totalCount = 0;
        for (int i = 1; i <= n; i++) {
            fathers.add(i);
            totalCount += sumInternal(1, i, n, fathers);
            fathers.remove(i);
        }
        return totalCount;
    }

    private static int sumInternal(int lLimit, int x, int hLimit, Set<Integer> fathers) {
        if (mem[lLimit][x][hLimit] != 0) {
            return mem[lLimit][x][hLimit];
        }
        int leftCount = 0;
        int rightCount = 0;
        //左子树个数
        //比x小的数都可能作为左子节点
        for (int i = lLimit; i <= x - 1; i++) {
            if (!fathers.contains(i)) {
                fathers.add(i);
                leftCount += sumInternal(lLimit, i, x - 1, fathers);
                fathers.remove(i);
            }
        }

        //右子树个数
        //比x大的数都可能作为右子节点
        for (int i = x + 1; i <= hLimit; i++) {
            if (!fathers.contains(i)) {
                fathers.add(i);
                rightCount += sumInternal(x + 1, i, hLimit, fathers);
                fathers.remove(i);
            }
        }

        if (x == lLimit) {
            leftCount = 1;
        }
        if (x == hLimit) {
            rightCount = 1;
        }
        int result = leftCount * rightCount;
        mem[lLimit][x][hLimit] = result;
        return result;
    }


}
