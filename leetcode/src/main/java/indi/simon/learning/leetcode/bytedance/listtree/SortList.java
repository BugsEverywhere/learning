package indi.simon.learning.leetcode.bytedance.listtree;

import indi.simon.learning.leetcode.commonmodel.ListNode;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class SortList {

    public static void main(String[] args) {

        ListNode node1 = new ListNode(-1);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(0);
        //ListNode node6 = new ListNode(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

        ListNode result = sortList(node1);

        System.out.println(result);


    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode currentNode = head.next;
        ListNode leftHeadNode = null;
        ListNode rightHeadNode = null;

        ListNode leftCurrentNode = null;
        ListNode rightCurrentNode = null;
        //提前记录好左端最大节点，这样在排序好的左端链表中他就是最后的尾结点
        ListNode leftMaxNode = null;

        while (currentNode != null) {
            //提前将currentNode节点的next指针保存好，并清除
            ListNode next = currentNode.next;
            currentNode.next = null;
            //快排
            //取头结点作为快排的区分节点，比头结点小，则放在左链，比头结点大，则放在右链
            if (currentNode.val <= head.val) {
                if (leftHeadNode == null) {
                    leftHeadNode = currentNode;
                    leftCurrentNode = leftHeadNode;
                } else {
                    leftCurrentNode.next = currentNode;
                    leftCurrentNode = currentNode;
                }
                //因为需要获取左链的尾结点，亦即最大节点，因此遍历的时候需要记一下这个
                if (leftMaxNode != null && currentNode.val > leftMaxNode.val) {
                    leftMaxNode = currentNode;
                } else if (leftMaxNode == null) {
                    leftMaxNode = leftCurrentNode;
                }
            } else {
                if (rightHeadNode == null) {
                    rightHeadNode = currentNode;
                    rightCurrentNode = rightHeadNode;
                } else {
                    rightCurrentNode.next = currentNode;
                    rightCurrentNode = currentNode;
                }
            }
            currentNode = next;
        }

        leftHeadNode = sortList(leftHeadNode);
        rightHeadNode = sortList(rightHeadNode);

        //leftMaxNode有可能为空，这表示head已经是这条子链中的最小节点了，左链全为空，所以需要判断
        if (leftMaxNode != null) {
            leftMaxNode.next = head;
        }
        head.next = rightHeadNode;

        return leftHeadNode == null ? head : leftHeadNode;
    }

}


