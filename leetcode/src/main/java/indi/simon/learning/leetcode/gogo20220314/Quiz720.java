package indi.simon.learning.leetcode.gogo20220314;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz720 {

    public static void main(String[] args) {
        String[] strArr = {"b", "br", "bre", "brea", "break", "breakf", "breakfa", "breakfas", "breakfast", "l", "lu", "lun", "lunc", "lunch", "d", "di", "din", "dinn", "dinne", "dinner"};
        Quiz720 quiz720 = new Quiz720();
        String res = quiz720.longestWord(strArr);
        System.out.println(res);

    }

    public String longestWord(String[] words) {
        Arrays.sort(words);
        Set<String> wordSet = new HashSet<>(Arrays.asList(words));

        String maxLengthWordSoFar = null;

        for (int j = words.length - 1; j >= 0; j--) {
            String currentWord = words[j];
            boolean isOkStr = true;
            for (int i = 1; i <= currentWord.length(); i++) {
                String subStr = currentWord.substring(0, i);
                if (!wordSet.contains(subStr)) {
                    isOkStr = false;
                    break;
                }
            }
            if (isOkStr && maxLengthWordSoFar == null) {
                maxLengthWordSoFar = currentWord;
            } else if (isOkStr && maxLengthWordSoFar != null) {
                if (currentWord.length() >= maxLengthWordSoFar.length()) {
                    maxLengthWordSoFar = currentWord;
                }
            }
        }

        if (maxLengthWordSoFar == null) {
            return "";
        }
        return maxLengthWordSoFar;
    }
}
