package indi.simon.learning.leetcode.gogo20221107;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz791_needReview {

    public static void main(String[] args) {

    }

    public String customSortString(String order, String s) {
        char[] sArr = s.toCharArray();
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : sArr) {
            charCount.merge(c, 1, Integer::sum);
        }
        //遍历order，把需要排序的字符先排好序
        StringBuilder res = new StringBuilder();
        char[] orderArr = order.toCharArray();
        for (char c : orderArr) {
            if (charCount.containsKey(c)) {
                int count = charCount.get(c);
                while (count > 0) {
                    res.append(c);
                    count--;
                }
                charCount.remove(c);
            }
        }
        //将剩余的字符放入结果
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            int count = entry.getValue();
            while (count > 0) {
                res.append(entry.getKey());
                count--;
            }
        }

        return res.toString();
    }

}
