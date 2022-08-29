package indi.simon.learning.leetcode.gogo20220815;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz473_needReview {

    public static void main(String[] args) {
        Quiz473_needReview quiz473NeedReview = new Quiz473_needReview();
        boolean res = quiz473NeedReview.makeSquareRecursive(new int[]{2, 2, 2, 2, 2, 6});
        System.out.println(res);
    }

    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        Map<Integer, Integer> stickCountMap = new HashMap<>();
        for (int singleMatch : matchsticks) {
            sum += singleMatch;
            stickCountMap.merge(singleMatch, 1, Integer::sum);
        }
        if (sum % 4 != 0) {
            return false;
        }
        int singleSide = sum / 4;

        //dp[i][j]代表遍历到第i根火柴时是否可以拼出长度j
        boolean[][] dp = new boolean[matchsticks.length][sum + 1];
        dp[0][matchsticks[0]] = true;


        return false;
    }


    //todo 自己的记忆化回溯写法，内存超了

    private Boolean[][] mem;

    public boolean makeSquareRecursive(int[] matchsticks) {
        int sum = 0;
        for (int singleMatch : matchsticks) {
            sum += singleMatch;
        }
        if (sum % 4 != 0) {
            return false;
        }
        int singleSide = sum / 4;
        mem = new Boolean[sum + 1][(int) Math.pow(2, matchsticks.length)];
        return makeSquareRecursiveInternal(sum, singleSide, matchsticks, 0, new boolean[4], 0);
    }

    private boolean makeSquareRecursiveInternal(int totalLength, int singleSideLength, int[] matchsticks, int lengthSoFar, boolean[] sideMark, int sticksBitMark) {
        if (lengthSoFar / singleSideLength >= 1) {
            //如果已经超过单边长，那么校验一下最近节点是否为true，如果不是，说明最近节点没踩中，返回false
            boolean passRes = true;
            for (int i = lengthSoFar / singleSideLength - 1; i >= 0; i--) {
                passRes = passRes && sideMark[i];
            }
            if (!passRes) {
                return false;
            }
        }

        if (lengthSoFar == totalLength) {
            //到达终点
            return true;
        }

        if (mem[lengthSoFar][sticksBitMark] != null) {
            return mem[lengthSoFar][sticksBitMark];
        }

        boolean res = false;
        for (int i = 0; i < matchsticks.length; i++) {
            if (matchsticks[i] == -1) {
                //已经被标记为用过的火柴
                continue;
            }
            int[] newMatchSticks = new int[matchsticks.length];
            System.arraycopy(matchsticks, 0, newMatchSticks, 0, matchsticks.length);
            int temp = newMatchSticks[i];
            newMatchSticks[i] = -1;
            int newLengthSoFar = lengthSoFar + temp;
            boolean[] newSideMark = new boolean[4];
            System.arraycopy(sideMark, 0, newSideMark, 0, sideMark.length);
            if (newLengthSoFar != 0 && newLengthSoFar % (totalLength / 4) == 0) {
                //如果正好是单边长度，那么就把该单边长节点置位
                newSideMark[newLengthSoFar / singleSideLength - 1] = true;
            }
            res = res || makeSquareRecursiveInternal(totalLength, singleSideLength, newMatchSticks, newLengthSoFar, newSideMark, sticksBitMark | 1 << i);
        }

        mem[lengthSoFar][sticksBitMark] = res;
        return res;
    }


}



