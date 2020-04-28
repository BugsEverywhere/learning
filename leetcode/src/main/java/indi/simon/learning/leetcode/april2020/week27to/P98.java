package indi.simon.learning.leetcode.april2020.week27to;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

public class P98 {

    public static void main(String[] args) {

        TreeNode ten = new TreeNode(10);
        TreeNode five = new TreeNode(5);
        TreeNode fifteen = new TreeNode(15);
        TreeNode six = new TreeNode(6);
        TreeNode twenty = new TreeNode(20);

        ten.left = five;
        ten.right = fifteen;

        fifteen.left = six;
        fifteen.right = twenty;

        System.out.println(isValidBST(ten));

    }

    public static boolean isValidBST(TreeNode root) {

        if (root == null) {
            return false;
        }

        return judgeInternal(root);
    }

    private static boolean judgeInternal(TreeNode node) {

        if (node.left == null && node.right == null) {
            return true;
        }

        boolean mark = true;

        //todo 遇到二叉树的问题，没有不递归的，递归这个不能忘
        if (node.left != null) {
            mark = node.val > leftMax(node) && judgeInternal(node.left);
        }

        if (node.right != null) {
            mark = mark && node.val < rightMin(node) && judgeInternal(node.right);
        }

        return mark;

    }

    //todo 二叉搜索树是否合法，不是一个自底向上的分段最优解问题，子节点合格推不出父节点合格，因此必须在每一个父节点自顶向下看
    private static int leftMax(TreeNode node) {

        if (node.left == null) {
            return Integer.MIN_VALUE;
        }

        TreeNode currentNode = node.left;

        while (currentNode.right != null) {
            currentNode = currentNode.right;
        }

        return currentNode.val;

    }

    private static int rightMin(TreeNode node) {

        if (node.right == null) {
            return Integer.MAX_VALUE;
        }

        TreeNode currentNode = node.right;

        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }

        return currentNode.val;

    }

}
