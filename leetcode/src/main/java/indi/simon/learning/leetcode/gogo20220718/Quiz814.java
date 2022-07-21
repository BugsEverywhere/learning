package indi.simon.learning.leetcode.gogo20220718;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 第一次提交又粗心大意，没注意根节点也有可能要剔除的情况
public class Quiz814 {

    public static void main(String[] args) {

    }

    public TreeNode pruneTree(TreeNode root) {
        boolean rootRes = pruneTreeInternal(root);
        return rootRes ? root : null;
    }

    private boolean pruneTreeInternal(TreeNode node) {
        if (node == null) {
            return false;
        }

        boolean leftHasOne = pruneTreeInternal(node.left);
        if (!leftHasOne) {
            node.left = null;
        }

        boolean rightHasOne = pruneTreeInternal(node.right);
        if (!rightHasOne) {
            node.right = null;
        }

        return node.val == 1 || leftHasOne || rightHasOne;

    }

}
