package indi.simon.learning.复习.双指针_滑动窗口;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的 最短窗口 子串，使得该子串包含 t 中的每一个字符（包括重复字符）。如果没有这样的子串，返回空字符串 ""。
 * <p>
 * 测试用例保证答案唯一。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * 示例 2：
 * <p>
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 * 示例 3:
 * <p>
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s 和 t 由英文字母组成
 */
public class Quiz76_最小覆盖子串 {


    public static void main(String[] args) {
        Quiz76_最小覆盖子串 quiz76_最小覆盖子串 = new Quiz76_最小覆盖子串();
        String res = quiz76_最小覆盖子串.minWindow("cabwefgewcwaefgcf", "cae");
        System.out.println(res);
    }

    //用于记录t串中字符分布
    Map<Character, Integer> tMap = new HashMap<>();
    //用于记录s串中字符分布
    Map<Character, Integer> sMap = new HashMap<>();

    public String minWindow(String s, String t) {
        char[] tArr = t.toCharArray();
        for (char c : tArr) {
            Integer cnt = tMap.getOrDefault(c, 0);
            cnt++;
            tMap.put(c, cnt);
        }

        int res = Integer.MAX_VALUE;
        String resStr = "";

        int i = 0;
        int j = 0;

        sMap.put(s.charAt(0), 1);

        while (i < s.length() && j < s.length()) {
            while (i <= j && check(sMap, tMap)) {
                if (res > j - i + 1) {
                    res = Math.min(res, j - i + 1);
                    resStr = s.substring(i, j + 1);
                }
                //i左移
                Integer iCharCnt = sMap.get(s.charAt(i));
                if (iCharCnt == 1) {
                    sMap.remove(s.charAt(i));
                } else if (iCharCnt > 1) {
                    sMap.put(s.charAt(i), iCharCnt - 1);
                }
                i++;
            }
            //j左移
            j++;
            if (j < s.length()) {
                Integer jCharCnt = sMap.getOrDefault(s.charAt(j), 0);
                jCharCnt++;
                sMap.put(s.charAt(j), jCharCnt);
            }
        }

        return resStr;

    }

    private boolean check(Map<Character, Integer> sMap, Map<Character, Integer> tMap) {

        for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
            char c = entry.getKey();
            int cnt = entry.getValue();
            if (!sMap.containsKey(c) || sMap.get(c) < cnt) {
                return false;
            }
        }

        return true;
    }


}
