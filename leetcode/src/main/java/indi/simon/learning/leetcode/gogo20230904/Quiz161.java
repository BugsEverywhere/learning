package indi.simon.learning.leetcode.gogo20230904;

/**
 * Created by Chen Zhuo on 2023/9/10.
 */
public class Quiz161 {

    public static void main(String[] args) {
        String s = "a";
        String t = "a";
        Quiz161 quiz161 = new Quiz161();
        boolean res = quiz161.isOneEditDistance(s, t);
        System.out.println(res);
    }

    public boolean isOneEditDistance(String s, String t) {
        //优化项
        if (Math.abs(s.length() - t.length()) > 1) {
            return false;
        }

        int i = 0;
        int j = 0;

        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
                continue;
            } else {
                return startNewCompare(s, t, i + 1, j) || startNewCompare(s, t, i, j + 1) || startNewCompare(s, t, i + 1, j + 1);
            }
        }
        //todo: 注意要在最后记得判断前minLength顺利相等的情形
        if(Math.abs(s.length() - t.length()) == 1){
            return true;
        }
        return false;
    }

    private boolean startNewCompare(String s, String t, int i, int j) {
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) != t.charAt(j)) {
                return false;
            }
            i++;
            j++;
        }
        if (i == s.length() && j == t.length()) {
            return true;
        }
        return false;
    }

}
