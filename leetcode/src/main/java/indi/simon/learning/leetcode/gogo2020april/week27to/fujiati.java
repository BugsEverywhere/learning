package indi.simon.learning.leetcode.gogo2020april.week27to;

public class fujiati {

    public static void main(String[] args) {

        int result = nSteps(4);

        System.out.println(result);

    }

    private static int[] mem = null;

    private static int nSteps(int n) {

        mem = new int[n + 1];

        return nStepsThisLevel(0, n + 1);
    }

    private static int nStepsThisLevel(int x, int n) {

        if (x >= n) {
            return 0;
        }


        if (mem[x] != 0) {
            return mem[x];
        }

        if (x + 1 == n) {
            return 1;
        }

        int thisLevelResult = nStepsThisLevel(x + 1, n) + nStepsThisLevel(x + 2, n);

        mem[x] = thisLevelResult;

        return thisLevelResult;

    }

}
