package indi.simon.learning.leetcode.gogo20220418;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz123_timeExceed {

    public static void main(String[] args) {
        Quiz123_timeExceed quiz123 = new Quiz123_timeExceed();
        int[] prices = new int[]{3,3,5,0,0,3,1,4};
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


}
