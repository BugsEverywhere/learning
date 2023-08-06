package indi.simon.learning.leetcode.gogo20230731;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz450 {

    public static void main(String[] args) {

    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }

        if (key == root.val) {
            if (root.right == null) {
                return root.left;
            } else {
                TreeNode originRoot = root;
                root = root.right;
                setLeft(root, originRoot.left);
            }
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            root.left = deleteNode(root.left, key);
        }

        return root;
    }

    private void setLeft(TreeNode curr, TreeNode leftToSet) {
        if (curr.left == null) {
            curr.left = leftToSet;
        } else {
            setLeft(curr.left, leftToSet);
        }
    }


}
