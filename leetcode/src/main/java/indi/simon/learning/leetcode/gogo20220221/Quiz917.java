package indi.simon.learning.leetcode.gogo20220221;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz917 {

    public static void main(String[] args) {
        Quiz917 quiz917 = new Quiz917();
        String res = quiz917.reverseOnlyLetters("a-bC-dEf-ghIj");
        System.out.println(res);
    }

    public String reverseOnlyLetters(String s) {
        List<Character> englishCharList = new ArrayList<>();
        char[] charArr = s.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            if (isEnglishChar(charArr[i])) {
                //是英文字符
                englishCharList.add(charArr[i]);
            }
        }
        if (englishCharList.size() == 0) {
            return new String(charArr);
        }
        int arrIndex = 0;
        for (int i = englishCharList.size() - 1; i >= 0; i--) {
            while (!isEnglishChar(charArr[arrIndex])) {
                arrIndex++;
            }
            charArr[arrIndex] = englishCharList.get(i);
            arrIndex++;
        }

        return new String(charArr);
    }

    private boolean isEnglishChar(char c) {
        return (c - 'a' < 26 && c - 'a' >= 0) || (c - 'A' < 26 && c - 'A' >= 0);
    }

}
