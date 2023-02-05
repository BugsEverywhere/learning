package indi.simon.learning.leetcode.二叉树;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1145_reviewed {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(1);
//        TreeNode node6 = new TreeNode(6);
//        TreeNode node7 = new TreeNode(7);
//        TreeNode node8 = new TreeNode(8);
//        TreeNode node9 = new TreeNode(9);
//        TreeNode node10 = new TreeNode(10);
//        TreeNode node11 = new TreeNode(11);

//        node1.left = node2;
//        node1.right = node3;
//
//        node2.left = node4;
//        node2.right = node5;
//        node3.left = node6;
//        node3.right = node7;
//
//        node4.left = node8;
//        node4.right = node9;
//        node5.left = node10;
//        node5.right = node11;

        Quiz1145_reviewed quiz1145NeedReview = new Quiz1145_reviewed();
        boolean res = quiz1145NeedReview.btreeGameWinningMoveOfficial(node1, 5, 4);
        System.out.println(res);
    }

    //todo: 官方DFS，思路其实非常简单，一号玩家先手选定了一个节点，那么就将全图分成了三块，只需要找到这三块中节点数超过半数的那一块，就证明了2号选手有成功的可能。
    // 凡事别那么头铁，不是题目说你你一下我一下，就真的当成博弈在搞，唉，也是太年轻。
    TreeNode xNode;

    public boolean btreeGameWinningMoveOfficial(TreeNode root, int n, int x) {
        find(root, x);
        int leftSize = getSubtreeSize(xNode.left);
        if (leftSize >= (n + 1) / 2) {
            return true;
        }
        int rightSize = getSubtreeSize(xNode.right);
        if (rightSize >= (n + 1) / 2) {
            return true;
        }
        int remain = n - 1 - leftSize - rightSize;
        return remain >= (n + 1) / 2;
    }

    public void find(TreeNode node, int x) {
        if (xNode != null || node == null) {
            return;
        }
        if (node.val == x) {
            xNode = node;
            return;
        }
        find(node.left, x);
        find(node.right, x);
    }

    public int getSubtreeSize(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + getSubtreeSize(node.left) + getSubtreeSize(node.right);
    }


}
