package indi.simon.learning.leetcode.gogo20230724;

import java.util.PriorityQueue;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2208 {

    public static void main(String[] args) {

    }

    public int halveArray(int[] nums) {
        double sum = 0;
        PriorityQueue<Double> queue = new PriorityQueue<>((o1, o2) -> Double.compare(o2, o1));
        for (int num : nums) {
            queue.offer((double) num);
            sum += num;
        }

        //开始递减
        int cnt = 0;
        double newSum = sum;
        double threshold = ((double) sum) / 2;
        while (newSum > threshold) {
            double max = queue.poll();
            double maxHalf = max / 2;
            newSum -= maxHalf;
            cnt++;
            queue.offer(maxHalf);
        }

        return cnt;
    }

}
