package indi.simon.learning.leetcode.gogo20230109;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2283 {

    public static void main(String[] args) {
        Quiz2283 quiz2283 = new Quiz2283();
        boolean res = quiz2283.digitCount("1210");
        System.out.println(res);
    }

    public boolean digitCount(String num) {
        Map<Character, Integer> charCount = new HashMap<>();

        for (char c : num.toCharArray()) {
            charCount.merge(c, 1, Integer::sum);
        }

        for (int i = 0; i < num.length(); i++) {
            char c = (char) ('0' + i);
            if (!charCount.containsKey(c) && num.charAt(i) == '0') {
                continue;
            } else if (!charCount.containsKey(c)) {
                return false;
            }
            if (num.charAt(i) - '0' != charCount.get(c)) {
                return false;
            }
        }
        return true;
    }

}
