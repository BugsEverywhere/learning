package indi.simon.learning.leetcode.gogo20230522;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1080 {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(-3);
        TreeNode node4 = new TreeNode(-5);
        TreeNode node5 = new TreeNode(4);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node3.right = node5;

        Quiz1080 quiz1080 = new Quiz1080();

        TreeNode node = quiz1080.sufficientSubset(node1, -1);
        System.out.println(node);
    }

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        int maxRootPath = maxPathSum(root, limit, 0);
        return maxRootPath < limit ? null : root;
    }

    private int maxPathSum(TreeNode node, int limit, int sumSoFar) {

        Integer leftMaxSum = null;
        if (node.left != null) {
            leftMaxSum = maxPathSum(node.left, limit, sumSoFar + node.val);
            if (leftMaxSum < limit) {
                node.left = null;
            }
        }

        Integer rightMaxSum = null;
        if (node.right != null) {
            rightMaxSum = maxPathSum(node.right, limit, sumSoFar + node.val);
            if (rightMaxSum < limit) {
                node.right = null;
            }
        }

        if (leftMaxSum != null && rightMaxSum != null) {
            return Math.max(leftMaxSum, rightMaxSum);
        } else if (leftMaxSum != null) {
            return leftMaxSum;
        } else if (rightMaxSum != null) {
            return rightMaxSum;
        } else {
            return sumSoFar + node.val;
        }
    }

}
