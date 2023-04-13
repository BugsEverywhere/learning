package indi.simon.learning.leetcode.gogo20230410;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2404 {

    public static void main(String[] args) {

    }

    public int mostFrequentEven(int[] nums) {

        Map<Integer, Integer> cntMap = new HashMap<>();

        int curr = Integer.MAX_VALUE;
        int currCnt = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o));

        for (int num : nums) {
            cntMap.merge(num, 1, Integer::sum);

            if (num % 2 == 0 && cntMap.get(num) > currCnt) {
                //抬高了出现次数
                curr = num;
                currCnt = cntMap.get(num);
                queue.clear();
                queue.add(num);
            } else if (num % 2 == 0 && cntMap.get(num) == currCnt && num != curr) {
                //发现有相同出现次数的
                curr = num;
                queue.add(num);
            }
        }

        return curr == Integer.MAX_VALUE ? -1 : queue.poll();
    }

}
