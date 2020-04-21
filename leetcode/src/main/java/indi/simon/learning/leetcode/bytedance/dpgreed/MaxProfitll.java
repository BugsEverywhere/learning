package indi.simon.learning.leetcode.bytedance.dpgreed;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class MaxProfitll {

    private static int maxProfit = 0;
    private static Integer minPrice = Integer.MAX_VALUE;

    //备忘录,记录下
    private static int[] buyInMem;

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 4, 5};
        int maxProfit = maxProfit(prices);
        System.out.println(maxProfit);
    }

    public static int maxProfit(int[] prices) {
        buyInMem = new int[prices.length];
        for (int index = 0; index < buyInMem.length; index++) {
            buyInMem[index] = -1;
        }
        for (int i = 0; i < prices.length; i++) {
            if (minPrice != null && prices[i] >= minPrice) {
                continue;
            }
            minPrice = prices[i];
            int result;
            if (buyInMem[i] >= 0) {
                result = buyInMem[i];
            } else {
                result = buyIn(i, prices);
                buyInMem[i] = result;
            }
            if (result > maxProfit) {
                maxProfit = result;
            }
        }
        return maxProfit;
    }

    private static int buyIn(int i, int[] prices) {
        int maxProfitThisRound = 0;
        //j是卖出时刻
        for (int j = i + 1; j < prices.length; j++) {
            int maxProfitThisSell = 0;
            if (prices[j] - prices[i] > 0) {
                //穷举下一次买入，递归
                int maxProfitNextBuyIn = 0;
                for (int k = j + 1; k < prices.length; k++) {
                    int profit;
                    if (buyInMem[k] >= 0) {
                        profit = buyInMem[k];
                    } else {
                        profit = buyIn(k, prices);
                        buyInMem[k] = profit;
                    }
                    if (profit >= maxProfitNextBuyIn) {
                        maxProfitNextBuyIn = profit;
                    }
                }
                maxProfitThisSell += maxProfitNextBuyIn + prices[j] - prices[i];
            }
            if (maxProfitThisSell > maxProfitThisRound) {
                maxProfitThisRound = maxProfitThisSell;
            }
        }
        return maxProfitThisRound;
    }

}
