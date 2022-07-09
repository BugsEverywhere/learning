package indi.simon.learning.leetcode.gogo20220627;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1200 {

    public static void main(String[] args) {
        Quiz1200 quiz1200 = new Quiz1200();
        int[] arr = new int[]{3, 8, -10, 23, 19, -4, -14, 27};
        List<List<Integer>> res = quiz1200.minimumAbsDifference(arr);
        System.out.println(res);
    }

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        if (arr.length <= 1) {
            return null;
        }
        Arrays.sort(arr);
        int minGap = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            minGap = Math.min(arr[i + 1] - arr[i], minGap);
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] == minGap) {
                List<Integer> oneRes = new ArrayList<>();
                oneRes.add(arr[i]);
                oneRes.add(arr[i + 1]);
                res.add(oneRes);
            }
        }

        return res;
    }
}
