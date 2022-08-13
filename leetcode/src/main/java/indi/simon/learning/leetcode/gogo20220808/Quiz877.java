package indi.simon.learning.leetcode.gogo20220808;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 基本上跟464以及486一个路数，这次DP自己写出来了，不戳不戳
public class Quiz877 {

    public static void main(String[] args) {
        Quiz877 quiz877 = new Quiz877();
        boolean res = quiz877.stoneGameDp(new int[]{5, 3, 4, 5});
        System.out.println(res);
    }

    //记忆化回溯，超时

    Integer[][][][] mem;

    public boolean stoneGame(int[] piles) {

        int sum = 0;
        for (int i = 0; i < piles.length; i++) {
            sum += piles[i];
        }
        mem = new Integer[piles.length][piles.length][sum + 1][2];

        int aliceRes = stoneGameInternal(piles, 0, piles.length - 1, 0, true);
        return aliceRes > sum - aliceRes;
    }

    private int stoneGameInternal(int[] piles, int left, int right, int aliceTotal, boolean mark) {
        if (left > right) {
            return aliceTotal;
        }

        if (mem[left][right][aliceTotal][mark ? 1 : 0] != null) {
            return mem[left][right][aliceTotal][mark ? 1 : 0];
        }

        //Alice选左边
        int leftRes = stoneGameInternal(piles, left + 1, right, mark ? aliceTotal + piles[left] : aliceTotal, !mark);

        //Alice选右边
        int rightRes = stoneGameInternal(piles, left, right - 1, mark ? aliceTotal + piles[right] : aliceTotal, !mark);

        int res = Math.max(leftRes, rightRes);
        mem[left][right][aliceTotal][mark ? 1 : 0] = res;

        return res;

    }

    //自己的dp


    public boolean stoneGameDp(int[] piles) {

        int sum = 0;
        for (int i = 0; i < piles.length; i++) {
            sum += piles[i];
        }

        int[][] dp = new int[piles.length][piles.length];

        for (int i = 0; i < piles.length; i++) {
            dp[i][i] = piles[i];
        }

        for (int i = piles.length - 2; i >= 0; i--) {
            for (int j = piles.length - 1; j >= 1; j--) {
                if (i > j) {
                    break;
                }
                dp[i][j] = Math.max(dp[i + 1][j] + piles[i], dp[i][j - 1] + piles[j]);
            }
        }

        return dp[0][piles.length - 1] > sum - dp[0][piles.length - 1];
    }


}
