package indi.simon.learning.leetcode.gogo2020april.week27to;

import indi.simon.learning.leetcode.commonmodel.ListNode;

public class P24 {

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

        ListNode newHead = swapPairs(one);
        System.out.println(newHead);

    }

    private static ListNode swapPairs(ListNode head) {

        if (head == null) {
            return head;
        }

        if (head.next == null) {
            return head;
        }

        ListNode newHead = head.next;

        ListNode pre = null;
        ListNode currentNode = head;

        while (currentNode != null) {
            ListNode temp = null;
            if(currentNode.next != null){
                temp = currentNode.next;
                currentNode.next = temp.next;
                temp.next = currentNode;
            } else {
                temp = currentNode;
            }

            if (pre != null) {
                pre.next = temp;
            }
            //todo 粗心大意啊，前向节点往前推进是必须的啊
            pre = currentNode;
            currentNode = currentNode.next;
        }

        return newHead;
    }


}
