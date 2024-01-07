package indi.simon.learning.leetcode.bytedance.dpgreed;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class MaxProfitll {

    private static int maxProfit = 0;
    //遍历的时候记录实时最低股价，此后只需在比之前最低价还低的地方买入往下递归即可
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
        Arrays.fill(buyInMem, -1);
        for (int i = 0; i < prices.length; i++) {
            //todo 遍历时记录最低股价
            if (minPrice != null && prices[i] >= minPrice) {
                continue;
            }
            minPrice = prices[i];
            //todo 每次先从备忘录取值
            int result;
            if (buyInMem[i] >= 0) {
                result = buyInMem[i];
            } else {
                result = buyIn(i, prices);
            }
            if (result > maxProfit) {
                maxProfit = result;
            }
        }
        return maxProfit;
    }

    private static int buyIn(int i, int[] prices) {
        //todo 记录每一次买入的最大利润，这个需要计入备忘录
        //穷举卖出时刻，j是卖出时刻
        int maxProfitThisRound = 0;
        for (int j = i + 1; j < prices.length; j++) {
            //todo 仅仅当有利润时才卖出，节省时间
            if (prices[j] - prices[i] <= 0) {
                continue;
            }
            int maxProfitThisRoundAgent = 0;
            //穷举下一次买入，递归
            int maxProfitNextBuyIn = 0;
            for (int k = j + 1; k < prices.length; k++) {
                int maxProfitNextBuyInAgent;
                if (buyInMem[k] >= 0) {
                    maxProfitNextBuyInAgent = buyInMem[k];
                } else {
                    maxProfitNextBuyInAgent = buyIn(k, prices);
                }
                //todo 记录卖出之后下一次买入的最大利润
                if (maxProfitNextBuyInAgent >= maxProfitNextBuyIn) {
                    maxProfitNextBuyIn = maxProfitNextBuyInAgent;
                }
            }
            maxProfitThisRoundAgent += maxProfitNextBuyIn + prices[j] - prices[i];

            //todo 记录本次卖出的最大利润
            if (maxProfitThisRoundAgent > maxProfitThisRound) {
                maxProfitThisRound = maxProfitThisRoundAgent;
            }
        }
        //todo 统一在此处记录备忘录
        buyInMem[i] = maxProfitThisRound;
        return maxProfitThisRound;
    }


    //==================================================================================================================================================
    //todo 下面这种方法正确，但是超时了

    public static int maxProfit2(int[] prices) {
        for (int i = 0; i < prices.length - 1; i++) {
            maxProfitInternal2(prices, i, 0);
        }
        if (maxProfit < 0) {
            return 0;
        }
        return maxProfit;
    }

    private static void maxProfitInternal2(int[] prices, int buyInIndex, int profitForNow) {

        if (buyInIndex == prices.length - 2) {
            profitForNow = prices[prices.length - 1] - prices[buyInIndex] + profitForNow;
            if (profitForNow >= maxProfit) {
                maxProfit = profitForNow;
            }
            return;
        }

        for (int i = buyInIndex + 1; i < prices.length; i++) {
            if (prices[i] - prices[buyInIndex] < 0) {
                continue;
            }
            int profitCopy = profitForNow + prices[i] - prices[buyInIndex];
            if (profitCopy >= maxProfit) {
                maxProfit = profitCopy;
            }
            for (int k = i + 1; k < prices.length - 1; k++) {
                maxProfitInternal2(prices, k, profitCopy);
            }
        }
    }

    //=====================================================================================================================================================

    public int maxProfit3(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxprofit += prices[i] - prices[i - 1];
            }
        }
        return maxprofit;
    }

}
