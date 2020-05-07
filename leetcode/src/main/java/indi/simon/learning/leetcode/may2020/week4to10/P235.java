package indi.simon.learning.leetcode.may2020.week4to10;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class P235 {

    public static void main(String[] args) {

        TreeNode six = new TreeNode(6);
        TreeNode two = new TreeNode(2);
        TreeNode eight = new TreeNode(8);
        TreeNode zero = new TreeNode(0);
        TreeNode four = new TreeNode(4);
        TreeNode seven = new TreeNode(7);
        TreeNode nine = new TreeNode(9);
        TreeNode three = new TreeNode(3);
        TreeNode five = new TreeNode(5);

        six.left = two;
        six.right = eight;

        two.left = zero;
        two.right = four;

        eight.left = seven;
        eight.right = nine;

        four.left = three;
        four.right = five;

        TreeNode node = lowestCommonAncestor(six, two, four);

        System.out.println(node);

    }

    private static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (p == q) {
            return p;
        }

        List<TreeNode> pPath = new ArrayList<>();
        List<TreeNode> qPath = new ArrayList<>();

        if (!findTreeNodePath(root, p, pPath) || !findTreeNodePath(root, q, qPath)) {
            return null;
        }

        //todo 路径都找出来了，但是在如何遍历的时候粗心大意，应该从前向后遍历两个list，并且要注意其中一个List先结束的情况
        for (int i = 0; i < Math.max(pPath.size(), qPath.size()); i++) {
            if (i >= pPath.size() || i >= qPath.size() || pPath.get(i) != qPath.get(i)) {
                return pPath.get(i - 1);
            }
        }

        return null;
    }

    private static boolean findTreeNodePath(TreeNode node, TreeNode x, List<TreeNode> path) {
        if (node == null) {
            return false;
        }
        path.add(node);
        if (node == x) {
            return true;
        }

        if (x.val < node.val) {
            boolean leftResult = findTreeNodePath(node.left, x, path);
            if (leftResult) {
                return true;
            }
        } else if (x.val > node.val) {
            boolean rightResult = findTreeNodePath(node.right, x, path);
            if (rightResult) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

}
