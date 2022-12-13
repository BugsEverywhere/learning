package indi.simon.learning.leetcode.gogo20221212;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1780_reviewed {

    public static void main(String[] args) {
        Quiz1780_reviewed quiz1780Reviewed = new Quiz1780_reviewed();
        boolean res = quiz1780Reviewed.checkPowersOfThreeOfficial(3441848);
        System.out.println(res);
    }

    //todo: 官方进制转换，有意思，将一个数n转成3进制之后，这样就相当于可以用n= x * 3的0次幂 + y * 3的1次幂 + ...... 来表示了，
    // 一旦x或者y这样的系数等于2，那么就意味着这一个次幂在组成这个数的时候要加两次，不满足题目要求
    public boolean checkPowersOfThreeOfficial(int n) {
        while (n != 0) {
            if (n % 3 == 2) {
                return false;
            }
            n /= 3;
        }
        return true;
    }


    //todo: 自己的dp，超时
    public boolean checkPowersOfThree(int n) {

        Map<Integer, Set<Integer>> usedPowMap = new HashMap<>();
        boolean[] dp = new boolean[n + 1];

        dp[1] = true;
        Set<Integer> oneSet = new HashSet<>();
        oneSet.add(0);
        usedPowMap.put(1, oneSet);

        for (int i = 2; i <= n; i++) {
            for (int j = 1; ; ) {
                int next = i - (int) Math.pow(3, j);
                if (next < 0) {
                    break;
                }
                if (next == 0) {
                    dp[i] = true;
                    Set<Integer> iSet = new HashSet<>();
                    iSet.add(j);
                    usedPowMap.put(i, iSet);
                    break;
                }
                if (dp[next] && !usedPowMap.get(next).contains(j)) {
                    dp[i] = true;
                    Set<Integer> iSet = new HashSet<>(usedPowMap.get(next));
                    iSet.add(j);
                    usedPowMap.put(i, iSet);
                    break;
                }
                j++;
            }
        }

        return dp[n];
    }

}
