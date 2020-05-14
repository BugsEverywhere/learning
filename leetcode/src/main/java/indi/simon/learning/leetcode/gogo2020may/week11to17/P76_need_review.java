package indi.simon.learning.leetcode.gogo2020may.week11to17;

import java.util.HashMap;
import java.util.Map;

public class P76_need_review {

    public static void main(String[] args) {
        String s = "bbaac";
        String t = "aba";

        String res = minWindow(s, t);
        System.out.println(res);

    }

    private static String minWindow(String s, String t) {
        if (s == null || t == null) {
            return "";
        }
        if (s.length() < t.length()) {
            return "";
        }
        if (s.contains(t)) {
            return t;
        }
        if (s.length() == 1 && !s.contains(t)) {
            return "";
        } else if (s.length() == 1) {
            return t;
        }

        Map<Character, Integer> tOriginMap = new HashMap<>();
        char[] tCharArr = t.toCharArray();
        for (char singleChar : tCharArr) {
            if (tOriginMap.get(singleChar) == null) {
                tOriginMap.put(singleChar, 1);
            } else {
                int originCount = tOriginMap.get(singleChar);
                tOriginMap.put(singleChar, originCount + 1);
            }
        }

        int head = 0;
        int tail = Integer.MAX_VALUE;

        Map<Character, Integer> windowMap = new HashMap<>();
        char[] sCharArr = s.toCharArray();
        int i = 0;
        int j = i + 1;
        if (tOriginMap.get(sCharArr[i]) != null) {
            addOneInMap(windowMap, sCharArr[i]);
        }
        boolean moveSwitch = false;
        for (; i <= sCharArr.length - t.length() && j <= sCharArr.length - 1; ) {
            if ((moveSwitch && tOriginMap.get(sCharArr[i]) == null) || (!moveSwitch && tOriginMap.get(sCharArr[j]) == null)) {
                if (moveSwitch) {
                    i++;
                } else {
                    j++;
                }
                continue;
            }
            if (!moveSwitch) {
                addOneInMap(windowMap, sCharArr[j]);
            }
            if (isValidWindow(tOriginMap, windowMap)) {
                if (j - i < tail - head) {
                    head = i;
                    tail = j;
                }
                subOneInMap(windowMap, sCharArr[i]);
                i++;
                moveSwitch = true;
            } else {
                j++;
                moveSwitch = false;
            }
        }

        if (tail == Integer.MAX_VALUE) {
            return "";
        }

        return tail + 1 == s.length() ? s.substring(head) : s.substring(head, tail + 1);
    }

    private static void addOneInMap(Map<Character, Integer> map, char key) {
        if (map.get(key) == null) {
            map.put(key, 1);
        } else {
            map.put(key, map.get(key) + 1);
        }
    }

    private static void subOneInMap(Map<Character, Integer> map, char key) {
        Integer val = map.get(key);
        if (val == null || val == 0) {
            return;
        }
        map.put(key, val - 1);
    }

    private static boolean isValidWindow(Map<Character, Integer> originMap, Map<Character, Integer> windowMap) {
        if (originMap.size() != windowMap.size()) {
            return false;
        }
        for (Map.Entry<Character, Integer> singleEntry : originMap.entrySet()) {
            if (windowMap.get(singleEntry.getKey()) == null || windowMap.get(singleEntry.getKey()) < singleEntry.getValue()) {
                return false;
            }
        }
        return true;
    }

}


//todo 此题使用滑动窗口，两个指针，每次只移动其中一个，要注意的地方实在太多，比如每次移动右指针需要往窗口加元素，在何处加。
// 如何避免循环初始条件对循环的影响。
