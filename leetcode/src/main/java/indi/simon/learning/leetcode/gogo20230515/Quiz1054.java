package indi.simon.learning.leetcode.gogo20230515;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1054 {

    public static void main(String[] args) {
        Quiz1054 quiz1054 = new Quiz1054();
        int[] res = quiz1054.rearrangeBarcodes(new int[]{2, 1, 1});
        System.out.println(res);
    }

    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> cntMap = new HashMap<>();


        for (int barCode : barcodes) {
            cntMap.merge(barCode, 1, Integer::sum);
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(cntMap.get(o2), cntMap.get(o1)));
        for (Map.Entry<Integer, Integer> entry : cntMap.entrySet()) {
            queue.add(entry.getKey());
        }

        Arrays.fill(barcodes, 0);
        int index = 0;
        for (Integer code : queue) {
            int cnt = cntMap.get(code);
            int originalIndex = index;
            while (cnt > 0) {
                while (barcodes[index] != 0) {
                    index++;
                    if (index == originalIndex) {
                        break;
                    }
                    if (index >= barcodes.length) {
                        index = 0;
                    }
                }
                barcodes[index] = code;
                cnt--;
                index += 2;
                if (index >= barcodes.length) {
                    index = 0;
                }
                if (index == originalIndex) {
                    break;
                }
            }
        }

        return barcodes;
    }

}
