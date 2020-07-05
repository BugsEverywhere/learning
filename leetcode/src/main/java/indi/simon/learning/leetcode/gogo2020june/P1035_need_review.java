package indi.simon.learning.leetcode.gogo2020june;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P1035_need_review {

    public static void main(String[] args) {

        int[] aArr = new int[]{3, 1, 4, 1, 1, 3, 5, 1, 2, 2};
        int[] bArr = new int[]{4, 1, 5, 2, 1, 1, 1, 5, 3, 1, 1, 1, 2, 3, 1, 4, 3, 5, 5, 3, 1, 2, 3, 2, 4, 1, 1, 1, 5, 3};

        int res = maxUncrossedLines(aArr, bArr);

        System.out.println(res);
    }


    //别人的解法，动态规划：
    //状态转移方程：

    public static int maxUncrossedLines111(int[] a, int[] b) {
        //故意把两个维度的长度定义多一个，省去开始时判断边界条件，这样dp数组中dp[i][j]对应的其实是a[i-1]和b[j-1]的情况
        int[][] dp = new int[a.length + 1][b.length + 1];
        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {

                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }

            }
        }
        return dp[a.length][b.length];
    }


    //我的解法，回溯，穷举，超时。。。。。
    //todo:

    private static int maxSize;

    private static int maxUncrossedLines(int[] a, int[] b) {

        maxSize = Integer.MIN_VALUE;

        Map<Integer, List<Integer>> mapB = new HashMap<>();
        for (int i = 0; i < b.length; i++) {
            if (mapB.get(b[i]) == null) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                mapB.put(b[i], list);
            } else {
                mapB.get(b[i]).add(i);
            }
        }

        Map<Integer, Integer> map = new HashMap<>();

        maxUncrossedLinesInternal(0, 0, a, b, map, mapB);

        return maxSize;

    }

    private static void maxUncrossedLinesInternal(int bStartIndex, int aIndex, int[] a, int[] b, Map<Integer, Integer> tuples, Map<Integer, List<Integer>> mapB) {
        if (aIndex >= a.length || bStartIndex >= b.length) {
            if (tuples.size() >= maxSize) {
                if (tuples.size() == 9) {
                    System.out.println(tuples.size());
                }
                maxSize = tuples.size();
            }
            return;
        }

        int anum = a[aIndex];

        //B中没有这个数,直接往下递归
        if (mapB.get(anum) == null) {
            maxUncrossedLinesInternal(bStartIndex, aIndex + 1, a, b, new HashMap<>(tuples), mapB);
        } else {
            //B中有这个数，取下标最小者与A相连
            List<Integer> bIndexes = mapB.get(anum);
            for (Integer bIndex : bIndexes) {
                if (bIndex >= bStartIndex) {
                    Map<Integer, Integer> newTuples = new HashMap<>(tuples);
                    newTuples.put(aIndex, bIndex);
                    maxUncrossedLinesInternal(bIndex + 1, aIndex + 1, a, b, newTuples, mapB);
                    break;
                }
            }

            //B中的下标最小者都小于当前的BstartIndex，以及选择此数不连的情况，往下递归
            maxUncrossedLinesInternal(bStartIndex, aIndex + 1, a, b, new HashMap<>(tuples), mapB);
        }

    }

}
