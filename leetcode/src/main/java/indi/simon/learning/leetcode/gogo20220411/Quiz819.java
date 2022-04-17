package indi.simon.learning.leetcode.gogo20220411;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz819 {

    public static void main(String[] args) {
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = new String[]{"hit"};
        Quiz819 quiz819 = new Quiz819();
        String res = quiz819.mostCommonWord(paragraph, banned);
        System.out.println(res);
    }

    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
        String[] rawArr = paragraph.split(" ");

        int maxCount = 0;
        String maxCountWord = null;
        Map<String, Integer> countMap = new HashMap<>();
        for (String singleWord : rawArr) {
            String[] singleWordArr = singleWord.split(",");
            for (String singleWord1 : singleWordArr) {
                String[] singleWordArr1 = singleWord1.split("\\.");
                for (String singleWord2 : singleWordArr1) {
                    String[] singleWordArr2 = singleWord2.split("!");
                    for (String singleWord3 : singleWordArr2) {
                        String[] singleWordArr3 = singleWord3.split("\\?");
                        for (String singleWord4 : singleWordArr3) {
                            String[] singleWordArr4 = singleWord4.split(";");
                            for (String singleWord5 : singleWordArr4) {
                                String[] singleWordArr5 = singleWord5.split("'");
                                for (String singleWord6 : singleWordArr5) {
                                    singleWord6 = singleWord6.toLowerCase();
                                    if (!bannedSet.contains(singleWord6)) {
                                        countMap.merge(singleWord6, 1, Integer::sum);
                                        if (countMap.get(singleWord6) > maxCount) {
                                            maxCount = countMap.get(singleWord6);
                                            maxCountWord = singleWord6;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return maxCountWord;
    }
}
