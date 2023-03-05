package indi.simon.learning.leetcode.gogo20230227;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1599 {

    public static void main(String[] args) {
        Quiz1599 quiz1599 = new Quiz1599();
        int res = quiz1599.minOperationsMaxProfit(new int[]{3, 4, 0, 5, 1}, 1, 92);
        System.out.println(res);
    }

    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int queueing = customers[0];
        int currProfit = 0;
        int maxProfit = Integer.MIN_VALUE;
        int res = -1;
        int i = 0;
        while (i < customers.length || queueing > 0) {
            int currPassengers;
            if (queueing > 4) {
                currPassengers = 4;
                queueing = queueing - 4;
            } else {
                currPassengers = queueing;
                queueing = 0;
            }

            currProfit = currProfit + currPassengers * boardingCost - runningCost;
            if (currProfit > maxProfit) {
                maxProfit = currProfit;
                if (maxProfit > 0) {
                    res = i + 1;
                }
            }
            i++;
            if (i < customers.length) {
                queueing += customers[i];
            }
        }

        return res;
    }

}
