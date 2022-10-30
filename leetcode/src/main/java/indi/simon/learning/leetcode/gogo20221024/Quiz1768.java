package indi.simon.learning.leetcode.gogo20221024;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1768 {

    public static void main(String[] args) {
        Quiz1768 quiz1768 = new Quiz1768();
        String res = quiz1768.mergeAlternately("abcd", "pq");
        System.out.println(res);
    }

    public String mergeAlternately(String word1, String word2) {

        StringBuilder stringBuilder = new StringBuilder();

        char[] c1 = word1.toCharArray();
        char[] c2 = word2.toCharArray();

        int i = 0;
        while (i < c1.length && i < c2.length) {
            stringBuilder.append(c1[i]);
            stringBuilder.append(c2[i]);
            i++;
        }

        if (c1.length < c2.length) {
            for (; i < c2.length; i++) {
                stringBuilder.append(c2[i]);
            }
        } else {
            for (; i < c1.length; i++) {
                stringBuilder.append(c1[i]);
            }
        }

        return stringBuilder.toString();
    }

}
