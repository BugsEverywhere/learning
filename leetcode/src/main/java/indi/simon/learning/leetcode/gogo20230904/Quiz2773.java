package indi.simon.learning.leetcode.gogo20230904;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.*;

/**
 * Created by Chen Zhuo on 2023/9/10.
 */
//todo: 注意树的高度的定义，不要审错题。本题我就是两次遍历，第一次找到所有的叶子结点，第二次计算高度，避免对叶子结点的进一步dfs
public class Quiz2773 {

    public static void main(String[] args) {

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node4.left = node5;
        node4.right = node6;
        node2.left = node6;
        node2.right = node5;
        node5.left = node2;
        node5.right = node6;
        node6.left = node5;
        node6.right = node2;

        Quiz2773 quiz2773 = new Quiz2773();
        int res = quiz2773.heightOfTree(node1);
        System.out.println(res);
    }

    private int height;
    private Set<Integer> leafSet;

    public int heightOfTree(TreeNode root) {
        height = Integer.MIN_VALUE;
        leafSet = new HashSet<>();
        detectLeaf(root, new ArrayList<>());

        List<Integer> path = new ArrayList<>();
        heightOfTreeInternal(root, 0);
        return height;
    }

    private void detectLeaf(TreeNode node, List<Integer> path) {
        if (node == null) {
            return;
        }
        if (path.contains(node.val)) {
            leafSet.add(node.val);
            return;
        }
        path.add(node.val);
        detectLeaf(node.left, path);
        detectLeaf(node.right, path);
    }

    private void heightOfTreeInternal(TreeNode node, int currHeight) {
        if (node == null) {
            return;
        }

        height = Math.max(height, currHeight);

        if (!leafSet.contains(node.val)) {
            heightOfTreeInternal(node.left, currHeight + 1);
            heightOfTreeInternal(node.right, currHeight + 1);
        }
    }

}
