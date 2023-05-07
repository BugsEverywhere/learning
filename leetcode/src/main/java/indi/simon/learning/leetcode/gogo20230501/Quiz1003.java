package indi.simon.learning.leetcode.gogo20230501;

import java.util.Stack;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1003 {

    public static void main(String[] args) {
        Quiz1003 quiz1003 = new Quiz1003();
        boolean res = quiz1003.isValid("aabcbc");
        System.out.println(res);
    }

    public boolean isValid(String s) {

        if (!s.contains("abc")) {
            return false;
        }

        if (!s.startsWith("a") || !s.endsWith("c")) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == 'c') {
                if (stack.size() >= 2 && stack.get(stack.size() - 1) == 'b' && stack.get(stack.size() - 2) == 'a') {
                    stack.pop();
                    stack.pop();
                } else {
                    stack.push(c);
                }
            } else {
                stack.push(c);
            }
        }

        return stack.size() == 0;
    }


}
