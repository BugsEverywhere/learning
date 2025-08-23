package indi.simon.learning.复习.二叉树;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Quiz285_二叉搜索树的中序后继 {

    public static void main(String[] args) {

    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        List<TreeNode> path = new ArrayList<>();
        inorder(root, path);

        for (int i = 0; i < path.size(); i++) {
            if (path.get(i).val == p.val) {
                return i + 1 < path.size() ? path.get(i + 1) : null;
            }
        }
        return null;
    }

    public void inorder(TreeNode node, List<TreeNode> path) {
        if (node == null) {
            return;
        }
        inorder(node.left, path);
        path.add(node);
        inorder(node.right, path);
    }


}
