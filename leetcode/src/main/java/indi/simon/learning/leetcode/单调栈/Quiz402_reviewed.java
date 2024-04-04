package indi.simon.learning.leetcode.单调栈;

import java.util.Stack;

//todo: 思路是贪心加单调栈，贪心的含义在于，一个数字，如果需要他尽可能小，那么越小的数越应该排前面，那么如果要求删
// 除掉其中k个数来做到这一点的话，就从左往右遍历，发现某个数大于后面的数，则删除该数。这属于单调栈的典型用法，
// 其中需要注意的点：
// 1. 这种思路注定了有两层循环，因为可能出现某个数很小，他前面若干个数都比他大，那么需要一直往前删，直到删够k个数。
// 2. 单调栈可以保证遍历完字符串之后，如果还没删除够，那么该栈肯定是一个单调递增的栈，这种情况如果需要继续删除的话，就从尾部从后往前删
//    只有在一趟遍历之后删除了k个数的情况下，才可能使得栈不单调
// 3. 最终拼成字符串的时候来保证去除前缀0的逻辑，可以不在前面单调栈的地方增加判断的复杂度
public class Quiz402_reviewed {

    public static void main(String[] args) {
        String num = "1432219";
        int k = 3;
        Quiz402_reviewed quiz402Reviewed = new Quiz402_reviewed();
        String res = quiz402Reviewed.removeKdigits(num, k);
        System.out.println(res);
    }

    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        int cnt = 0;
        for (char c : num.toCharArray()) {
            //todo: 注意的点1，这里是个while循环，不是简单的一个if
            while (!stack.isEmpty() && cnt < k && stack.peek() > c) {
                stack.pop();
                cnt++;
            }
            stack.push(c);
        }
        //todo: 注意的点2，如果还没删够，则要从尾部继续删，之所以不是从头部删，是因为这种情况下，
        // 这肯定是个单调递增栈
        for (int i = cnt; i < k && !stack.isEmpty(); i++) {
            stack.pop();
        }

        //todo: 注意的点3，去除前缀0的逻辑可以放在最终拼字符串这里，减少前面单调栈的复杂度
        StringBuilder builder = new StringBuilder();
        for (char c : stack) {
            if (builder.length() == 0 && c == '0') {
                continue;
            }
            builder.append(c);
        }
        if (builder.length() == 0) {
            return "0";
        }
        return builder.toString();
    }
}
