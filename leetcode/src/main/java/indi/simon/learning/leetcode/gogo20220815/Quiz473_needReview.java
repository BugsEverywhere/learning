package indi.simon.learning.leetcode.gogo20220815;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz473_needReview {

    public static void main(String[] args) {
        Quiz473_needReview quiz473NeedReview = new Quiz473_needReview();
        boolean res = quiz473NeedReview.makesquare(new int[]{1, 5, 3, 3, 3, 3, 4, 2});
        System.out.println(res);
    }

    private Boolean[] statusMem;
    private int per;

    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        int max = 0;
        for (int match : matchsticks) {
            sum += match;
            max = Math.max(match, max);
        }

        if (sum % 4 != 0) {
            return false;
        }
        per = sum / 4;
        Arrays.sort(matchsticks);
        int statusCount = 1 << matchsticks.length;
        statusMem = new Boolean[statusCount];
        return dfs((1 << matchsticks.length) - 1, 0, matchsticks);
    }

    private boolean dfs(int status, int sideSoFar, int[] matchsticks) {
        if (status == 0) {
            return true;
        }
        if (statusMem[status] != null) {
            return statusMem[status];
        }
        statusMem[status] = false;
        for (int i = 0; i < matchsticks.length; i++) {
            if (sideSoFar + matchsticks[i] > per) {
                break;
            }
            if (((status >> i) & 1) != 0) {
                boolean res = dfs(status ^ (1 << i), (sideSoFar + matchsticks[i]) % per, matchsticks);
                if (res) {
                    statusMem[status] = true;
                    return true;
                }
            }
        }
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



