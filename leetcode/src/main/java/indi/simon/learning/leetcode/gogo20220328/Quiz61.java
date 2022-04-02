package indi.simon.learning.leetcode.gogo20220328;

import indi.simon.learning.leetcode.commonmodel.ListNode;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz61 {

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;

        Quiz61 quiz61 = new Quiz61();
        ListNode res = quiz61.rotateRight(one, 5);
        System.out.println(res);
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        int nodeCount = 0;
        ListNode currentNode = head;
        ListNode tail = null;
        while (currentNode != null) {
            nodeCount++;
            tail = currentNode;
            currentNode = currentNode.next;
        }

        int cutCount = k % nodeCount;
        if (cutCount != 0) {
            //不能整除的情况，需要移动
            int nodeCountAgain = 1;
            currentNode = head;
            while (nodeCount - nodeCountAgain > cutCount) {
                currentNode = currentNode.next;
                nodeCountAgain++;
            }

            ListNode newHead = currentNode.next;
            currentNode.next = null;
            tail.next = head;

            return newHead;
        } else {
            //可以整除的情况，不用移动
            return head;
        }
    }
}
