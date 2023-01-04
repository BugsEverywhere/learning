package indi.simon.learning.leetcode.gogo20230103;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1801 {

    public static void main(String[] args) {
        Quiz1801 quiz1801 = new Quiz1801();
        int res = quiz1801.getNumberOfBacklogOrders(new int[][]{
                {23, 8, 0},
                {28, 29, 1},
                {11, 30, 1},
                {30, 25, 0},
                {26, 9, 0}, {3, 21, 0}, {28, 19, 1}, {19, 30, 0}, {20, 9, 1}, {17, 6, 0}});
        System.out.println(res);
    }

    public int getNumberOfBacklogOrders(int[][] orders) {
        PriorityQueue<int[]> bigHeapBuy = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[0], o1[0]));
        PriorityQueue<int[]> smallHeapSell = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));

        for (int[] singleBatch : orders) {
            if (singleBatch[2] == 0) {
                //采购订单
                while (smallHeapSell.size() > 0 && smallHeapSell.peek()[0] <= singleBatch[0]) {
                    //可以匹配
                    if (smallHeapSell.peek()[1] > singleBatch[1]) {
                        //销售订单更多
                        smallHeapSell.peek()[1] -= singleBatch[1];
                        singleBatch[1] = 0;
                        break;
                    } else if (smallHeapSell.peek()[1] < singleBatch[1]) {
                        //采购订单更多
                        singleBatch[1] = singleBatch[1] - smallHeapSell.peek()[1];
                        smallHeapSell.poll();
                    } else {
                        //同归于尽
                        smallHeapSell.poll();
                        singleBatch[1] = 0;
                        break;
                    }
                }
                if (singleBatch[1] > 0) {
                    bigHeapBuy.offer(singleBatch);
                }
            } else {
                //销售订单
                while (bigHeapBuy.size() > 0 && bigHeapBuy.peek()[0] >= singleBatch[0]) {
                    //可以匹配
                    if (bigHeapBuy.peek()[1] > singleBatch[1]) {
                        //采购订单更多
                        bigHeapBuy.peek()[1] -= singleBatch[1];
                        //todo: 要记得对本批次订单数清零
                        singleBatch[1] = 0;
                        break;
                    } else if (bigHeapBuy.peek()[1] < singleBatch[1]) {
                        //销售订单更多
                        singleBatch[1] = singleBatch[1] - bigHeapBuy.peek()[1];
                        bigHeapBuy.poll();
                    } else {
                        //同归于尽
                        bigHeapBuy.poll();
                        //todo: 同归于尽的时候也要记得对本批次订单数清零
                        singleBatch[1] = 0;
                        break;
                    }
                }
                //todo: 本批次的订单需要最终看是否积压
                if (singleBatch[1] > 0) {
                    smallHeapSell.offer(singleBatch);
                }
            }
        }

        long res = 0;

        for (int[] singleBuyOrder : bigHeapBuy) {
            res += singleBuyOrder[1];
        }

        for (int[] singleSellOrder : smallHeapSell) {
            res += singleSellOrder[1];
        }

        return (int) (res % (Math.pow(10, 9) + 7));

    }


}
