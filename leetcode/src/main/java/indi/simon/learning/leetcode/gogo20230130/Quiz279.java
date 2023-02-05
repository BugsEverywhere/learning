package indi.simon.learning.leetcode.gogo20230130;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz279 {

    public static void main(String[] args) {
        Quiz279 quiz279 = new Quiz279();
        int res = quiz279.numSquaresDp(43);
        System.out.println(res);
    }

    //todo: 自己的dp，哈哈阔以嘛小伙子
    public int numSquaresDp(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 10);
        //dp[i]代表合成i需要最少多少个完全平方数相加
        dp[1] = 1;
        //todo: 需要注意往后状态转移时需要一直考虑到dp[0]，以为有的数他自己就是完全平方数，比如4，这样只需要dp[0]=0，那么他从0的基础上加一个他自己就完事儿了
        dp[0] = 0;

        for (int i = 2; i <= n; i++) {
            int j = 1;
            int num = i - j * j;
            while (num >= 0) {
                dp[i] = Math.min(dp[i], dp[num] + 1);
                j++;
                num = i - j * j;
            }
        }

        return dp[n];
    }


    //todo: 自己的递归，超时
    private int numCount = Integer.MAX_VALUE;

    public int numSquares(int n) {
        if (n == 1) {
            return 1;
        }
        int upperLimit = 0;
        for (int i = 1; i <= 100; i++) {
            if (i * i == n) {
                return 1;
            }
            if (i * i > n) {
                upperLimit = i - 1;
                break;
            }
        }
        for (int i = upperLimit; i >= 1; i--) {
            countNum(n, i, 0, 0);
        }

        return numCount;
    }

    private void countNum(int n, int curr, int sum, int currCount) {
        sum += curr * curr;
        currCount++;
        if (sum == n) {
            numCount = Math.min(numCount, currCount);
        } else if (sum < n) {
            for (int i = curr; i >= 1; i--) {
                countNum(n, i, sum, currCount);
            }
        }
    }


}
