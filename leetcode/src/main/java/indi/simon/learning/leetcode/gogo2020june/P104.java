package indi.simon.learning.leetcode.gogo2020june;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

public class P104 {

    public static void main(String[] args) {

    }

    private static int maxDepth(TreeNode root) {
        return maxDepthInternal(root, 0);
    }

    private static int maxDepthInternal(TreeNode node, int level) {
        if (node == null) {
            return level;
        }
        return Math.max(maxDepthInternal(node.left, level + 1), maxDepthInternal(node.right, level + 1));
    }


}
