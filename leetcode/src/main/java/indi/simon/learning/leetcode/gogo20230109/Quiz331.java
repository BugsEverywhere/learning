package indi.simon.learning.leetcode.gogo20230109;

import java.util.Stack;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz331 {

    public static void main(String[] args) {
        Quiz331 quiz331 = new Quiz331();
        boolean res = quiz331.isValidSerialization("#,#");
        System.out.println(res);
    }

    public boolean isValidSerialization(String preorder) {
        //todo: 空二叉树的情况也要考虑到
        if ("#".equals(preorder)) {
            return true;
        }

        String[] arr = preorder.split(",");
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            String node = arr[i];
            if ("#".equals(node) && stack.size() > 0 && "#".equals(stack.peek())) {
                while (stack.size() >= 2 && "#".equals(stack.peek())) {
                    stack.pop();
                    //todo: 要校验第二次pop出来的是不是也是个#，这是因为这个用例：[1,#,#,#,#]
                    if ("#".equals(stack.pop())) {
                        return false;
                    }
                }
                //todo: stack为空了还要校验这是不是字符串的最后一个字符了，是不是后面还有节点
                if (stack.size() == 0 && i == arr.length - 1) {
                    return true;
                }
                stack.push("#");
            } else {
                stack.push(node);
            }
        }

        return false;
    }

}
