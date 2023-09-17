package indi.simon.learning.leetcode.gogo20230911;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2023/9/16.
 */
public class Quiz249 {

    public static void main(String[] args) {
        Quiz249 quiz249 = new Quiz249();
        String[] strings = new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        List<List<String>> res = quiz249.groupStrings(strings);
        System.out.println(res);
    }

    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strings) {
            boolean mapped = false;
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                if (entry.getKey().length() != str.length()) {
                    continue;
                }
                if (isSamePattern(entry.getKey(), str)) {
                    entry.getValue().add(str);
                    mapped = true;
                    break;
                }
            }
            if (!mapped) {
                List<String> newList = new ArrayList<>();
                newList.add(str);
                map.put(str, newList);
            }
        }

        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            res.add(entry.getValue());
        }

        return res;
    }

    private boolean isSamePattern(String pattern, String str) {
        if (pattern.equals(str)) {
            return true;
        }
        int i = 1;
        int gap = pattern.charAt(0) - str.charAt(0);
        while (i < pattern.length()) {
            int thisGap = pattern.charAt(i) - str.charAt(i);
            if (gap * thisGap > 0) {
                //两者符号相同
                if (gap != thisGap) {
                    return false;
                }
            } else {
                //两者符号不同
                if (Math.abs(gap) + Math.abs(thisGap) != 26) {
                    return false;
                }
            }
            //todo: 存在i在结尾处自增的循环，要避免在循环中途使用continue，因为很可能会忽略了i的自增导致死循环
            i++;
        }
        return true;
    }

}
