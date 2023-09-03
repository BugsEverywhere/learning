package indi.simon.learning.leetcode.gogo20230828;

/**
 * Created by Chen Zhuo on 2023/9/3.
 */
public class Quiz2240 {

    public static void main(String[] args) {
        Quiz2240 quiz2240 = new Quiz2240();
        long res = quiz2240.waysToBuyPensPencils(5,10,10);
        System.out.println(res);
    }

    public long waysToBuyPensPencils(int total, int cost1, int cost2) {

        int maxCost = Math.max(cost1, cost2);
        int minCost = Math.min(cost1, cost2);

        long cnt = 0;
        //先计算能购买的贵的那种的数目，再计算每种购买的贵的数目的基础上能购买的便宜的那种的数目
        int expensiveCnt = total / maxCost;
        for (int i = 0; i <= expensiveCnt; i++) {
            int remainTotal = total - maxCost * i;
            int cheapCnt = remainTotal / minCost;
            cnt += (cheapCnt + 1);
        }

        return cnt;
    }
}
