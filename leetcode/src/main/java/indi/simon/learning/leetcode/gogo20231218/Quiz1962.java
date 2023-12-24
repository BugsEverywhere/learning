package indi.simon.learning.leetcode.gogo20231218;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Chen Zhuo on 2023/12/23.
 */
public class Quiz1962 {

    public static void main(String[] args) {

    }

    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        for (int pile : piles) {
            queue.offer(pile);
        }

        for (int i = 0; i < k; i++) {
            int pile = queue.poll();
            queue.offer(pile - pile/2);
        }

        int sum = 0;
        while(queue.size() > 0){
            sum += queue.poll();
        }

        return sum;
    }

}
