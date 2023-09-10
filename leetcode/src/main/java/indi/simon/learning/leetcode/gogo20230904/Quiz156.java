package indi.simon.learning.leetcode.gogo20230904;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

/**
 * Created by Chen Zhuo on 2023/9/10.
 */
public class Quiz156 {

    public static void main(String[] args) {

    }

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            TreeNode newRoot = upsideDownBinaryTreeInternal(root.left, root, root.right);
            root.left = null;
            root.right = null;
            return newRoot;
        }
        return root;
    }

    private TreeNode upsideDownBinaryTreeInternal(TreeNode node, TreeNode parent, TreeNode originRight) {
        TreeNode originThisLeft = node.left;
        TreeNode originThisRight = node.right;
        node.right = parent;
        node.left = originRight;
        if (originThisLeft != null) {
            return upsideDownBinaryTreeInternal(originThisLeft, node, originThisRight);
        } else {
            return node;
        }
    }


}
