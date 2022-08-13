package indi.simon.learning.leetcode.gogo20220808;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1417 {

    public static void main(String[] args) {
        Quiz1417 quiz = new Quiz1417();
        String res = quiz.reformat("covid201987");
        System.out.println(res);
    }

    public String reformat(String s) {
        List<Character> numCharList = new ArrayList<>();
        List<Character> charCharList = new ArrayList<>();

        char[] sArr = s.toCharArray();

        for (char c : sArr) {
            if (c >= 'a' && c <= 'z') {
                charCharList.add(c);
            } else {
                numCharList.add(c);
            }
        }

        if (Math.abs(numCharList.size() - charCharList.size()) > 1) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        int j = 0;
        boolean mark;
        //决定先放数字还是先放字母
        mark = numCharList.size() > charCharList.size();
        while ((i < numCharList.size() && mark) || (j < charCharList.size() && !mark)) {
            if (mark) {
                stringBuilder.append(numCharList.get(i));
                i++;
                mark = false;
            } else {
                stringBuilder.append(charCharList.get(j));
                j++;
                mark = true;
            }
        }
        return stringBuilder.toString();
    }
}
