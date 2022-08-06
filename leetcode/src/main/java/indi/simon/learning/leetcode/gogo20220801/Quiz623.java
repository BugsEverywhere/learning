package indi.simon.learning.leetcode.gogo20220801;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz623 {

    public static void main(String[] args) {

    }

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }
        addOneRowInternal(root, val, depth, 1);
        return root;
    }

    private void addOneRowInternal(TreeNode node, int val, int depth, int curDepth) {
        if (node == null) {
            return;
        }
        if (curDepth < depth - 1) {
            addOneRowInternal(node.left, val, depth, curDepth + 1);
            addOneRowInternal(node.right, val, depth, curDepth + 1);
        } else if (curDepth == depth - 1) {
            TreeNode leftNewNode = new TreeNode(val);
            TreeNode rightNewNode = new TreeNode(val);
            leftNewNode.left = node.left;
            rightNewNode.right = node.right;
            node.left = leftNewNode;
            node.right = rightNewNode;
        }
    }

}
