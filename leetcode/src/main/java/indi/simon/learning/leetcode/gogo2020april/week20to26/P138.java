package indi.simon.learning.leetcode.gogo2020april.week20to26;

import java.util.HashMap;
import java.util.Map;

public class P138 {

    public static void main(String[] args) {

        Node haedNode = new Node(7);
        Node thirteenNode = new Node(13);
        Node elevenNode = new Node(11);
        Node tenNode = new Node(10);
        Node oneNode = new Node(1);

        haedNode.next = thirteenNode;
        haedNode.random = null;
        thirteenNode.next = elevenNode;
        thirteenNode.random = haedNode;
        elevenNode.next = tenNode;
        elevenNode.random = oneNode;
        tenNode.next = oneNode;
        tenNode.random = elevenNode;
        oneNode.next = null;
        oneNode.random = haedNode;

        copyRandomList(haedNode);
    }

    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Node> nodeMap = new HashMap<>();
        Node newHeadNode = new Node(head.val);
        Node newListCurrentNode = newHeadNode;
        Node currentNode = head;
        nodeMap.put(currentNode, newListCurrentNode);
        //todo 两个链表的遍历这里纠结了很久，以后可以模板化，多个链表的遍历都采用这种形式，即while判断条件里是标杆链表的next指针是否为null。
        // 此时因为判断跳出循环的条件，使得标杆列表先走一步，因此需要在循环结束后在标杆列表的最后一个节点处补刀一下。
        while (currentNode.next != null) {
            newListCurrentNode.next = new Node(currentNode.next.val);
            newListCurrentNode.random = currentNode.random;
            nodeMap.put(currentNode, newListCurrentNode);
            newListCurrentNode = newListCurrentNode.next;
            currentNode = currentNode.next;
        }
        newListCurrentNode.random = currentNode.random;
        nodeMap.put(currentNode, newListCurrentNode);

        newListCurrentNode = newHeadNode;
        do {
            //todo 差点忘记判空，采用Map来登记标杆链表和新链表各个节点的话，Map的Key为空就玩完了
            if (newListCurrentNode.random != null) {
                newListCurrentNode.random = nodeMap.get(newListCurrentNode.random);
            }
        } while ((newListCurrentNode = newListCurrentNode.next) != null);

        return newHeadNode;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

}
