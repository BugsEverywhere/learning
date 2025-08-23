package indi.simon.learning.复习.记忆化回溯;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给你一种规律 pattern 和一个字符串 s，请你判断 s 是否和 pattern 的规律相匹配。
 * <p>
 * 如果存在单个字符到 非空 字符串的 双射映射 ，那么字符串 s 匹配 pattern ，即：如果 pattern 中的每个字符都被它映射到的字符串替换，那么最终的字符串则为 s 。双射 意味着映射双方一一对应，不会存在两个字符映射到同一个字符串，也不会存在一个字符分别映射到两个不同的字符串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：pattern = "abab", s = "redblueredblue"
 * 输出：true
 * 解释：一种可能的映射如下：
 * 'a' -> "red"
 * 'b' -> "blue"
 * 示例 2：
 * <p>
 * 输入：pattern = "aaaa", s = "asdasdasdasd"
 * 输出：true
 * 解释：一种可能的映射如下：
 * 'a' -> "asd"
 * 示例 3：
 * <p>
 * 输入：pattern = "aabb", s = "xyzabcxzyabc"
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= pattern.length, s.length <= 20
 * pattern 和 s 由小写英文字母组成
 */
public class Quiz291_字符串模式映射 {

    public static void main(String[] args) {
        Quiz291_字符串模式映射 quiz291 = new Quiz291_字符串模式映射();
        boolean res = quiz291.wordPatternMatch("abab", "redblueredblue");
        System.out.println(res);
    }

    //pattern的字符数组
    private char[] patternCharArr;
    //pattern的长度
    private int patternLength;
    //目标字符串
    private String s;

    public boolean wordPatternMatch(String pattern, String s) {
        patternCharArr = pattern.toCharArray();
        patternLength = patternCharArr.length;
        this.s = s;
        return dfs(0, 0, new HashMap<>(), new HashSet<>());
    }


    /**
     * @idx1: 遍历pattern的指针
     * @idx2: 遍历字符串s的指针
     * @map: 保存pattern中字符与所代表的字符串的映射
     * @hashSet: 保证双射
     */
    private boolean dfs(int idx1, int idx2, Map<Character, String> map, Set<String> hashSet) {
        //如果pattern指针结束了，那么s的指针也必须结束，否则不匹配
        if (idx1 == patternLength) {
            return idx2 == s.length();
        }
        //之前添加过当前pattern中字符代表的字符串
        if (map.containsKey(patternCharArr[idx1])) {
            //取出字符映射的字符串
            String str = map.get(patternCharArr[idx1]);
            //匹配则继续递归后续，否则返回false
            if (idx2 + str.length() <= s.length() && s.startsWith(str, idx2))
                return dfs(idx1 + 1, idx2 + str.length(), map, hashSet);
            else
                return false;
        }
        //没有添加过当前pattern中字符代表的字符串，则需要找到当前模式字符的映射字符串，for循环递归s后续，一个个找
        for (int i = idx2 + 1; i <= s.length(); i++) {
            String str = s.substring(idx2, i);
            //使用hashSet保存该找寻结果，hashSet来自上一层传入，有可能之前就使用过该映射字符串，之前就使用过则跳过
            if (hashSet.contains(str)) {
                continue;
            }
            //往下递归
            hashSet.add(str);
            map.put(patternCharArr[idx1], str);
            if (dfs(idx1 + 1, i, map, hashSet))
                return true;
            map.remove(patternCharArr[idx1]);
            hashSet.remove(str);

        }
        return false;
    }
}
