package indi.simon.learning.leetcode.april2020.week20to26;

import java.util.HashMap;
import java.util.Map;

public class P146 {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(2, 1);
        cache.put(2, 2);
        System.out.println(cache.get(2));
        cache.put(1, 1);
        cache.put(4, 1);
        System.out.println(cache.get(2));
    }


    static class LRUCache {

        private Map<Integer, Node> cache;
        private int limit;
        private Node first;
        private Node tail;

        public LRUCache(int capacity) {
            cache = new HashMap<>();
            limit = capacity;
        }

        public int get(int key) {
            Node node = cache.get(key);
            if (node == null) {
                return -1;
            }
            //todo: 此处应该分为四种情况讨论，既是头节点也是尾节点（do nothing），是头节点但不是尾节点（do nothing），
            // 既不是头节点也不是尾节点，是尾节点但不是头节点。。。。。。第一次提交的时候首先是忘了get操作也会导致LRU
            // 缓存更新，第二次提交就暴露了四种情况考虑不周的问题。
            if (node != tail && node != first) {
                node.next.pre = node.pre;
                node.pre.next = node.next;
                node.next = first;
                first.pre = node;
                first = node;
            } else if (node == tail && node != first) {
                tail = node.pre;
                node.pre.next = node.next;
                node.next = first;
                first.pre = node;
                first = node;
            }
            return node.getValue();
        }

        public void put(int key, int value) {
            if (first == null) {
                Node node = new Node(key, value);
                first = node;
                tail = node;
                cache.put(key, node);
                return;
            }
            Node node = cache.get(key);
            //todo: put时大方向分为put已有key的值和put新值两种情形讨论
            if (node == null) {
                node = new Node(key, value);
                node.next = first;
                first.pre = node;
                first = node;
                if (cache.entrySet().size() >= limit) {
                    cache.remove(tail.getKey());
                    tail = tail.pre;
                    tail.next = null;
                }
            } else {
                //todo: put一个已有key的值的时候，需要对链表进行的操作与get时是一样的
                node.setValue(value);
                if (node != tail && node != first) {
                    node.next.pre = node.pre;
                    node.pre.next = node.next;
                    node.next = first;
                    first.pre = node;
                    first = node;
                } else if (node == tail && node != first) {
                    tail = node.pre;
                    node.pre.next = node.next;
                    node.next = first;
                    first.pre = node;
                    first = node;
                }
            }
            cache.put(key, node);
        }
    }

    static class Node {
        private Integer key;
        private Integer value;
        private Node next;
        private Node pre;

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPre() {
            return pre;
        }

        public void setPre(Node pre) {
            this.pre = pre;
        }
    }

}
