package indi.simon.learning.leetcode.gogo20230321;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1630 {

    public static void main(String[] args) {
        Quiz1630 quiz1630 = new Quiz1630();
        List<Boolean> res = quiz1630.checkArithmeticSubarrays(new int[]{4, 6, 5, 9, 3, 7}, new int[]{0, 0, 2}, new int[]{2, 3, 5});
        System.out.println(res);
    }

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {

        List<Boolean> res = new ArrayList<>();

        for (int i = 0; i < l.length; i++) {
            if (l[i] == r[i]) {
                res.add(false);
                continue;
            }
            PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o));
            for (int j = l[i]; j <= r[i]; j++) {
                heap.add(nums[j]);
            }
            Integer gap = null;
            Integer last = null;
            while (heap.size() > 0) {
                if (last == null) {
                    last = heap.poll();
                    gap = last - heap.peek();
                } else {
                    int curr = heap.poll();
                    if (last - curr != gap) {
                        res.add(false);
                        break;
                    } else {
                        last = curr;
                    }
                }
            }
            if (res.size() < i + 1) {
                res.add(true);
            }
        }

        return res;
    }

}
