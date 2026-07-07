package indi.simon.learning.复习.双指针_滑动窗口;

import java.util.HashMap;
import java.util.Map;

public class Quiz76_最小覆盖子串 {

    //用于记录t串中字符分布
    Map<Character, Integer> tMap = new HashMap<>();
    //用于记录s串中字符分布
    Map<Character, Integer> sMap = new HashMap<>();

    public String minWindow(String s, String t) {
        //先登记t串中字符
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        int l = 0, r = -1;
        int len = Integer.MAX_VALUE;
        //记录结果的左右边界
        int ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            r++;
            if (r < sLen && tMap.containsKey(s.charAt(r))) {
                sMap.put(s.charAt(r), sMap.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (tMap.containsKey(s.charAt(l))) {
                    sMap.put(s.charAt(l), sMap.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    /**
     * 检查当前子串是否覆盖了t串
     *
     * @return
     */
    public boolean check() {
        for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
            Character key = entry.getKey();
            Integer val = entry.getValue();
            if (sMap.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }


}
