package indi.simon.learning.leetcode.april2020.week27to;

public class P151 {

    public static void main(String[] args) {
        System.out.println(reverseWords("a good   example    "));
    }

    public static String reverseWords(String s) {
        s = s.trim();
        String[] wordArr = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int j = wordArr.length - 1; j >= 0; j--) {
            sb.append(wordArr[j]).append(" ");
        }
        return sb.toString().trim();
    }

}
