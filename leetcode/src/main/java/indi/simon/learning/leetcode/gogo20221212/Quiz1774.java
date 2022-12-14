package indi.simon.learning.leetcode.gogo20221212;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 可以看看官方回溯为什么那么精炼
public class Quiz1774 {

    public static void main(String[] args) {
        Quiz1774 quiz1774 = new Quiz1774();
        int res = quiz1774.closestCost(new int[]{3, 10}, new int[]{2, 5}, 9);
        System.out.println(res);
    }

    private int leastGap;
//    private int[][][] mem;

    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        leastGap = Integer.MAX_VALUE;
        Arrays.sort(toppingCosts);
        Arrays.sort(baseCosts);

        int maxBaseCost = baseCosts[baseCosts.length - 1];
        int maxToppingCost = 2 * toppingCosts[toppingCosts.length - 1];
//        mem = new int[toppingCosts.length][maxBaseCost + 1][maxToppingCost + 1];

        int finalRes = Integer.MAX_VALUE;
        for (int baseCost : baseCosts) {
            int baseRes = closestCostInternal(toppingCosts, target, 0, baseCost, 0);
            if (Math.abs(baseRes - target) < Math.abs(finalRes - target)) {
                finalRes = baseRes;
            } else if (Math.abs(baseRes - target) == Math.abs(finalRes - target)) {
                finalRes = Math.min(finalRes, baseRes);
            }
        }

        return finalRes;
    }

    private int closestCostInternal(int[] toppingCosts, int target, int j, int baseCost, int toppingCost) {
        int totalCost = (baseCost + toppingCost);
        //递归终止条件
        if (j >= toppingCosts.length || totalCost >= target) {
            return totalCost;
        }

        //查询备忘录
//        if (mem[j][baseCost][toppingCost] != 0) {
//            return mem[j][baseCost][toppingCost];
//        }


        //不选择该种配料
        int res1 = closestCostInternal(toppingCosts, target, j + 1, baseCost, toppingCost);

        //选择1份该种配料
        int res2 = closestCostInternal(toppingCosts, target, j + 1, baseCost, toppingCost + toppingCosts[j]);

        //选择2份该种配料
        int res3 = closestCostInternal(toppingCosts, target, j + 1, baseCost, toppingCost + 2 * toppingCosts[j]);


        if (Math.abs(res1 - target) < Math.abs(res2 - target)) {
            if (Math.abs(res1 - target) < Math.abs(res3 - target)) {
//                mem[j][baseCost][toppingCost] = res1;
                return res1;
            } else if (Math.abs(res1 - target) > Math.abs(res3 - target)) {
//                mem[j][baseCost][toppingCost] = res3;
                return res3;
            } else {
//                mem[j][baseCost][toppingCost] = Math.min(res3, res1);
                return Math.min(res3, res1);
            }
        } else if (Math.abs(res1 - target) > Math.abs(res2 - target)) {
            if (Math.abs(res2 - target) < Math.abs(res3 - target)) {
//                mem[j][baseCost][toppingCost] = res2;
                return res2;
            } else if (Math.abs(res2 - target) > Math.abs(res3 - target)) {
//                mem[j][baseCost][toppingCost] = res3;
                return res3;
            } else {
//                mem[j][baseCost][toppingCost] = Math.min(res3, res2);
                return Math.min(res3, res2);
            }
        } else {
            int res12 = Math.min(res1, res2);
            if (Math.abs(res12 - target) < Math.abs(res3 - target)) {
//                mem[j][baseCost][toppingCost] = res12;
                return res12;
            } else if (Math.abs(res12 - target) > Math.abs(res3 - target)) {
//                mem[j][baseCost][toppingCost] = res3;
                return res3;
            } else {
//                mem[j][baseCost][toppingCost] = Math.min(res3, res12);
                return Math.min(res3, res12);
            }
        }

//        return mem[j][baseCost][toppingCost];
    }


}
