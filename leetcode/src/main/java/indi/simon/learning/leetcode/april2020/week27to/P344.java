package indi.simon.learning.leetcode.april2020.week27to;

public class P344 {

    public static void main(String[] args) {


    }

    private static void reverseString(char[] s) {
        if (s.length == 0) {
            return;
        }
        for (int i = 0, j = s.length - 1; i <= j; i++, j--) {
            swap(s, i, j);
        }
    }


    private static void swap(char[] s, int i, int j) {
        char temp = s[j];
        s[j] = s[i];
        s[i] = temp;
    }

}
