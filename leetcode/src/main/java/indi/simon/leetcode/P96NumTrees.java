package indi.simon.leetcode;

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

        int result = numTrees(3);
        System.out.println(result);
    }


    public static int numTrees(int n) {
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

        if (leftCount == 0) {
            return rightCount;
        } else if (rightCount == 0) {
            return leftCount;
        }
        return leftCount * rightCount;
    }


}
