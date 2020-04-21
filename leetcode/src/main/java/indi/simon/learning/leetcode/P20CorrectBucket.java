package indi.simon.learning.leetcode;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P20CorrectBucket {

    public static void main(String[] args) {
        String test = "{([[]])}";
        boolean ifElse = isValid(test);
        System.out.println(ifElse);
    }

    public static boolean isValid(String s) {
        if (s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        char[] charArr = s.toCharArray();
        for (char singleChar : charArr) {
            if (singleChar == '(' || singleChar == '[' || singleChar == '{') {
                stack.push(singleChar);
            } else if (stack.size() == 0) {
                return false;
            } else if (singleChar == ')') {
                if (stack.pop().equals('(')) {
                    continue;
                } else {
                    return false;
                }
            } else if (singleChar == ']') {
                if (stack.pop().equals('[')) {
                    continue;
                } else {
                    return false;
                }
            } else if (singleChar == '}') {
                if (stack.pop().equals('{')) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        if (stack.size() > 0) {
            return false;
        }
        return true;
    }

}

//todo: 注意一开始就是] ) }的情况，需要判断stack在pop的时候是否有值，
// 最终返回的时候需要判断一下stack的size，如果stack
// 中间还有没pop的( [ { 说明也是坏字符串