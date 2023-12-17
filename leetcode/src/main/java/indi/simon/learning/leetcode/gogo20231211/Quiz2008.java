package indi.simon.learning.leetcode.gogo20231211;

import java.util.*;

/**
 * Created by Chen Zhuo on 2023/12/17.
 */
public class Quiz2008 {

    public static void main(String[] args) {
        Quiz2008 quiz2008 = new Quiz2008();
        int[][] rides = new int[][]{{9, 10, 2}, {4, 5, 6}, {6, 8, 1}, {1, 5, 5}, {4, 9, 5}, {1, 6, 5}, {4, 8, 3}, {4, 7, 10}, {1, 9, 8}, {2, 3, 5}};
        long res = quiz2008.maxTaxiEarnings(10, rides);
        System.out.println(res);
    }

    public long maxTaxiEarnings(int n, int[][] rides) {
        //key为每一程终点，val为该终点处的所有ride
        Map<Integer, List<int[]>> map = new HashMap<>();
        //先将rides中的ride[2]改写为实际盈利
        for (int[] ride : rides) {
            ride[2] = ride[1] - ride[0] + ride[2];
            List<int[]> list = map.getOrDefault(ride[1], new ArrayList<>());
            list.add(ride);
            map.put(ride[1], list);
        }

        //开始dp，dp[i]代表行驶到i地点时的最大盈利
        long[] dp = new long[n + 1];
        long res = 0;
        for (int i = 1; i <= n; i++) {
            //取出以i为终点的所有ride
            List<int[]> list = map.get(i);
            //todo:此处注意，dp[i]要累积一下dp[i-1]的状态，因为dp[i-1]已经是前一步的最大化盈利了，不能忽略
            dp[i] = Math.max(dp[i], dp[i - 1]);
            if (list != null) {
                for (int[] ride : list) {
                    //todo:此处注意，因为此前的状态已经在上面转移过来了（上面那个max），此处就不需要考虑ride[0]之前的盈利情况了
                    dp[i] = Math.max(dp[i], dp[ride[0]] + ride[2]);
                }
            }
            res = Math.max(dp[i], res);
        }

        return res;
    }


}
