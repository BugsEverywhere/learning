package indi.simon.learning.leetcode.bytedance.listtree;

import indi.simon.learning.leetcode.commonmodel.ListNode;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class AddTwoNumbers {

    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);

        node1.next = node3;
        node2.next = node4;
        node3.next = node5;
        node5.next = null;
        node4.next = node6;
        node6.next = null;

        ListNode result = addTwoNumbers(node1, node2);

        System.out.println(result);

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //捏住头
        ListNode head = new ListNode((l1.val + l2.val) % 10);
        int carry = (l1.val + l2.val) / 10;
        l1 = l1.next;
        l2 = l2.next;
        //定义当前指针
        ListNode current = head;

        //循环开始
        while (l1 != null && l2 != null) {
            current.next = new ListNode((l1.val + l2.val + carry) % 10);
            carry = (l1.val + l2.val + carry) / 10;
            l1 = l1.next;
            l2 = l2.next;
            current = current.next;
        }

        //处理较长的链表
        if (l1 == null && l2 != null) {
            while (l2 != null) {
                current.next = new ListNode((l2.val + carry) % 10);
                carry = (l2.val + carry) / 10;
                l2 = l2.next;
                current = current.next;
            }
        } else if (l1 != null) {
            while (l1 != null) {
                current.next = new ListNode((l1.val + carry) % 10);
                carry = (l1.val + carry) / 10;
                l1 = l1.next;
                current = current.next;
            }
        }

        //最后不要忘了处理进位
        if (carry > 0) {
            current.next = new ListNode(carry);
        }

        return head;

    }

}
