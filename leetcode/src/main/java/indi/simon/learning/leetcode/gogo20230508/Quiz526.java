package indi.simon.learning.leetcode.gogo20230508;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz526 {

    public static void main(String[] args) {
        Quiz526 quiz526 = new Quiz526();
        int res = quiz526.countArrangement(1);
        System.out.println(res);
    }

    public int countArrangement(int n) {
        //dp[i]表示整数i可以构造的优美排列的所有n位数字状态
        List[] dp = new ArrayList[n + 1];
        dp[1] = new ArrayList();
        //因为对第一位来说，1~n的所有数都能整除1，所以dp[1]需要加上所有这些数
        for (int i = 1; i <= n; i++) {
            dp[1].add(1 << (n - i));
        }

        for (int i = 2; i <= n; i++) {
            dp[i] = new ArrayList();
            //观察前一位的所有状态，进行状态转移
            for (Object jStatus : dp[i - 1]) {
                //基于当前状态，找到一个当前i可用的整数
                for (int k = 1; k <= n; k++) {
                    //检查整数k是否已经被占用，如果等于1就是jStatus在该位已经是1，即k已经被占用
                    if ((((Integer) jStatus >> (n - k)) & 1) == 1) {
                        continue;
                    }
                    if (k % i == 0 || i % k == 0) {
                        //找到了k，将k标记为已使用状态
                        int newStatus = (Integer) jStatus | (1 << (n - k));
                        dp[i].add(newStatus);
                    }
                }
            }
        }

        return dp[n].size();
    }

}
