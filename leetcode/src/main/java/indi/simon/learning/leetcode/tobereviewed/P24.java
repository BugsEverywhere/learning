package indi.simon.learning.leetcode.tobereviewed;

import indi.simon.learning.leetcode.commonmodel.ListNode;

public class P24 {

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);

        one.next = two;
        two.next = three;
        three.next = four;

        ListNode res = swapPairs(one);

        System.out.println(res);
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode headNext = head.next;
        if (headNext == null) {
            return head;
        }
        ListNode last = head;
        ListNode nextRoundNode = headNext.next;
        headNext.next = head;
        //todo 一定要注意每一轮的last节点要断后，不然会有环
        last.next = null;
        while (nextRoundNode != null) {
            ListNode currentNode = nextRoundNode;
            ListNode currentNextNode = nextRoundNode.next;
            if (currentNextNode == null) {
                last.next = currentNode;
                break;
            }
            nextRoundNode = currentNextNode.next;
            last.next = currentNextNode;
            currentNextNode.next = currentNode;
            last = currentNode;
            //todo 一定要注意每一轮的last节点要断后，不然会有环
            last.next = null;
        }
        return headNext;
    }
}
