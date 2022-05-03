package indi.simon.learning.leetcode.gogo20220502;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz173 {

    public static void main(String[] args) {

    }

    private List<Integer> list;
    private Iterator<Integer> iterator;

    public Quiz173(TreeNode root) {
        list = new LinkedList<>();
        inOrder(root);
        iterator = list.iterator();
    }

    private void inOrder(TreeNode node) {
        if (node.left != null) {
            inOrder(node.left);
        }
        list.add(node.val);
        if (node.right != null) {
            inOrder(node.right);
        }
    }

    public int next() {
        return iterator.next();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }
}
