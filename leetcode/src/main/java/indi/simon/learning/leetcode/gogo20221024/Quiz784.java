package indi.simon.learning.leetcode.gogo20221024;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz784 {

    public static void main(String[] args) {
        Quiz784 quiz784 = new Quiz784();
        List<String> res = quiz784.letterCasePermutation("a1b2");
        System.out.println(res);
    }

    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        letterCasePermutationInternal(res, new StringBuilder(), 0, s);
        return res;
    }

    private void letterCasePermutationInternal(List<String> res, StringBuilder stringBuilder, int index, String s) {
        if (index >= s.length()) {
            res.add(stringBuilder.toString());
            return;
        }
        if ((s.charAt(index) >= 'a' && s.charAt(index) <= 'z') || (s.charAt(index) >= 'A' && s.charAt(index) <= 'Z')) {
            //转变他
            StringBuilder sb1 = new StringBuilder(stringBuilder);
            sb1.append(turn(s.charAt(index)));
            letterCasePermutationInternal(res, sb1, index + 1, s);
            //不转变他
            StringBuilder sb2 = new StringBuilder(stringBuilder);
            sb2.append(s.charAt(index));
            letterCasePermutationInternal(res, sb2, index + 1, s);
        } else {
            //是其他字符
            StringBuilder sb = new StringBuilder(stringBuilder);
            sb.append(s.charAt(index));
            letterCasePermutationInternal(res, sb, index + 1, s);
        }
    }

    private char turn(char c) {
        if ((c >= 'a' && c <= 'z')) {
            return (char) ('A' + (c - 'a'));
        } else {
            return (char) ('a' + (c - 'A'));
        }
    }

}
