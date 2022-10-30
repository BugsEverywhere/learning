package indi.simon.learning.leetcode.gogo20221024;

import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1773 {

    public static void main(String[] args) {

    }

    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int ruleIndex = -1;
        if ("type".equals(ruleKey)) {
            ruleIndex = 0;
        } else if ("color".equals(ruleKey)) {
            ruleIndex = 1;
        } else {
            ruleIndex = 2;
        }

        //统计个数
        int count = 0;
        for (List<String> singleItem : items) {
            if (singleItem.get(ruleIndex).equals(ruleValue)) {
                count++;
            }
        }

        return count;
    }

}
