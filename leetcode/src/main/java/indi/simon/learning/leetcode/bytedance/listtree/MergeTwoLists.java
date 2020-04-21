package indi.simon.learning.leetcode.bytedance.listtree;

import indi.simon.learning.leetcode.commonmodel.ListNode;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class MergeTwoLists {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node3;
        node2.next = node4;
        node3.next = node5;
        node5.next = null;

        ListNode result = mergeTwoLists(node1, node2);

        System.out.println(result);

    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode currentNode = null;
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val >= l2.val) {
            currentNode = l2;
            l2 = l2.next;
        } else {
            currentNode = l1;
            l1 = l1.next;
        }
        ListNode head = currentNode;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                currentNode.next = l1;
                l1 = l1.next;
            } else {
                currentNode.next = l2;
                l2 = l2.next;
            }
            currentNode = currentNode.next;
        }

        //处理剩余节点
        if (l1 == null) {
            while (l2 != null) {
                currentNode.next = l2;
                l2 = l2.next;
                currentNode = currentNode.next;
            }
        } else {
            while (l1 != null) {
                currentNode.next = l1;
                l1 = l1.next;
                currentNode = currentNode.next;
            }
        }

        return head;
    }


}
