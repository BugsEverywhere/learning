package indi.simon.learning.leetcode.gogo20220404;

import indi.simon.learning.leetcode.commonmodel.ListNode;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz83 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(3);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        Quiz83 quiz83 = new Quiz83();
        ListNode res = quiz83.deleteDuplicates(node1);
        System.out.println(res);

    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode curr = head;
        ListNode next = curr.next;

        while (next != null) {
            if (curr.val == next.val) {
                next = next.next;
                curr.next = next;
            } else {
                curr = next;
                next = curr.next;
            }
        }

        return head;
    }
}
