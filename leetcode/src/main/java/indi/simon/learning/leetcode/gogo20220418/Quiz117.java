package indi.simon.learning.leetcode.gogo20220418;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz117 {

    public static void main(String[] args) {

    }

    public Node connect(Node root) {

        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        Node nextRowMarkNode = root.left == null ? root.right : root.left;

        while (queue.size() != 0) {
            Node node = queue.poll();

            if (queue.size() != 0 && queue.getFirst() != nextRowMarkNode) {
                node.next = queue.getFirst();
            }

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

            if (node == nextRowMarkNode || nextRowMarkNode == null) {
                nextRowMarkNode = (node.left == null ? node.right : node.left);
            }
        }
        return root;
    }


    private class Node {
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
}
