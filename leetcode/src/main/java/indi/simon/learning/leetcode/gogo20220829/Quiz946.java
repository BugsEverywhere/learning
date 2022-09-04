package indi.simon.learning.leetcode.gogo20220829;

import java.util.Stack;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz946 {

    public static void main(String[] args) {
        Quiz946 quiz946 = new Quiz946();
        boolean res = quiz946.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2});
        System.out.println(res);
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {

        Stack<Integer> popStack = new Stack<>();
        for (int i = popped.length - 1; i >= 0; i--) {
            popStack.push(popped[i]);
        }

        Stack<Integer> pushStack = new Stack<>();
        for (int i = 0; i < pushed.length; i++) {
            pushStack.push(pushed[i]);
            while (!pushStack.isEmpty() && !popStack.isEmpty() && pushStack.peek().equals(popStack.peek())) {
                popStack.pop();
                pushStack.pop();
            }
        }

        return popStack.size() == 0;
    }

}
