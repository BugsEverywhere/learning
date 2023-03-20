package indi.simon.learning.leetcode.gogo20230313;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz316_needReview {

    public static void main(String[] args) {
        Quiz316_needReview quiz316Notfinish = new Quiz316_needReview();
        String res = quiz316Notfinish.removeDuplicateLetters("bcabc");
        System.out.println(res);
    }

    //todo: 贪心+官方单调栈
    public String removeDuplicateLetters(String s) {
        boolean[] vis = new boolean[26];
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!vis[ch - 'a']) {
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch) {
                    if (num[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                        vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    } else {
                        break;
                    }
                }
                vis[ch - 'a'] = true;
                sb.append(ch);
            }
            num[ch - 'a'] -= 1;
        }
        return sb.toString();
    }


}
