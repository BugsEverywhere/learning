package indi.simon.learning.leetcode.gogo20220829;

import indi.simon.learning.leetcode.commonmodel.TreeNode;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz662 {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(0);
        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(0);
        TreeNode node5 = new TreeNode(1);
        TreeNode node6 = new TreeNode(2);
        TreeNode node7 = new TreeNode(3);
        TreeNode node8 = new TreeNode(4);
        TreeNode node9 = new TreeNode(5);

        node1.right = node2;
        node2.right = node3;
        node3.right = node4;
        node4.left = node5;
        node4.right = node6;
        node5.left = node7;
        node5.right = node8;
        node6.right = node9;

        Quiz662 quiz662ReviewedTobeReviewed = new Quiz662();
        int res = quiz662ReviewedTobeReviewed.widthOfBinaryTree(node1);
        System.out.println(res);
    }

    //todo: 这种BFS层序遍历二叉树的方式可以学学
    public int widthOfBinaryTree(TreeNode root) {
        int res = 1;
        List<Pair<TreeNode, Integer>> queue = new ArrayList<>();
        queue.add(new Pair<>(root, 1));
        while (!queue.isEmpty()) {
            List<Pair<TreeNode, Integer>> nextLayer = new ArrayList<>();
            for (Pair<TreeNode, Integer> pair : queue) {
                TreeNode node = pair.getKey();
                int index = pair.getValue();
                if (node.left != null) {
                    nextLayer.add(new Pair<>(node.left, index * 2));
                }
                if (node.right != null) {
                    nextLayer.add(new Pair<>(node.right, index * 2 + 1));
                }
                System.out.println(index);
            }
            res = Math.max(res, queue.get(queue.size() - 1).getValue() - queue.get(0).getValue() + 1);
            queue = nextLayer;
        }
        return res;
    }


    //=========================================================================自己的提交
    public int widthOfBinaryTreeSelf(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<int[]> layerStartEnd = new ArrayList<>();
        widthOfBinaryTreeInternal(root, 1, 0, layerStartEnd);

        int res = Integer.MIN_VALUE;
        for (int[] startEnd : layerStartEnd) {
            res = Math.max(res, startEnd[1] - startEnd[0] + 1);
            System.out.println(startEnd[0]);
            System.out.println(startEnd[1]);
        }

        return res;
    }

    private void widthOfBinaryTreeInternal(TreeNode node, int level, int index, List<int[]> layerStartEnd) {
        if (node == null) {
            return;
        }

        if (layerStartEnd.size() < level) {
            int[] thisLevel = new int[2];
            //todo:被这个困扰了好久，不能简单约定成-1，因为在层数很多的情况下，index会溢出等于-1，此时跟这个约定的值相同了就。。。导致后头计算会有错误
            thisLevel[0] = -12306;
            thisLevel[1] = -12306;
            layerStartEnd.add(thisLevel);
        }

        if (layerStartEnd.get(level - 1)[0] == -12306) {
            layerStartEnd.get(level - 1)[0] = index;
        }
        layerStartEnd.get(level - 1)[1] = index;
        widthOfBinaryTreeInternal(node.left, level + 1, index * 2, layerStartEnd);
        widthOfBinaryTreeInternal(node.right, level + 1, index * 2 + 1, layerStartEnd);
    }


}
