package indi.simon.learning.leetcode.gogo20220404;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.ArrayList;
import java.util.List;


/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz99 {

    public static void main(String[] args) {
        TreeNode one = new TreeNode(1);
        TreeNode three = new TreeNode(3);
        TreeNode two = new TreeNode(2);
        TreeNode root = one;
        one.left = three;
        three.right = two;

        Quiz99 quiz99 = new Quiz99();
        quiz99.recoverTree(root);
        System.out.println(root);
    }

    public void recoverTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        tree2ListTreeInternal(root, list);
        TreeNode bigNode = null;
        TreeNode smallNode = null;
        for (int i = 0; i < list.size(); i++) {
            TreeNode iNode = list.get(i);
            if ((i + 1 < list.size() && iNode.val >= list.get(i + 1).val)) {
                bigNode = list.get(i + 1);
                if (smallNode == null) {
                    smallNode = iNode;
                }
            }
        }
        if (bigNode == null || smallNode == null) {
            return;
        }
        recoverTreeInternal(root, bigNode.val, smallNode.val);
    }

    private void tree2ListTreeInternal(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        tree2ListTreeInternal(node.left, list);
        list.add(node);
        tree2ListTreeInternal(node.right, list);
    }

    private void recoverTreeInternal(TreeNode node, int val1, int val2) {
        if (node == null) {
            return;
        }

        if (node.val == val1) {
            node.val = val2;
        } else if (node.val == val2) {
            node.val = val1;
        }

        recoverTreeInternal(node.left, val1, val2);
        recoverTreeInternal(node.right, val1, val2);
    }


}
