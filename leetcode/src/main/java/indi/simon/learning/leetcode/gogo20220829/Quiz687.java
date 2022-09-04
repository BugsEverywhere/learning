package indi.simon.learning.leetcode.gogo20220829;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz687 {

    public static void main(String[] args) {

    }

    private int maxLength = 1;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        longestUnivaluePathInternal(root);
        return maxLength - 1;
    }

    public int longestUnivaluePathInternal(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftRes = longestUnivaluePathInternal(node.left);
        int rightRes = longestUnivaluePathInternal(node.right);

        int max = 1;
        if (node.left != null && node.val == node.left.val && node.right != null && node.val == node.right.val) {
            maxLength = Math.max(leftRes + rightRes + 1, maxLength);
            max = Math.max(leftRes, rightRes) + 1;
        } else if (node.left != null && node.val == node.left.val) {
            maxLength = Math.max(leftRes + 1, maxLength);
            max = leftRes + 1;
        } else if (node.right != null && node.val == node.right.val) {
            maxLength = Math.max(rightRes + 1, maxLength);
            max = rightRes + 1;
        }

        return max;
    }
}
