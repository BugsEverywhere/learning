package indi.simon.learning.leetcode.gogo20220801;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz622 {

    public static void main(String[] args) {

    }

    private Deque<Integer> queue = new ArrayDeque<>();
    private int limit;

    public Quiz622(int k) {
        this.limit = k;
    }

    public boolean enQueue(int value) {
        if (queue.size() >= limit) {
            return false;
        }
        return queue.add(value);
    }

    public boolean deQueue() {
        if (queue.size() == 0) {
            return false;
        }
        queue.poll();
        return true;
    }

    public int Front() {
        if (queue.size() == 0) {
            return -1;
        }
        return queue.peek();
    }

    public int Rear() {
        if (queue.size() == 0) {
            return -1;
        }
        return queue.peekLast();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean isFull() {
        return queue.size() >= limit;
    }

}
