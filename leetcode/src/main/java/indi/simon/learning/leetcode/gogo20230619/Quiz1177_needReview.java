package indi.simon.learning.leetcode.gogo20230619;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1177_needReview {

    public static void main(String[] args) {
        Quiz1177_needReview quiz1177NeedReview = new Quiz1177_needReview();
        List<Boolean> res = quiz1177NeedReview.canMakePaliQueries("hunu", new int[][]{{1, 1, 1}, {2, 3, 0}, {3, 3, 1}, {0, 3, 2}, {1, 3, 3}, {2, 3, 1}, {3, 3, 1}, {0, 3, 0}, {1, 1, 1}, {2, 3, 0}, {3, 3, 1}, {0, 3, 1}, {1, 1, 1}});
        System.out.println(res);
    }

    //todo: 超时
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        List<Boolean> res = new ArrayList<>();
        for (int[] arr : queries) {
            int left = arr[0];
            int right = arr[1];
            int k = arr[2];

            Map<Character, Integer> cCnt = new HashMap<>();
            for (int i = left; i <= right; i++) {
                cCnt.merge(s.charAt(i), 1, Integer::sum);
            }

            boolean isOk = true;
            int oddCharCnt = 0;
            for (Map.Entry<Character, Integer> entry : cCnt.entrySet()) {
                if (entry.getValue() % 2 != 0) {
                    oddCharCnt++;
                }
            }

            if ((right - left + 1) % 2 == 0) {
                if (oddCharCnt / 2 > k) {
                    isOk = false;
                }
            } else {
                if ((oddCharCnt - 1) / 2 > k) {
                    isOk = false;
                }
            }

            res.add(isOk);
        }

        return res;
    }

    //todo: 官方前缀数组
    public List<Boolean> canMakePaliQueriesOfficial(String s, int[][] queries) {
        int n = s.length();
        int[] count = new int[n + 1];
        for (int i = 0; i < n; i++) {
            count[i + 1] = count[i] ^ (1 << (s.charAt(i) - 'a'));
        }
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1], k = queries[i][2];
            int bits = 0, x = count[r + 1] ^ count[l];
            while (x > 0) {
                x &= x - 1;
                bits++;
            }
            res.add(bits <= k * 2 + 1);
        }
        return res;
    }


}
