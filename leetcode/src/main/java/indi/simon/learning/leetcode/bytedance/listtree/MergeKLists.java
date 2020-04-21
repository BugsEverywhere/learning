package indi.simon.learning.leetcode.bytedance.listtree;

import indi.simon.learning.leetcode.commonmodel.ListNode;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class MergeKLists {

    public static void main(String[] args) {

        ListNode listNode1 = new ListNode(1);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode11 = new ListNode(1);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode44 = new ListNode(4);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode6 = new ListNode(6);

        listNode1.next = listNode4;
        listNode4.next = listNode5;

        listNode11.next = listNode3;
        listNode3.next = listNode44;

        listNode2.next = listNode6;

        ListNode[] lists = new ListNode[3];
        lists[0] = listNode1;
        lists[1] = listNode11;
        lists[2] = listNode2;

        ListNode result = mergeKLists(lists);

        System.out.println(result);
    }

    public static ListNode mergeKLists(ListNode[] lists) {

        ListNode headNode = null;
        ListNode currentNode = null;

        while (true) {
            int nullNum = 0;
            int minValThisRound = Integer.MAX_VALUE;
            int minListIndex = 0;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) {
                    nullNum++;
                    continue;
                }
                if (lists[i].val < minValThisRound) {
                    minListIndex = i;
                    minValThisRound = lists[i].val;
                }
            }
            //全为null了退出while循环
            if (nullNum == lists.length) {
                break;
            }
            if (headNode == null) {
                headNode = lists[minListIndex];
                currentNode = lists[minListIndex];
            } else {
                currentNode.next = lists[minListIndex];
                currentNode = currentNode.next;
            }
            lists[minListIndex] = lists[minListIndex].next;
        }

        return headNode;
    }

}
