package indi.simon.learning.leetcode.gogo20220502;

import indi.simon.learning.leetcode.commonmodel.ListNode;

import java.util.Stack;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz143 {

    public static void main(String[] args) {
        Quiz143 quiz143 = new Quiz143();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        one.next = two;
        two.next = three;
        three.next = four;

        quiz143.reorderList(one);

        System.out.println(one);
    }

    public void reorderList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode node = head;
        while (node != null) {
            stack.push(node);
            node = node.next;
        }

        int listSize = stack.size();
        //todo: 注意此处要把stack的size记录下来，因为后头stack的size是会变的

        node = head;
        while ((listSize % 2 == 1 && stack.peek() != node) || (listSize % 2 == 0 && node.next != stack.peek())) {
            ListNode originalNext = node.next;
            ListNode newNext = stack.pop();
            node.next = newNext;
            newNext.next = originalNext;
            node = originalNext;
        }

        stack.peek().next = null;
    }
}
