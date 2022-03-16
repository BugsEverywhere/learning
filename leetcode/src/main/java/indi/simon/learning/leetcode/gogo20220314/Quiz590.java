package indi.simon.learning.leetcode.gogo20220314;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz590 {

    public static void main(String[] args) {

    }

    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        traversTree(res, root);
        return res;
    }

    private void traversTree(List<Integer> resList, Node currentNode) {
        if (currentNode == null) {
            return;
        }
        if (currentNode.children != null && currentNode.children.size() != 0) {
            for (Node child : currentNode.children) {
                traversTree(resList, child);
            }
        }
        resList.add(currentNode.val);

    }


    private class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

}



