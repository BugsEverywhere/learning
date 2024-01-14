package indi.simon.learning.leetcode.gogo20240108;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Chen Zhuo on 2024/1/13.
 */
public class Quiz316_hinted {

    public static void main(String[] args) {
        Quiz316_hinted quiz316NeedReview = new Quiz316_hinted();
        String res = quiz316NeedReview.removeDuplicateLetters("abacb");
        System.out.println(res);
    }

    public String removeDuplicateLetters(String s) {
        //用于记录s中每个字符出现次数的map
        Map<Character, Integer> cCntMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            Integer cnt = cCntMap.getOrDefault(c, 0);
            cnt++;
            cCntMap.put(c, cnt);
        }

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (stack.size() == 0) {
                stack.push(c);
            } else {
                if (stack.peek() < c) {
                    //如果栈顶元素字典序小于c，且栈中没有该字符，则c可以压栈
                    if (!stack.contains(c)) {
                        stack.push(c);
                    }
                } else if (stack.peek() > c && !stack.contains(c)) {
                    //如果栈顶元素字典序大于c，则校验其是否可以出栈
                    while (stack.size() > 0 && stack.peek() > c && cCntMap.containsKey(stack.peek())) {
                        stack.pop();
                    }
                    stack.push(c);
                }
            }
            //遍历过的字符要在map减掉，保证map中保存的是当前后续还存在的字符
            Integer cnt = cCntMap.get(c);
            cnt--;
            if (cnt == 0) {
                cCntMap.remove(c);
            } else {
                cCntMap.put(c, cnt);
            }
        }

        StringBuilder builder = new StringBuilder();
        for (char c : stack) {
            builder.append(c);
        }

        return builder.toString();
    }

}
