package indi.simon.learning.leetcode.gogo20220404;

import indi.simon.learning.leetcode.commonmodel.ListNode;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz82 {

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(2);
        ListNode three1 = new ListNode(2);
        ListNode four = new ListNode(5);
//        ListNode four1 = new ListNode(4);
//        ListNode five = new ListNode(5);

        one.next = two;
        two.next = three;
        three.next = three1;
        three1.next = four;
//        four.next = four1;
//        four1.next = five;
        Quiz82 quiz82 = new Quiz82();
        ListNode res = quiz82.deleteDuplicates(one);
        System.out.println(res);

    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode lastNode = null;
        ListNode currentNode = head;
        while (currentNode != null) {
            ListNode nextNode = currentNode.next;
            if (nextNode != null && nextNode.val == currentNode.val) {
                while (nextNode != null && nextNode.val == currentNode.val) {
                    nextNode = nextNode.next;
                }
                currentNode = nextNode;
                if (lastNode == null) {
                    head = currentNode;
                } else {
                    lastNode.next = currentNode;
                }
            } else if (nextNode == null) {
                break;
            } else {
                lastNode = currentNode;
                currentNode = nextNode;
            }
        }

        return head;
    }

}
