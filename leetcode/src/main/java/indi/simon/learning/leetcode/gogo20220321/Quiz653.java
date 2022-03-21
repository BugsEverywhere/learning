package indi.simon.learning.leetcode.gogo20220321;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz653 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(3);
        TreeNode right = new TreeNode(6);
        TreeNode leftLeft = new TreeNode(2);
        TreeNode leftRight = new TreeNode(4);
        TreeNode rightRight = new TreeNode(7);

        root.left = left;
        root.right = right;
        left.left = leftLeft;
        left.right = leftRight;
        right.right = rightRight;

        Quiz653 quiz653 = new Quiz653();
        boolean res = quiz653.findTarget(root, 11);
        System.out.println(res);
    }

    public boolean findTarget(TreeNode root, int k) {
        return findTargetInternal(root, k, new HashSet<>());
    }

    private boolean findTargetInternal(TreeNode node, int k, Set<Integer> allNodesForNow) {
        if (node == null) {
            return false;
        }

        if (allNodesForNow.contains(k - node.val)) {
            return true;
        }

        allNodesForNow.add(node.val);

        boolean leftRes = findTargetInternal(node.left, k, allNodesForNow);
        if (leftRes) {
            return leftRes;
        }
        return findTargetInternal(node.right, k, allNodesForNow);
    }

}
