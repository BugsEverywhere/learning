package indi.simon.learning.leetcode.gogo20231022;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

/**
 * Created by Chen Zhuo on 2023/10/22.
 */
//todo: 值得好好总结反思一下，提交次数有点多
public class Quiz333 {

    public static void main(String[] args) {
        Quiz333 quiz333 = new Quiz333();
        TreeNode node1 = new TreeNode(3);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(4);
//        TreeNode node4 = new TreeNode(1);
////        TreeNode node5 = new TreeNode(8);
////        TreeNode node6 = new TreeNode(7);
//
//        node1.left = node2;
//        node1.right = node3;
//        node2.left = node4;
//        node2.right = node5;

//        node3.left = node4;

        int res = quiz333.largestBSTSubtree(node1);
        System.out.println(res);
    }

    private int maxSize;

    public int largestBSTSubtree(TreeNode root) {
        maxSize = 1;
        if (root == null) {
            return 0;
        }
        int[] left = largestBSTSubtreeInternal(root.left);
        int[] right = largestBSTSubtreeInternal(root.right);
        if (left[0] >= 0 && right[0] >= 0 && root.val > left[2] && root.val < right[1]) {
            int size = left[0] + right[0] + 1;
            maxSize = Math.max(maxSize, size);
            return size;
        } else {
            return maxSize;
        }
    }

    private int[] largestBSTSubtreeInternal(TreeNode node) {
        if (node == null) {
            //0位为子树的大小，返回-1代表该子树不是有效的BST，1位为子树最小值，2位为子树最大值
            return new int[]{0, Integer.MAX_VALUE, Integer.MIN_VALUE};
        }

        int[] left = largestBSTSubtreeInternal(node.left);
        int[] right = largestBSTSubtreeInternal(node.right);
        int size;
        if (left[0] >= 0 && right[0] >= 0 && node.val > left[2] && node.val < right[1]) {
            size = left[0] + right[0] + 1;
            maxSize = Math.max(maxSize, size);
            return new int[]{size, left[1] == Integer.MAX_VALUE ? node.val : left[1], right[2] == Integer.MIN_VALUE ? node.val : right[2]};
        } else {
            return new int[]{-1};
        }
    }

}
