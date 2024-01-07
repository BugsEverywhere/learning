package indi.simon.learning.leetcode.bytedance.listtree;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {

        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);

        node3.left = node5;
        node3.right = node1;
        node5.left = node6;
        node5.right = node2;
        node1.left = node0;
        node1.right = node8;
        node2.left = node7;
        node2.right = node4;

        TreeNode result = lowestCommonAncestor(node3, node5, node4);
        System.out.println(result);
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        List<TreeNode> pPath = new ArrayList<>();
        findNode(root, p, pPath);

        List<TreeNode> qPath = new ArrayList<>();
        findNode(root, q, qPath);

        for (int i = 0; i < pPath.size() && i < qPath.size(); i++) {
            if (pPath.get(i).equals(qPath.get(i))) {
                continue;
            }
            return pPath.get(i - 1);
        }

        return pPath.size() > qPath.size() ? qPath.get(qPath.size() - 1) : pPath.get(pPath.size() - 1);
    }

    private static boolean findNode(TreeNode currentNode, TreeNode p, List<TreeNode> path) {
        if (currentNode == null || p == null) {
            return false;
        }
        if (currentNode.equals(p)) {
            path.add(p);
            return true;
        } else {
            //先找左子树
            path.add(currentNode);
            boolean leftResult = findNode(currentNode.left, p, path);
            if (leftResult) {
                return true;
            }
            //再找右子树
            boolean rightResult = findNode(currentNode.right, p, path);
            if (rightResult) {
                return true;
            }
            path.remove(currentNode);
        }
        return false;
    }
}
