package indi.simon.learning.leetcode.gogo20220704;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz648 {

    public static void main(String[] args) {
        Quiz648 quiz648 = new Quiz648();
        List<String> dictionary = Arrays.asList("a", "b", "c");
        String sentence = "aadsfasf absbs bbab cadsfafs";
        String res = quiz648.replaceWords(dictionary, sentence);
        System.out.println(res);
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        Set<String> dictionarySet = new HashSet<>(dictionary);
        String[] wordArr = sentence.split(" ");
        String res = "";
        for (int i = 0; i < wordArr.length; i++) {
            String iStr = null;
            for (int j = 1; j <= wordArr[i].length(); j++) {
                String subStr = wordArr[i].substring(0, j);
                if (dictionarySet.contains(subStr)) {
                    iStr = subStr;
                    break;
                }
            }
            if (iStr == null) {
                iStr = wordArr[i];
            }
            if (i == 0) {
                res = iStr;
            } else {
                res = res + " " + iStr;
            }

        }
        return res;
    }
}
