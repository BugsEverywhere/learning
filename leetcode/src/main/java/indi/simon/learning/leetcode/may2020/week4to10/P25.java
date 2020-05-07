package indi.simon.learning.leetcode.may2020.week4to10;

import indi.simon.learning.leetcode.commonmodel.ListNode;

import java.util.HashMap;
import java.util.Map;

public class P25 {
    public static void main(String[] args) {

        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        ListNode six = new ListNode(6);
        ListNode seven = new ListNode(7);
        ListNode eight = new ListNode(8);

        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        six.next = seven;
        seven.next = eight;

        ListNode reversed = reverseKGroup(one, 3);

        System.out.println(reversed);

    }

    private static ListNode reverseKGroup(ListNode head, int k) {

        if (head == null) {
            return null;
        }
        ListNode newHead = null;
        ListNode startNode = head;
        ListNode endNode = head;
        ListNode leftOutNode = null;
        while (startNode != null) {
            int moveCount = 1;
            while (moveCount < k && endNode.next != null) {
                endNode = endNode.next;
                moveCount++;
            }
            if(moveCount < k){
                //最后一轮，且剩余节点不足k个
                leftOutNode.next = startNode;
                break;
            }
            ListNode nextRoundStartNode = endNode.next;
            endNode.next = null;
            Map<String, ListNode> reverResult = reverseInternal(startNode);
            //todo 分组链表翻转，在组内要想象成已经转过来之后的样子，比如 1 → 2 → 3 转过来之后是 3 → 2 → 1，而不是1 ← 2 ← 3，前面的节点需要next到3而不是1。
            ListNode newEndNode = reverResult.get("end");
            ListNode newStartNode = reverResult.get("start");
            //todo 之前节点为null的时候，说明这是第一组，要把返回的结果记录下来
            if (leftOutNode != null) {
                leftOutNode.next = newStartNode;
            } else {
                newHead = newStartNode;
            }
            leftOutNode = newEndNode;
            startNode = nextRoundStartNode;
            endNode = nextRoundStartNode;
        }
        return newHead;
    }


    private static Map<String, ListNode> reverseInternal(ListNode startNode) {
        ListNode lastNode = null;
        ListNode currentNode = startNode;
        ListNode tmpNode = null;
        while (currentNode != null) {
            //todo 简单的链表翻转。。。。居然忘了把next指针保存下来
            tmpNode = currentNode.next;
            currentNode.next = lastNode;
            lastNode = currentNode;
            currentNode = tmpNode;
        }
        Map<String, ListNode> map = new HashMap<>();
        map.put("end", startNode);
        map.put("start", lastNode);
        return map;
    }

}
