package indi.simon.learning.leetcode.gogo20230417;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1026 {

    public static void main(String[] args) {

    }

    private int res;

    public int maxAncestorDiff(TreeNode root) {
        if (root == null) {
            return -1;
        }
        res = Integer.MIN_VALUE;
        maxAncestorDiffInternal(root.val, root.val, root);
        return res;
    }

    private void maxAncestorDiffInternal(int maxSoFar, int minSoFar, TreeNode node) {
        int gap = Math.abs(maxSoFar - minSoFar);
        if (gap > res) {
            res = gap;
        }
        if (node == null) {
            return;
        }

        maxSoFar = Math.max(maxSoFar, node.val);
        minSoFar = Math.min(minSoFar, node.val);

        maxAncestorDiffInternal(maxSoFar, minSoFar, node.right);
        maxAncestorDiffInternal(maxSoFar, minSoFar, node.left);

    }
}
