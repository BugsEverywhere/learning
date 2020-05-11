package indi.simon.learning.leetcode.may2020.week4to10;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.*;

public class P322_not_finish {

    private static boolean[][] mem = null;


    public static void main(String[] args) {
        int result = coinChange(new int[]{186,419,83,408}, 6249);

        System.out.println(result);
    }

    private static int min = Integer.MAX_VALUE;

    private static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        List<Integer> list = new ArrayList<>();
        coinChangeInternal(coins, amount, list, 0);
        if (min < Integer.MAX_VALUE) {
            return min;
        } else {
            return -1;
        }
    }


    private static void coinChangeInternal(int[] coins, int target, List<Integer> list, int sumForNow) {

        for (int singleCoin : coins) {
            int result1 = sumForNow + singleCoin;
            if (result1 < target) {
                list.add(singleCoin);
                coinChangeInternal(coins, target, list, result1);
                list.remove(list.size() - 1);
            } else if (result1 == target) {
                list.add(singleCoin);
                if (list.size() < min) {
                    min = list.size();
                }
                list.remove(list.size() - 1);
            } else {
                return;
            }
        }
    }

    private static int coinChangeInternal111(int idxCoin, int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (idxCoin < coins.length && amount > 0) {
            int maxVal = amount / coins[idxCoin];
            int minCost = Integer.MAX_VALUE;
            for (int x = 0; x <= maxVal; x++) {
                if (amount >= x * coins[idxCoin]) {
                    int res = coinChangeInternal111(idxCoin + 1, coins, amount - x * coins[idxCoin]);
                    if (res != -1) {
                        minCost = Math.min(minCost, res + x);
                    }
                }
            }
            return (minCost == Integer.MAX_VALUE)? -1: minCost;
        }
        return -1;
    }

    private int coinChangeInternal222(int[] coins, int remain, int[] count) {
        if (remain < 0) {
            return -1;
        }
        if (remain == 0) {
            return 0;
        }
        if (count[remain - 1] != 0) {
            return count[remain - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChangeInternal222(coins, remain - coin, count);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        count[remain - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[remain - 1];
    }
}

