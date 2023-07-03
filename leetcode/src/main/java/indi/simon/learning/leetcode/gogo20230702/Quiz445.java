package indi.simon.learning.leetcode.gogo20230702;

import indi.simon.learning.leetcode.commonmodel.ListNode;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz445 {

    public static void main(String[] args) {

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode l1Last = null;
        ListNode l1Curr = l1;
        ListNode l1Next = l1Curr.next;
        while (l1Curr != null) {
            l1Curr.next = l1Last;
            l1Last = l1Curr;
            l1Curr = l1Next;
            if (l1Curr != null) {
                l1Next = l1Curr.next;
            }
        }

        ListNode l2Last = null;
        ListNode l2Curr = l2;
        ListNode l2Next = l2Curr.next;
        while (l2Curr != null) {
            l2Curr.next = l2Last;
            l2Last = l2Curr;
            l2Curr = l2Next;
            if (l2Curr != null) {
                l2Next = l2Curr.next;
            }
        }


        ListNode currLast = null;
        ListNode curr = null;
        int carry = 0;
        while (l2Last != null && l1Last != null) {
            int res = (l2Last.val + l1Last.val + carry) % 10;
            carry = (l2Last.val + l1Last.val + carry) / 10;
            curr = new ListNode(res);
            curr.next = currLast;
            currLast = curr;
            l2Last = l2Last.next;
            l1Last = l1Last.next;
        }

        ListNode moreNode = null;
        if (l2Last == null && l1Last != null) {
            moreNode = l1Last;
        } else if (l1Last == null && l2Last != null) {
            moreNode = l2Last;
        }

        while (moreNode != null) {
            int res = (moreNode.val + carry) % 10;
            carry = (moreNode.val + carry) / 10;
            curr = new ListNode(res);
            curr.next = currLast;
            currLast = curr;
            moreNode = moreNode.next;
        }

        if(carry > 0){
            curr = new ListNode(carry);
            curr.next = currLast;
        }

        return curr;
    }


}
