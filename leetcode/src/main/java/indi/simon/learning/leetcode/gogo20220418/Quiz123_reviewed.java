package indi.simon.learning.leetcode.gogo20220418;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz123_reviewed {

    public static void main(String[] args) {
        Quiz123_reviewed quiz123 = new Quiz123_reviewed();
        int[] prices = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        int res = quiz123.maxProfit(prices);
        System.out.println(res);
    }

    private Integer[][][][] mem;

    public int maxProfit(int[] prices) {
        mem = new Integer[prices.length][5][2][100000];
        return maxProfitInternal(prices, 0, 0, 0);
    }

    private int maxProfitInternal(int[] prices, int day, int status, int currFund) {
        if (day >= prices.length || status >= 4) {
            //两次交易都完成，或者天数已耗尽
            return currFund;
        }

        int mark = currFund >= 0 ? 1 : 0;
        int pureFund = Math.abs(currFund);

        if (mem[day][status][mark][pureFund] != null) {
            return mem[day][status][mark][pureFund];
        }

        if (status == 0 || status == 2) {
            //交易前
            //此时买入
            int profit1 = maxProfitInternal(prices, day + 1, status + 1, currFund - prices[day]);
            //此时不买入
            int profit2 = maxProfitInternal(prices, day + 1, status, currFund);
            mem[day][status][mark][pureFund] = Math.max(profit1, profit2);
            return mem[day][status][mark][pureFund];
        } else {
            //交易中
            //此时卖出
            int profit1 = maxProfitInternal(prices, day + 1, status + 1, currFund + prices[day]);
            //此时不卖出
            int profit2 = maxProfitInternal(prices, day + 1, status, currFund);
            mem[day][status][mark][pureFund] = Math.max(profit1, profit2);
            return mem[day][status][mark][pureFund];
        }
    }

    //todo: 以上是我的解答，内存超限了

    //以下官方DP解法

    //状态转移表：
    //天数的下标\状态  |买入0次 |买入1次 |卖出1次 |再次买入1次|再次卖出1次
    //      0       |-------|-------|-------|---------|-----------
    //      1       |-------|-------|-------|---------|-----------

    public int maxProfitOfficial(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }


}
