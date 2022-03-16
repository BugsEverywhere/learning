package indi.simon.learning.leetcode.gogo20220314;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz432 {

    public static void main(String[] args) {
        Quiz432 quiz432 = new Quiz432();
        quiz432.inc("hello");
        quiz432.inc("hello");
        quiz432.inc("hello");
        quiz432.inc("leet");
        quiz432.inc("leet");
        quiz432.inc("code");
        quiz432.dec("hello");
        quiz432.dec("hello");
        quiz432.dec("hello");
        quiz432.dec("hello");
        quiz432.dec("hello");
        System.out.println(quiz432.head);

    }

    public Quiz432() {
        countMap = new HashMap<>();
    }

    private Map<String, Node> countMap;
    private Node head;
    private Node tail;

    public void inc(String key) {
        Node node = countMap.get(key);
        if (node == null) {
            Node thisNode = new Node(key, 1);
            countMap.put(key, thisNode);
            if (tail != null) {
                thisNode.pre = tail;
                tail.next = thisNode;
                tail = thisNode;
            } else {
                head = tail = thisNode;
            }
        } else {
            int count = node.count;
            node.count = (count + 1);
            while (node.pre != null && node.count > node.pre.count) {
                swap(node.pre, node);
            }
            if (node.pre == null) {
                head = node;
            }
        }
    }

    public void dec(String key) {
        Node node = countMap.get(key);
        if (node == null) {
            return;
        } else {
            int count = node.count;
            node.count = count - 1;
            while (node.next != null && node.count < node.next.count) {
                swap(node, node.next);
            }
            if (node.next == null) {
                tail = node;
                if (node.count == 0) {
                    tail = node.pre;
                    node.pre = null;
                    node.next = null;
                    tail.next = null;
                    countMap.remove(key);
                }
            }
        }


    }

    public String getMaxKey() {
        if (head == null) {
            return "";
        }
        return head.key;
    }

    public String getMinKey() {
        if (tail == null) {
            return "";
        }
        return tail.key;
    }

    public void swap(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        Node prev = node1.pre;
        Node next = node2.next;
        node2.next = node1;
        node1.next = next;
        node1.pre = node2;
        node2.pre = prev;
        if (prev != null) {
            prev.next = node2;
        }
        if (next != null) {
            next.pre = node1;
        }
        if (head == node1) {
            head = node2;
        }
        if (tail == node2) {
            tail = node1;
        }
    }

    private class Node {

        public Node(String key, Integer count) {
            this.key = key;
            this.count = count;
        }

        private Node pre;
        private Node next;

        private String key;
        private Integer count;

    }

}
