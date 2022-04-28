package indi.simon.learning.leetcode.gogo20220425;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz133 {

    public static void main(String[] args) {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);

        List<Node> oneNeighbors = new ArrayList<>();
        oneNeighbors.add(two);
        oneNeighbors.add(four);
        one.neighbors = oneNeighbors;
        List<Node> twoNeighbors = new ArrayList<>();
        twoNeighbors.add(one);
        twoNeighbors.add(three);
        two.neighbors = twoNeighbors;
        List<Node> threeNeighbors = new ArrayList<>();
        threeNeighbors.add(two);
        threeNeighbors.add(four);
        three.neighbors = threeNeighbors;
        List<Node> fourNeighbors = new ArrayList<>();
        fourNeighbors.add(one);
        fourNeighbors.add(three);
        four.neighbors = fourNeighbors;

        Quiz133 quiz133 = new Quiz133();
        Node res = quiz133.cloneGraph(one);
        System.out.println(res);

    }

    Map<Integer, Node> path;

    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        path = new HashMap<>();
        Node copiedNode = new Node(node.val);
        copiedNode.neighbors = node.neighbors;
        cloneGraphInternal(copiedNode);
        return copiedNode;
    }

    /**
     * @param copiedNode 已然是copy节点
     */
    private void cloneGraphInternal(Node copiedNode) {
        if (path.containsKey(copiedNode.val)) {
            return;
        }

        path.put(copiedNode.val, copiedNode);

        List<Node> newNeighbors = new ArrayList<>();
        if (copiedNode.neighbors != null && copiedNode.neighbors.size() > 0) {
            for (Node originNeighbor : copiedNode.neighbors) {
                Node copiedNeighbor;
                if (path.containsKey(originNeighbor.val)) {
                    copiedNeighbor = path.get(originNeighbor.val);
                    //todo: 此处要注意如果路径中已经有该邻居，则邻居也不必递归了，不然反而弄巧成拙把邻居的邻接表换成原来的邻接表
                } else {
                    copiedNeighbor = new Node(originNeighbor.val);
                    copiedNeighbor.neighbors = originNeighbor.neighbors;
                    cloneGraphInternal(copiedNeighbor);
                }
                newNeighbors.add(copiedNeighbor);
            }
        }
        copiedNode.neighbors = newNeighbors;

    }


    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

}
