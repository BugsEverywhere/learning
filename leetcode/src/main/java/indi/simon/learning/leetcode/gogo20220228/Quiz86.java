package indi.simon.learning.leetcode.gogo20220228;

import indi.simon.learning.leetcode.commonmodel.ListNode;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz86 {

    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(2);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;


        Quiz86 quiz86 = new Quiz86();
        ListNode res = quiz86.partition(node1, 3);
        System.out.println(res);

    }

    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }

        ListNode lastLessNode = null;
        ListNode lessHead = null;

        ListNode currentNode = head;
        ListNode lastBiggerNode = null;
        ListNode biggerHead = null;
        do {
            if (currentNode.val < x) {
                if (lastLessNode != null) {
                    lastLessNode.next = currentNode;
                } else {
                    lessHead = currentNode;
                }
                lastLessNode = currentNode;
            } else {
                if (lastBiggerNode != null) {
                    lastBiggerNode.next = currentNode;
                } else {
                    biggerHead = currentNode;
                }
                lastBiggerNode = currentNode;
            }
            currentNode = currentNode.next;
            if (lastBiggerNode != null) {
                lastBiggerNode.next = null;
            }
        } while (currentNode != null);


        if (lastLessNode != null) {
            lastLessNode.next = biggerHead;
            return lessHead;
        } else {
            return biggerHead;
        }
    }

}
