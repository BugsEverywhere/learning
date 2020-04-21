package indi.simon.learning.leetcode.bytedance.listtree;

import indi.simon.learning.leetcode.commonmodel.ListNode;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class ReverseList {

    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

        ListNode result = reverseList(node1);

        System.out.println(result);

    }

    public static ListNode reverseList(ListNode head) {

        ListNode last = null;
        ListNode current = head;

        while (current != null) {
            ListNode tmp = current.next;
            current.next = last;
            last = current;
            current = tmp;
        }
        return last;
    }


}
