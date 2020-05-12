package indi.simon.learning.leetcode.gogo2020may.week11to17;

public class P557 {

    public static void main(String[] args) {

    }

    private static String reverseWords(String s) {
        if (s == null) {
            return null;
        }
        if ("".equals(s)) {
            return "";
        }
        String[] words = s.split("\\s");
        StringBuilder sb = new StringBuilder();
        for (String singleWord : words) {
            sb.append(reversString(singleWord)).append(" ");
        }
        String str = sb.toString();
        return str.substring(0, str.length() - 1);
    }

    private static String reversString(String s) {
        char[] charArr = s.toCharArray();
        char[] anotherArr = new char[s.length()];
        int index = anotherArr.length - 1;
        for (char singleChar : charArr) {
            anotherArr[index] = singleChar;
            index--;
        }
        return new String(anotherArr);
    }


}
