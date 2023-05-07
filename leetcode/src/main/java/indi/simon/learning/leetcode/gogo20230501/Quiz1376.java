package indi.simon.learning.leetcode.gogo20230501;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1376 {

    public static void main(String[] args) {
        Quiz1376 quiz1376 = new Quiz1376();
        int res = quiz1376.numOfMinutes(6, 2, new int[]{2, 2, -1, 2, 2, 2}, new int[]{0, 0, 1, 0, 0, 0});
        System.out.println(res);
    }

    private int res;
    //用于记录自底向上统计到某员工时耗时最长的时间
    private int[] mem;

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        res = Integer.MIN_VALUE;
        mem = new int[n];
        for (int i = 0; i < n; i++) {
            timeCost(i, manager, informTime, 0);
        }
        return res;
    }

    /**
     * 自底向上计算总耗时
     *
     * @param currStaff
     * @param manager
     * @param informTime
     * @param costSoFar
     * @return
     */
    private void timeCost(int currStaff, int[] manager, int[] informTime, int costSoFar) {
        if (manager[currStaff] == -1) {
            //到顶了，时间计算一下返回
            res = Math.max(costSoFar, res);
            return;
        }
        if (mem[currStaff] > costSoFar) {
            //已经统计过该节点耗时更长的链路了，返回
            return;
        }
        mem[currStaff] = costSoFar;

        //递归到他的领导
        timeCost(manager[currStaff], manager, informTime, costSoFar + informTime[manager[currStaff]]);
    }

}
