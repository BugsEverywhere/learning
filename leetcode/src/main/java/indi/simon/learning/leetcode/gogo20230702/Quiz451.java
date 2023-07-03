package indi.simon.learning.leetcode.gogo20230702;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz451 {

    public static void main(String[] args) {
        Quiz451 quiz451 = new Quiz451();
        String orderedStr = quiz451.frequencySort("tree");
        System.out.println(orderedStr);
    }

    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.merge(c, 1, Integer::sum);
        }

        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            queue.offer(entry);
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (queue.size() > 0) {
            Map.Entry<Character, Integer> entry = queue.poll();
            for (int i = 0; i < entry.getValue(); i++) {
                stringBuilder.append(entry.getKey());
            }
        }

        return stringBuilder.toString();
    }

}
