package indi.simon.learning.leetcode.gogo2020june;

public class P134 {

    public static void main(String[] args) {
        int[] gas = new int[]{1, 2, 3, 4, 5, 5, 70};
        int[] cost = new int[]{2, 3, 4, 3, 9, 6, 2};
        int res = canCompleteCircuit(gas,cost);
        System.out.println(res);
    }

    private static int canCompleteCircuit(int[] gas, int[] cost) {

        int[] realCost = new int[gas.length];
        int sum = 0;
        for (int i = 0; i < gas.length; i++) {
            realCost[i] = gas[i] - cost[i];
            sum = sum + realCost[i];
        }

        if (sum < 0) {
            return -1;
        }

        for (int i = 0; i < realCost.length; i++) {
            if (realCost[i] >= 0) {
                int sumAgain = 0;
                boolean turned = false;
                for (int j = i; ; ) {
                    sumAgain = sumAgain + realCost[j];
                    if (sumAgain < 0) {
                        break;
                    }
                    if (j == i && turned) {
                        return j;
                    }

                    j++;
                    if (j == realCost.length) {
                        turned = true;
                        j = 0;
                    }
                }
            }
        }

        return -1;
    }
}
