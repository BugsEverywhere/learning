package indi.simon.learning.leetcode.知识性的;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1238_reviewed_格雷码 {

    public static void main(String[] args) {
        Quiz1238_reviewed_格雷码 quiz1238_reviewed格雷码 = new Quiz1238_reviewed_格雷码();
        List<Integer> res = quiz1238_reviewed格雷码.circularPermutation(3, 2);
        System.out.println(res);
    }

    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> ret = new ArrayList<Integer>();
        ret.add(start);
        for (int i = 1; i <= n; i++) {
            int m = ret.size();
            for (int j = m - 1; j >= 0; j--) {
                ret.add(((ret.get(j) ^ start) | (1 << (i - 1))) ^ start);
            }
        }
        return ret;
    }


}
