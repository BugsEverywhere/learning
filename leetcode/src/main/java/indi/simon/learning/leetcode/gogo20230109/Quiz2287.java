package indi.simon.learning.leetcode.gogo20230109;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2287 {

    public static void main(String[] args) {

    }

    public int rearrangeCharacters(String s, String target) {
        Map<Character, Integer> targetCharCount = new HashMap<>();
        for (char c : target.toCharArray()) {
            targetCharCount.merge(c, 1, Integer::sum);
        }

        Map<Character, Integer> sCharCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (targetCharCount.containsKey(c)) {
                sCharCount.merge(c, 1, Integer::sum);
            }
        }

        int min = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> singleChar : targetCharCount.entrySet()) {
            //todo: 要考虑到还有s不足target长的情况
            if (!sCharCount.containsKey(singleChar.getKey())) {
                return 0;
            }
            int combinationCount = sCharCount.get(singleChar.getKey()) / targetCharCount.get(singleChar.getKey());
            min = Math.min(min, combinationCount);
        }

        return min;
    }


}
