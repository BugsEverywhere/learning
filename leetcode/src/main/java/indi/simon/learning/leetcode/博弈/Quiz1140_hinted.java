package indi.simon.learning.leetcode.博弈;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 此题也是博弈的一种，难就难在dfs时候，每一层返回值的定义，因为每次博弈都是考虑从当前时刻起，往后的情况如何对自己有利，
// 因此dfs的返回值应该是当前及以后对自己最有利的结果，在本题中也就是从currIndex这一堆石子开始，怎么拿才能拿到剩下的石子里面最多的
// 而不是直接返回最终的结果，返回最终结果那种做法不是博弈，那是求值问题
public class Quiz1140_hinted {

    public static void main(String[] args) {
        Quiz1140_hinted quiz1140Hinted = new Quiz1140_hinted();
        int res = quiz1140Hinted.stoneGameIIDp(new int[]{2, 7, 9, 4, 4});
        System.out.println(res);
    }

    private int[][] mem;

    public int stoneGameII(int[] piles) {
        mem = new int[piles.length][piles.length + 1];
        for (int i = 0; i < mem.length; i++) {
            for (int j = 0; j < mem[0].length; j++) {
                mem[i][j] = -1;
            }
        }
        return stoneGameIIInternal(piles, 0, 1);
    }

    /**
     * @param piles
     * @param currIndex
     * @param m
     * @return 返回当前选手从剩下的堆里能获得的最多石头，不管当前选手是谁
     */
    private int stoneGameIIInternal(int[] piles, int currIndex, int m) {
        if (currIndex >= piles.length) {
            return 0;
        }
        if (mem[currIndex][m] != -1) {
            return mem[currIndex][m];
        }
        int leftTotal = 0;
        for (int i = currIndex; i < piles.length; i++) {
            leftTotal += piles[i];
        }
        int minEnemyCount = Integer.MAX_VALUE;
        for (int i = currIndex; i < currIndex + 2 * m && i < piles.length; i++) {
            //当前选手拿完从currIndex到i的这么多堆
            int x = i - currIndex + 1;
            int tryOnce = stoneGameIIInternal(piles, i + 1, Math.max(x, m));
            minEnemyCount = Math.min(minEnemyCount, tryOnce);
        }
        mem[currIndex][m] = leftTotal - minEnemyCount;
        return mem[currIndex][m];
    }


    //别人的DP
    public int stoneGameIIDp(int[] piles) {
        int len = piles.length, sum = 0;
        //dp[i][j]表示剩余[i]堆时，M = j的情况下，先取的人能获得的最多石子数
        int[][] dp = new int[len][len + 1];
        //技巧，i从后往前遍历，因为i大的情况能确定作为初始条件，所以要先确定i大的情况，再反向递推i小的情况
        for (int i = len - 1; i >= 0; i--) {
            sum += piles[i];
            for (int M = 1; M <= len; M++) {
                if (i + 2 * M >= len) {
                    //如果i+2*M比剩余数组长度还大了，那肯定通吃最划算
                    dp[i][M] = sum;
                } else {
                    //否则，取下一个人从i往后能拿到的最少的情况，也就是dp[i + x][Math.max(M, x)]的最小值
                    for (int x = 1; x <= 2 * M; x++) {
                        dp[i][M] = Math.max(dp[i][M], sum - dp[i + x][Math.max(M, x)]);
                    }
                }
            }
        }
        return dp[0][1];
    }

}
