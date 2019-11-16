package indi.simon.leetcode.bytedance.dpgreed;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class MaxProfit {

    public static void main(String[] args) {

        int[] prices = {7, 6, 4, 3, 1};

        int maxProfit = maxProfit(prices);

        System.out.println(maxProfit);
    }

    private static int maxProfit = 0;
    private static Integer minPrice = null;

    public static int maxProfit(int[] prices) {
        for (int i = 0; i < prices.length; i++) {
            if (minPrice != null && prices[i] >= minPrice) {
                continue;
            }
            minPrice = prices[i];
            buyIn(i, prices);
        }
        return maxProfit;
    }

    private static void buyIn(int i, int[] prices) {
        for (int j = i + 1; j < prices.length; j++) {
            if (prices[j] - prices[i] > maxProfit) {
                maxProfit = prices[j] - prices[i];
            }
        }
    }

}
