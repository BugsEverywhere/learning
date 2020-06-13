package indi.simon.learning.leetcode.gogo2020june;

public class P392 {

    public static void main(String[] args) {

        boolean res = isSubsequence("ahbgdc", "abc");
        System.out.println(res);

    }

    public static boolean isSubsequence(String s, String t) {
        //todo 注意判空
        if (s == null || t == null) {
            return false;
        }
        if ("".equals(s)) {
            return true;
        }
        char[] tCharArr = t.toCharArray();
        char[] sCharArr = s.toCharArray();
        int tIndex = 0;
        for (int i = 0; i < sCharArr.length; i++) {
            while (tIndex < tCharArr.length && tCharArr[tIndex] != sCharArr[i]) {
                tIndex++;
            }
            if (tIndex >= tCharArr.length) {
                return false;
            }
            if (i == sCharArr.length - 1 && tCharArr[tIndex] == sCharArr[i]) {
                return true;
            }
            //todo 遇到相等字符也要记得前进指针
            tIndex++;
        }
        return false;
    }

}
