package indi.simon.learning.leetcode.gogo20220523;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1021 {

    public static void main(String[] args) {
        Quiz1021 quiz1021 = new Quiz1021();
        String res = quiz1021.removeOuterParentheses("()()");
        System.out.println(res);
    }

    public String removeOuterParentheses(String s) {
        char[] cArr = s.toCharArray();
        String res = "";
        int leftBracketCount = 0;
        int lastStart = 0;
        for (int i = 0; i < s.length(); i++) {
            if (leftBracketCount == 0) {
                lastStart = i;
            }

            if (cArr[i] == '(') {
                leftBracketCount++;
            } else {
                leftBracketCount--;
            }

            if (leftBracketCount == 0 && i != 0) {
                //形成一个原语
                res = res + s.substring(lastStart + 1, i);
            }

        }

        return res;
    }

}
