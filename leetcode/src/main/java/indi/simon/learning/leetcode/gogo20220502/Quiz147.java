package indi.simon.learning.leetcode.gogo20220502;

import indi.simon.learning.leetcode.commonmodel.ListNode;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz147 {

    public static void main(String[] args) {
        ListNode four = new ListNode(4);
        ListNode two = new ListNode(2);
        ListNode one = new ListNode(1);
        ListNode three = new ListNode(3);

        four.next = two;
        two.next = one;
        one.next = three;
        Quiz147 quiz147 = new Quiz147();

        ListNode res = quiz147.insertionSortList(four);
        System.out.println(res);

    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode newList = head;
        ListNode curr = head.next;
        head.next = null;
        while (curr != null) {
            ListNode originNext = curr.next;
            ListNode newListCurr = newList;
            while (newListCurr != null) {
                if (curr.val <= newList.val) {
                    curr.next = newList;
                    newList = curr;
                    break;
                } else if (curr.val >= newListCurr.val && (newListCurr.next == null || curr.val <= newListCurr.next.val)) {
                    ListNode newListCurrOriginNext = newListCurr.next;
                    newListCurr.next = curr;
                    curr.next = newListCurrOriginNext;
                    break;
                }
                newListCurr = newListCurr.next;
            }
            curr = originNext;
        }


        return newList;
    }
}
