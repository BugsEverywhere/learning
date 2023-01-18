package indi.simon.learning.leetcode.gogo20230116;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz347 {

    public static void main(String[] args) {
        Quiz347 quiz347 = new Quiz347();
        int[] res = quiz347.topKFrequent(new int[]{111, 666, 111, 666, 444, 555}, 2);
        System.out.println(res);
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> ocurrMap = new HashMap<>();
        for (int num : nums) {
            ocurrMap.merge(num, 1, Integer::sum);
        }

        PriorityQueue<int[]> ocurrCountHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[1], o1[1]));
        for (Map.Entry<Integer, Integer> singleEntry : ocurrMap.entrySet()) {
            int[] singleOcurr = new int[2];
            singleOcurr[0] = singleEntry.getKey();
            singleOcurr[1] = singleEntry.getValue();
            ocurrCountHeap.offer(singleOcurr);
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            if (ocurrCountHeap.size() == 0) {
                continue;
            }
            res[i] = ocurrCountHeap.poll()[0];
        }

        return res;
    }


}
