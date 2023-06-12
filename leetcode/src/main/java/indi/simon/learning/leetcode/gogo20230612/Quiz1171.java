package indi.simon.learning.leetcode.gogo20230612;

import indi.simon.learning.leetcode.commonmodel.ListNode;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1171 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(-3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        Quiz1171 quiz1171 = new Quiz1171();
        ListNode res = quiz1171.removeZeroSumSublists(node1);
        System.out.println(res);
    }

    public ListNode removeZeroSumSublists(ListNode head) {

        int nodeCnt = 0;
        ListNode node = head;
        while (node != null) {
            nodeCnt++;
            node = node.next;
        }

        //numArr[i]是从该节点到当前节点的总和
        int[] numArr = new int[nodeCnt];
        //arr[i]标识指向i节点的节点
        ListNode[] arr = new ListNode[nodeCnt];

        ListNode curr = head;
        ListNode lastNode = null;
        int nodeIndex = 0;

        while (curr != null) {
            arr[nodeIndex] = lastNode;
            //遍历i之前的所有节点，及其本身，累加一下他的值
            int i = 0;
            for (; i <= nodeIndex; i++) {
                numArr[i] += curr.val;
                if (numArr[i] == 0) {
                    //需要删掉[i,nodeIndex]的所有节点，处理一下节点的引用
                    if (i == 0) {
                        head = curr.next;
                    } else {
                        curr = curr.next;
                        arr[i].next = curr;
                    }
                    //然后从头开始
                    curr = head;
                    Arrays.fill(numArr, 0);
                    Arrays.fill(arr, null);
                    break;
                }
            }

            if (i > nodeIndex) {
                lastNode = curr;
                curr = curr.next;
                nodeIndex++;
            } else {
                nodeIndex = 0;
            }
        }

        return head;
    }

}
