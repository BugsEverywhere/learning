package indi.simon.learning.leetcode.gogo20231002;

import java.util.*;

/**
 * Created by Chen Zhuo on 2023/10/8.
 */
public class Quiz2034 {

    public static void main(String[] args) {

    }

    private Map<Integer, Integer> validStockPrices;
    private PriorityQueue<int[]> maxHeap;
    private PriorityQueue<int[]> minHeap;
    private PriorityQueue<int[]> sequence;

    public Quiz2034() {
        validStockPrices = new HashMap<>();
        maxHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[1], o1[1]));
        minHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        sequence = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[0], o1[0]));
    }

    public void update(int timestamp, int price) {
        int[] tuple = new int[]{timestamp, price};
        validStockPrices.put(timestamp, price);
        maxHeap.offer(tuple);
        minHeap.offer(tuple);
        sequence.offer(tuple);
    }

    public int current() {
        while (sequence.size() > 0) {
            int[] tuple = sequence.peek();
            if (validStockPrices.get(tuple[0]) == tuple[1]) {
                return tuple[1];
            } else {
                sequence.poll();
            }
        }
        return -1;
    }

    public int maximum() {
        while (maxHeap.size() > 0) {
            int[] tuple = maxHeap.peek();
            if (validStockPrices.get(tuple[0]) == tuple[1]) {
                return tuple[1];
            } else {
                maxHeap.poll();
            }
        }
        return -1;
    }

    public int minimum() {
        while (minHeap.size() > 0) {
            int[] tuple = minHeap.peek();
            if (validStockPrices.get(tuple[0]) == tuple[1]) {
                return tuple[1];
            } else {
                minHeap.poll();
            }
        }
        return -1;
    }

}
