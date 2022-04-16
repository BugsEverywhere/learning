package indi.simon.learning.leetcode.gogo20220411;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz113 {

    public static void main(String[] args) {

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(-2);
        TreeNode node3 = new TreeNode(-3);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(-2);
        TreeNode node7 = new TreeNode(-1);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node4.left = node7;

        Quiz113 quiz113 = new Quiz113();
        List<List<Integer>> res = quiz113.pathSum(node1, -1);
        System.out.println(res);
    }

    private List<List<Integer>> res;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        res = new ArrayList<>();
        pathSumInternal(root, targetSum, 0, new ArrayList<>());
        return res;
    }

    private void pathSumInternal(TreeNode node, int targetSum, int sumSoFar, List<Integer> path) {
        if (node == null) {
            return;
        }

        path.add(node.val);
        sumSoFar += node.val;

        if (node.left == null && node.right == null && sumSoFar == targetSum) {
            res.add(new ArrayList<>(path));
        } else {
            //只要sumSoFar!=targetSum都可以往下递归
            if (node.left != null) {
                pathSumInternal(node.left, targetSum, sumSoFar, path);
            }

            if (node.right != null) {
                pathSumInternal(node.right, targetSum, sumSoFar, path);
            }
        }
        //要记得移除脚印
        path.remove(path.size() - 1);
    }

    //todo: 第一次提交没考虑到还有负数的可能，sumSoFar>targetSum的情况仍然需要递归，第二次提交没考虑到中途某个非叶子节点sumSoFar=targetSum，但是仍然需要往下递归的情况

}
