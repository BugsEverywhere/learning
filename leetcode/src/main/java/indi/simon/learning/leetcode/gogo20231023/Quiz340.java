package indi.simon.learning.leetcode.gogo20231023;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2023/10/29.
 */
public class Quiz340 {

    public static void main(String[] args) {
        Quiz340 quiz340 = new Quiz340();
        int res = quiz340.lengthOfLongestSubstringKDistinct("aa",1);
        System.out.println(res);
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {

        if (k == 0) {
            return 0;
        }

        int length = 0;
        int l = 0;
        int r = 0;

        Map<Character, Integer> cntMap = new HashMap<>();
        cntMap.put(s.charAt(0), 1);

        while (l <= r && r < s.length()) {
            if (cntMap.keySet().size() <= k) {
                length = Math.max(length, r - l + 1);
                //窗口内字符个数小于等于k，窗口扩大
                r++;
                if (r < s.length()) {
                    if (cntMap.containsKey(s.charAt(r))) {
                        cntMap.put(s.charAt(r), cntMap.get(s.charAt(r)) + 1);
                    } else {
                        cntMap.put(s.charAt(r), 1);
                    }
                }
            } else {
                //窗口内字符个数超了，窗口缩小
                if (cntMap.get(s.charAt(l)) > 1) {
                    cntMap.put(s.charAt(l), cntMap.get(s.charAt(l)) - 1);
                } else {
                    cntMap.remove(s.charAt(l));
                }
                l++;
            }
        }

        return length;
    }
}
