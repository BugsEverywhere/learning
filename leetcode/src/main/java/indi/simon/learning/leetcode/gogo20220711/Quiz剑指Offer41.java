package indi.simon.learning.leetcode.gogo20220711;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz剑指Offer41 {

    public static void main(String[] args) {

    }

    private int sum;
    private LinkedBlockingQueue<Integer> queue;
    private int queueSize;

    public Quiz剑指Offer41(int size) {
        sum = 0;
        queue = new LinkedBlockingQueue<Integer>();
        queueSize = size;
    }

    public double next(int val) {
        if (queue.size() < queueSize) {
            queue.add(val);
            sum += val;
        } else {
            sum -= queue.poll();
            sum += val;
            queue.add(val);
        }
        return (double) sum / (double) queue.size();
    }

}
