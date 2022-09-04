package indi.simon.learning.leetcode.gogo20220829;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1475 {

    public static void main(String[] args) {

    }

    public int[] finalPrices(int[] prices) {
        for (int i = 0; i < prices.length; i++) {
            int newPrice = prices[i];
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] <= prices[i]) {
                    newPrice = prices[i] - prices[j];
                    break;
                }
            }
            prices[i] = newPrice;
        }
        return prices;
    }

}
