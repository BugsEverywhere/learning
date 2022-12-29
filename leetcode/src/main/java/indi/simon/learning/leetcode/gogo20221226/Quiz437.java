package indi.simon.learning.leetcode.gogo20221226;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz437 {

    public static void main(String[] args) {

        TreeNode ten = new TreeNode(10);
        TreeNode five = new TreeNode(5);
        TreeNode mThree = new TreeNode(-3);
        TreeNode three = new TreeNode(3);
        TreeNode two = new TreeNode(2);
        TreeNode eleven = new TreeNode(11);
        TreeNode three2 = new TreeNode(3);
        TreeNode mTwo = new TreeNode(-2);
        TreeNode one = new TreeNode(1);

        ten.left = five;
        ten.right = mThree;
        five.left = three;
        five.right = two;
        three.left = three2;
        three.right = mTwo;
        two.right = one;
        mThree.right = eleven;

//        TreeNode root = new TreeNode(1000000000);
//        TreeNode node1 = new TreeNode(1000000000);
//        TreeNode node2 = new TreeNode(294967296);
//        TreeNode node3 = new TreeNode(1000000000);
//        TreeNode node4 = new TreeNode(1000000000);
//        TreeNode node5 = new TreeNode(1000000000);
//
//        root.left = node1;
//        node1.left = node2;
//        node2.left = node3;
//        node3.left = node4;
//        node4.left = node5;


        Quiz437 quiz437 = new Quiz437();
        int res = quiz437.pathSum(ten, 8);
        System.out.println(res);
    }

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int thisNodeRes = pathSumInternal(root, targetSum, 0);
        return pathSum(root.left, targetSum) + pathSum(root.right, targetSum) + thisNodeRes;
    }

    /**
     * @param node
     * @param targetSum
     * @param sumForNow
     * @return 从该节点出发的所有和为targetSum的路径总数
     */
    private int pathSumInternal(TreeNode node, long targetSum, long sumForNow) {
        if (node == null) {
            return 0;
        }

        int count = 0;
        sumForNow += node.val;
        if (sumForNow == targetSum) {
            count++;
        }

        int leftRes = pathSumInternal(node.left, targetSum, sumForNow);
        int rightRes = pathSumInternal(node.right, targetSum, sumForNow);

        return count + leftRes + rightRes;
    }


}
