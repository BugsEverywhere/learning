package indi.simon.learning.复习.单调栈;

/**
 * @author chenzhuo(zhiyue)
 *
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 * 示例 1：
 *
 * 输入：s = "bcabc"
 * 输出："abc"
 * 示例 2：
 *
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 *
 */
public class Quiz316_保持字符排序去除重复字母 {

    public static void main(String[] args) {
        Quiz316_保持字符排序去除重复字母 quiz316Notfinish = new Quiz316_保持字符排序去除重复字母();
        String res = quiz316Notfinish.removeDuplicateLetters("bcabc");
        System.out.println(res);
    }

    //todo: 贪心+官方单调栈
    public String removeDuplicateLetters(String s) {
        //added[i]记录在结果字符串中是否已经加入了该字符
        boolean[] added = new boolean[26];
        //记录26个字符在字符串中出现的次数
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }
        //结果字符串本身作为单调栈使用
        StringBuffer string = new StringBuffer();
        //遍历字符串
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            //如果当前字符已经在string中了，跳过
            if (!added[ch - 'a']) {
                //todo: 单调栈在此处发挥作用，如果string末尾的字符字典序比当前字符大，则：
                // 1. 如果末尾字符后续还有，则果断弹出，弹出时需要将added复位
                // 2. 如果末尾字符后续么得了，因为要确保所有字符都必须存在，所以只能break
                while (string.length() > 0 && string.charAt(string.length() - 1) > ch) {
                    if (num[string.charAt(string.length() - 1) - 'a'] > 0) {
                        added[string.charAt(string.length() - 1) - 'a'] = false;
                        string.deleteCharAt(string.length() - 1);
                    } else {
                        break;
                    }
                }
                //todo: 然后将当前字符塞到栈中，置位added
                added[ch - 'a'] = true;
                string.append(ch);
            }

            //在字符串余下的部分，该字符数量减1
            num[ch - 'a'] -= 1;
        }
        return string.toString();
    }


}
