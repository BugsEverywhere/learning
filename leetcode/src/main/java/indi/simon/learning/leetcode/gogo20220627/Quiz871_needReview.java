package indi.simon.learning.leetcode.gogo20220627;


/**
 * @author chenzhuo(zhiyue)
 */
//todo: 第一个难点是状态的抽象，本题如果陷入了正向思维的陷阱就比较麻烦了，比如将状态抽象为：到达每一个加油站所需要的最小加油次数。。。。
// 正确的做法是，将dp数组定义为，加i次油所能到达的最远距离，这才是成功的第一步，然后才考虑后续的状态转移
public class Quiz871_needReview {

    public static void main(String[] args) {
        Quiz871_needReview quiz871NeedReview = new Quiz871_needReview();
        int[][] stations = new int[][]{{47, 220}, {65, 1}, {98, 113}, {126, 196}, {186, 218}, {320, 205}, {686, 317}, {707, 325}, {754, 104}, {781, 105}};
        int res = quiz871NeedReview.minRefuelStops(1000, 83, stations);
        System.out.println(res);
    }

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int stationCount = stations.length;
        //todo: dp[i]代表加i次油可以到达的最远距离，所以dp[i]和stations[i]具有比较关系，也就是说，我加i次油，是否能到达第i个加油站，来决定dp[i+1]的值
        long[] dp = new long[stationCount + 1];
        dp[0] = startFuel;
        for (int i = 0; i < stationCount; i++) {
            //todo: 这就是这个动态规划的特殊之处，以往的动态规划的状态转移都是根据之前的状态来决定本次状态的值即可，
            // 这里每遍历到下一个加油站i，在更新本阶段状态的同时，还需要往回倒，看看之前的状态是否需要更新
            for (int j = i; j >= 0; j--) {
                if (dp[j] >= stations[i][0]) {
                    dp[j + 1] = Math.max(dp[j + 1], dp[j] + stations[i][1]);
                }
            }
        }
        for (int i = 0; i <= stationCount; i++) {
            if (dp[i] >= target) {
                return i;
            }
        }
        return -1;
    }

}
