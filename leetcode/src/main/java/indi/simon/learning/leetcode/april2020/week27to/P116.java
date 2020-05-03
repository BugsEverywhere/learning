package indi.simon.learning.leetcode.april2020.week27to;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P116 {

    public static void main(String[] args) {

    }

    private static Node connect(Node root) {

        Map<Integer, List<Node>> listMap = new HashMap<>();
        connectInternal(root, listMap, 0);
        for (Map.Entry<Integer, List<Node>> singleEntry : listMap.entrySet()) {
            List<Node> listThisLevel = singleEntry.getValue();
            for (int i = 0; i < listThisLevel.size(); i++) {
                if (i != listThisLevel.size() - 1) {
                    listThisLevel.get(i).next = listThisLevel.get(i + 1);
                } else {
                    listThisLevel.get(i).next = null;
                }
            }
        }

        return root;
    }

    private static void connectInternal(Node node, Map<Integer, List<Node>> listMap, int level) {
        if (node == null) {
            return;
        }
        List<Node> nodeListThisLevel = listMap.computeIfAbsent(level, k -> new ArrayList<>());
        nodeListThisLevel.add(node);
        connectInternal(node.left, listMap, level + 1);
        connectInternal(node.right, listMap, level + 1);

    }


    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;

}
