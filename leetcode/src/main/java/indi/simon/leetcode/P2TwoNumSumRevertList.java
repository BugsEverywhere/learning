package indi.simon.leetcode;

import indi.simon.leetcode.commonmodel.ListNode;

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
        int carry;
        int singleNodeSum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
        carry = singleNodeSum / 10;
        ListNode l3 = new ListNode(singleNodeSum % 10);
        addInternal(l1, l2, l3, carry);
        return l3;
    }

    public static void addInternal(ListNode l1, ListNode l2, ListNode l3, int carry) {
        if (l1.next == null && l2.next != null) {
            l1.next = new ListNode(0);
        }
        if (l2.next == null && l1.next != null) {
            l2.next = new ListNode(0);
        }
        if (l2.next == null && l1.next == null) {
            if (carry > 0) {
                l3.next = new ListNode(carry);
                return;
            }
            l3.next = null;
            return;
        }
        l3.next = new ListNode((l1.next.val + l2.next.val + carry) % 10);
        carry = (l1.next.val + l2.next.val + carry) / 10;
        addInternal(l1.next, l2.next, l3.next, carry);
    }


}


//todo:教训是，像这样处理多个链表的时候，递归的参数尽量就是各个链表同一级的节点，不要有的参数是上一级有的是下一级。
// 也就是说，处理多个链表，不论是合并，求和，还是咋地，递归的参数都一定是链表的当前节点，不要搞next或者prev。
// 当不太好掌握节省空间的技巧的时候，还是老老实实提高一下空间复杂度。