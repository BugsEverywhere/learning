package indi.simon.learning.leetcode.gogo20221003;

import java.util.Stack;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz856 {

    public static void main(String[] args) {
        Quiz856 quiz856 = new Quiz856();
        int res = quiz856.scoreOfParentheses("(()(()))");
        System.out.println(res);
    }

    public int scoreOfParentheses(String s) {
        char[] cArr = s.toCharArray();
        Stack<String> stack = new Stack<>();
        for (char c : cArr) {
            if (c == '(') {
                stack.push("(");
            } else {
                String lastStr = stack.peek();
                if ("(".equals(lastStr)) {
                    //前面的是左括号
                    stack.pop();
                    stack.push("1");
                } else {
                    //前面的是数字
                    int sum = 0;
                    String str;
                    while (!"(".equals(str = stack.pop())) {
                        sum += Integer.parseInt(str);
                    }
                    stack.push(Integer.toString(2 * sum));
                }
            }
        }
        //todo: 第一次提交忘了"()()"这种用例，也就是最终stack里面确实是没有()了，但是有一堆数字
        if (stack.size() == 1) {
            return Integer.parseInt(stack.pop());
        } else {
            int sum = 0;
            while (stack.size() > 0) {
                String str = stack.pop();
                sum += Integer.parseInt(str);
            }
            return sum;
        }
    }

}
