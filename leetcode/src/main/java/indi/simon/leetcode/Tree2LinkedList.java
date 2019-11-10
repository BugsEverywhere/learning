package indi.simon.leetcode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class Tree2LinkedList {


    public static void main(String[] args) {

    }

    public static void tree2List(Node root) {
        List<Node> traverseResult = inorderTraversal(root);
        //再对traverseResult处理，连成双向链表，
        Node lastOne = null;
        for (Node node : traverseResult) {
            node.pre = lastOne;
            if (lastOne != null) {
                lastOne.next = lastOne;
            }
            lastOne = node;
        }


    }

    public static List<Node> inorderTraversal(Node root) {
        List<Node> result = new ArrayList<>();
        traversalInternal(result, root);
        return result;
    }

    private static void traversalInternal(List<Node> nodeList, Node father) {
        if (father.left != null) {
            traversalInternal(nodeList, father.left);
        }
        nodeList.add(father);
        if (father.right != null) {
            traversalInternal(nodeList, father.right);
        }
    }

    private static class Node {

        private int val;

        private Node pre;
        private Node next;

        private Node left;
        private Node right;

        public Node(int val, Node pre, Node next, Node left, Node right) {
            this.val = val;
            this.pre = pre;
            this.next = next;
            this.left = left;
            this.right = right;
        }
    }


}