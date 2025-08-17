package indi.simon.learning.复习.字符串;

/**
 * Created by Chen Zhuo on 2023/9/10.
 *
 * 给定两个字符串 s 和 t ，如果它们的编辑距离为 1 ，则返回 true ，否则返回 false 。
 *
 * 字符串 s 和字符串 t 之间满足编辑距离等于 1 有三种可能的情形：
 *
 * 往 s 中插入 恰好一个 字符得到 t
 * 从 s 中删除 恰好一个 字符得到 t
 * 在 s 中用 一个不同的字符 替换 恰好一个 字符得到 t
 *
 *
 * 示例 1：
 *
 * 输入: s = "ab", t = "acb"
 * 输出: true
 * 解释: 可以将 'c' 插入字符串 s 来得到 t。
 * 示例 2:
 *
 * 输入: s = "cab", t = "ad"
 * 输出: false
 * 解释: 无法通过 1 步操作使 s 变为 t。
 *
 *
 * 提示:
 *
 * 0 <= s.length, t.length <= 104
 * s 和 t 由小写字母，大写字母和数字组成
 *
 */
public class Quiz161_判断2个字符串编辑距离是否为1 {

    public static void main(String[] args) {
        String s = "a";
        String t = "a";
        Quiz161_判断2个字符串编辑距离是否为1 quiz161 = new Quiz161_判断2个字符串编辑距离是否为1();
        boolean res = quiz161.isOneEditDistance(s, t);
        System.out.println(res);
    }

    public boolean isOneEditDistance(String s, String t) {
        //优化项
        if (Math.abs(s.length() - t.length()) > 1) {
            return false;
        }

        int i = 0;
        int j = 0;

        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
                continue;
            } else {
                return startNewCompare(s, t, i + 1, j) || startNewCompare(s, t, i, j + 1) || startNewCompare(s, t, i + 1, j + 1);
            }
        }
        //todo: 注意要在最后记得判断前minLength顺利相等的情形
        if(Math.abs(s.length() - t.length()) == 1){
            return true;
        }
        return false;
    }

    private boolean startNewCompare(String s, String t, int i, int j) {
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) != t.charAt(j)) {
                return false;
            }
            i++;
            j++;
        }
        if (i == s.length() && j == t.length()) {
            return true;
        }
        return false;
    }

}
