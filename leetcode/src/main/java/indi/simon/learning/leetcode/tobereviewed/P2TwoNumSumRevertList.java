package indi.simon.learning.leetcode.tobereviewed;

import indi.simon.learning.leetcode.commonmodel.ListNode;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P2TwoNumSumRevertList {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(5);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode l3 = addTwoNumbers(l1, l2);
        System.out.println(l3);
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


//todo:教训是，像这样处理多个链表的时候，递归的参数尽量就是各个链表同一级的节点，不要有的参数是上一级有的是下一级。
// 也就是说，处理多个链表，不论是合并，求和，还是咋地，递归的参数都一定是链表的当前节点，不要搞next或者prev。
// 当不太好掌握节省空间的技巧的时候，还是老老实实提高一下空间复杂度。