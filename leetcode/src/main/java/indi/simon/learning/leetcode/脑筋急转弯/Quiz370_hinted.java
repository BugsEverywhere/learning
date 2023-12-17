package indi.simon.learning.leetcode.脑筋急转弯;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Chen Zhuo on 2023/12/17.
 */
public class Quiz370_hinted {

    public static void main(String[] args) {
        Quiz370_hinted quiz370Hinted = new Quiz370_hinted();
        int[] res = quiz370Hinted.getModifiedArrayV2(5, new int[][]{{1, 3, 2}, {2, 4, 3}, {0, 2, -2}});
        System.out.println(res);
    }

    //todo: 差分数组，O(n)解法，使用递推的方式，求res[i]是通过res[i-1]来实现的
    public int[] getModifiedArrayV2(int length, int[][] updates) {
        int[] diff = new int[length];
        int[] ans = new int[length];
        for (int[] input : updates) {
            int start = input[0];
            int end = input[1];
            diff[start] += input[2];
            if (end < length - 1) {
                diff[end + 1] -= input[2];
            }
        }
        ans[0] = diff[0];
        for (int i = 1; i < length; i++) {
            ans[i] = ans[i - 1] + diff[i];
        }
        return ans;
    }


    //todo: 自己的暴力求解，虽然过了但是复杂度较高
    public int[] getModifiedArray(int length, int[][] updates) {

        Arrays.sort(updates, Comparator.comparingInt(o -> o[0]));

        int[] res = new int[length];

        for (int i = 0; i < length; i++) {
            for (int[] update : updates) {
                if (i > update[1] || i < update[0]) {
                    continue;
                }
                res[i] += update[2];
            }
        }

        return res;
    }

}
