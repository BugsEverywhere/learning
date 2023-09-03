package indi.simon.learning.leetcode.gogo20230828;

import java.util.Arrays;

/**
 * Created by Chen Zhuo on 2023/9/3.
 */
public class Quiz1921 {

    public static void main(String[] args) {
        int[] dist = new int[]{4, 2, 8};
        int[] speed = new int[]{2, 1, 4};
        Quiz1921 quiz1921 = new Quiz1921();
        int res = quiz1921.eliminateMaximum(dist, speed);
        System.out.println(res);
    }


    public int eliminateMaximum(int[] dist, int[] speed) {
        //minutes[i]表示第i个怪物第几分钟将到达城市
        int[] minutes = new int[dist.length];
        int max = 0;
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] % speed[i] == 0) {
                minutes[i] = dist[i] / speed[i];
            } else {
                minutes[i] = dist[i] / speed[i] + 1;
            }
            max = Math.max(max, minutes[i]);
        }

        Arrays.sort(minutes);

        //p[m]表示第m分钟以及之前将到达的怪物数量，一旦出现p[m]>m的情况，忙不过来，将输掉，且最多击杀m只怪物，
        // 因为到m分钟你只有m次机会，第m分钟时才能施展第m+1次机会
        int[] p = new int[max + 1];
        //currMinute为p的下标，也就是计算p的每一分钟，所以currMinute需要递增来统计p[currMinute]
        int currMinute = 1;
        for (int i = 0; i < minutes.length && currMinute <= max; ) {
            //因为minutes排序过，那么minutes[i]如果不等于currMinute的话，那只可能大于currMinute，
            // 如果是后者，那么增加currMinute，并且做好状态转移即可
            if (minutes[i] == currMinute) {
                p[currMinute]++;
                //todo: 注意此处如果currMinute没有递增，i才需要自增，不然会漏掉某个怪物
                i++;
            } else {
                //todo: 如果currMinute自增，需要做状态转移
                currMinute++;
                p[currMinute] += p[currMinute - 1];
            }

            if (p[currMinute] > currMinute) {
                return currMinute;
            }
        }

        return dist.length;
    }

}
