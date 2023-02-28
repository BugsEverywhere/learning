package indi.simon.learning.leetcode.gogo20230227;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2363 {

    public static void main(String[] args) {

    }

    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        Map<Integer, Integer> weightMap = new HashMap<>();

        for (int[] item : items1) {
            weightMap.put(item[0], item[1]);
        }

        for (int[] item : items2) {
            weightMap.merge(item[0], item[1], Integer::sum);
        }

        PriorityQueue<List<Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.get(0)));

        for (Map.Entry<Integer, Integer> entry : weightMap.entrySet()) {
            List<Integer> singleItem = new ArrayList<>();
            singleItem.add(entry.getKey());
            singleItem.add(entry.getValue());
            queue.add(singleItem);
        }

        List<List<Integer>> res = new ArrayList<>();
        while (queue.size() > 0) {
            res.add(queue.poll());
        }

        return res;
    }

}
