package indi.simon.learning.leetcode.gogo20230625;

import indi.simon.learning.leetcode.commonmodel.ListNode;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2 {

    public static void main(String[] args) {

        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(7);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(6);
        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;

        ListNode node5 = new ListNode(9);
        ListNode node6 = new ListNode(2);
//        ListNode node7 = new ListNode(4);
        node5.next = node6;
//        node6.next = node7;

        Quiz2 quiz2 = new Quiz2();
        ListNode res = quiz2.addTwoNumbers(node1, node5);
        System.out.println(res);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode newHead1 = l1;
        ListNode newHead2 = l2;

        ListNode resHead = null;
        ListNode currLast = null;
        int carry = 0;
        //从头部开始累加
        while (newHead1 != null && newHead2 != null) {
            int[] res = addListNode(newHead1, newHead2);
            int thisBit = (res[0] + carry) % 10;
            int thisCarry = (res[0] + carry) / 10;
            ListNode currRes = new ListNode(thisBit);
            carry = thisCarry + res[1];
            if (currLast != null) {
                currLast.next = currRes;
            }
            currLast = currRes;
            if (resHead == null) {
                resHead = currRes;
            }
            newHead1 = newHead1.next;
            newHead2 = newHead2.next;
        }

        if (newHead1 == null) {
            while (newHead2 != null) {
                int thisRes = newHead2.val + carry;
                int newCarry = thisRes / 10;
                int thisBit = thisRes % 10;
                ListNode currRes = new ListNode(thisBit);
                carry = newCarry;
                if (currLast != null) {
                    currLast.next = currRes;
                }
                currLast = currRes;
                if (resHead == null) {
                    resHead = currRes;
                }
                newHead2 = newHead2.next;
            }
        } else {
            while (newHead1 != null) {
                int thisRes = newHead1.val + carry;
                int newCarry = thisRes / 10;
                int thisBit = thisRes % 10;
                ListNode currRes = new ListNode(thisBit);
                carry = newCarry;
                if (currLast != null) {
                    currLast.next = currRes;
                }
                currLast = currRes;
                if (resHead == null) {
                    resHead = currRes;
                }
                newHead1 = newHead1.next;
            }
        }

        if (carry > 0) {
            ListNode currRes = new ListNode(carry);
            if (currLast != null) {
                currLast.next = currRes;
            }
        }

        return resHead;
    }

    /**
     * 返回的是l1和l2相加的和，以及进位,int[0]是和，int[1]是进位
     *
     * @param l1
     * @param l2
     * @return
     */
    private int[] addListNode(ListNode l1, ListNode l2) {
        int[] arr = new int[2];
        int res = l1.val + l2.val;
        int thisBit = res % 10;
        int carry = res / 10;
        arr[0] = thisBit;
        arr[1] = carry;
        return arr;
    }


}
