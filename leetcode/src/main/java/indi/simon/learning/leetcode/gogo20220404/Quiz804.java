package indi.simon.learning.leetcode.gogo20220404;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz804 {

    public static void main(String[] args) {

    }

    public int uniqueMorseRepresentations(String[] words) {
        String[] codeStrArr = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        Set<String> codeSet = new HashSet<>();
        for (String word : words) {
            char[] wordCharArr = word.toCharArray();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < wordCharArr.length; i++) {
                stringBuilder.append(codeStrArr[wordCharArr[i] - 'a']);
            }
            codeSet.add(stringBuilder.toString());
        }
        return codeSet.size();
    }
}
