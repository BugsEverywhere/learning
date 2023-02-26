package indi.simon.learning.leetcode.gogo20230213;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1124_needReview {

    public static void main(String[] args) {
        Quiz1124_needReview quiz1124NeedReview = new Quiz1124_needReview();
        int res = quiz1124NeedReview.longestWPIOfficial(new int[]{9, 9, 6, 0, 6, 6, 9});
        System.out.println(res);
    }

    //todo: 官方的贪心
    public int longestWPIOfficial(int[] hours) {
        int n = hours.length;
        int[] s = new int[n + 1];
        Deque<Integer> stk = new ArrayDeque<Integer>();
        stk.push(0);
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + (hours[i - 1] > 8 ? 1 : -1);
            if (s[stk.peek()] > s[i]) {
                stk.push(i);
            }
        }
        int res = 0;
        for (int r = n; r >= 1; r--) {
            while (!stk.isEmpty() && s[stk.peek()] < s[r]) {
                res = Math.max(res, r - stk.pop());
            }
        }
        return res;
    }

    //todo: 官方的哈希表
    public int longestWPIOfficialV2(int[] hours) {
        int n = hours.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int s = 0, res = 0;
        for (int i = 0; i < n; i++) {
            s += hours[i] > 8 ? 1 : -1;
            if (s > 0) {
                res = Math.max(res, i + 1);
            } else {
                if (map.containsKey(s - 1)) {
                    res = Math.max(res, i - map.get(s - 1));
                }
            }
            if (!map.containsKey(s)) {
                map.put(s, i);
            }
        }
        return res;
    }


    //todo: 穷举，反正也没超时，但是这么做出来在面试场也会被鄙视，所以不能算
    public int longestWPI(int[] hours) {
        int res = 0;

        for (int i = 0; i < hours.length; i++) {
            int tiredCount = 0;
            for (int j = i; j < hours.length; j++) {
                if (hours[j] > 8) {
                    tiredCount++;
                }

                if (tiredCount > (j - i + 1) / 2) {
                    res = Math.max(res, j - i + 1);
                }

            }
        }

        return res;
    }

}
