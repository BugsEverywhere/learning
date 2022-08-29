package indi.simon.learning.leetcode.gogo20220815;

import java.util.LinkedList;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz641 {

    public static void main(String[] args) {

    }

    private LinkedList<Integer> deque;
    private int sizeLimit;

    public Quiz641(int k) {
        this.sizeLimit = k;
        deque = new LinkedList<>();
    }

    public boolean insertFront(int value) {
        if (deque.size() >= sizeLimit) {
            return false;
        }
        deque.addFirst(value);
        return true;
    }

    public boolean insertLast(int value) {
        if (deque.size() >= sizeLimit) {
            return false;
        }
        deque.addLast(value);
        return true;
    }

    public boolean deleteFront() {
        if (deque.size() <= 0) {
            return false;
        }
        deque.removeFirst();
        return true;
    }

    public boolean deleteLast() {
        if (deque.size() <= 0) {
            return false;
        }
        deque.removeLast();
        return true;
    }

    public int getFront() {
        if (deque.size() <= 0) {
            return -1;
        }
        return deque.getFirst();
    }

    public int getRear() {
        if (deque.size() <= 0) {
            return -1;
        }
        return deque.getLast();
    }

    public boolean isEmpty() {
        return deque.size() <= 0;
    }

    public boolean isFull() {
        return deque.size() >= sizeLimit;
    }
}
