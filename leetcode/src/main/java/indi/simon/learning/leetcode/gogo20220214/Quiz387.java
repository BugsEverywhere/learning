package indi.simon.learning.leetcode.gogo20220214;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz387 {

    public static void main(String[] args) {
        Quiz387 quiz387 = new Quiz387();
        int res = quiz387.firstUniqChar("dddccdbba");
        System.out.println(res);
    }

    public int firstUniqChar(String s) {
        Map<Character, String> countMap = new LinkedHashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (countMap.get(c) == null) {
                countMap.put(c, 1 + "_" + i);
            } else {
                String content = countMap.get(c);
                String[] contentArr = content.split("_");
                Integer newCount = Integer.parseInt(contentArr[0]) + 1;
                countMap.put(c, newCount + "_" + i);
            }
        }

        for (Map.Entry<Character, String> entry : countMap.entrySet()) {
            String content = entry.getValue();
            String[] contentArr = content.split("_");
            if ("1".equals(contentArr[0])) {
                return Integer.parseInt(contentArr[1]);
            }
        }

        return -1;
    }
}
