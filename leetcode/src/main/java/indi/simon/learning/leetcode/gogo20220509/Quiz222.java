package indi.simon.learning.leetcode.gogo20220509;

import indi.simon.learning.leetcode.commonmodel.TreeNode;


/**
 * @author chenzhuo(zhiyue)
 */
//todo: 此题要注意height和level的关系，height==1和height==0的单独拿出来处理，细心，不要数错，然后就是各种细节
public class Quiz222 {

    public static void main(String[] args) {
        Quiz222 quiz222 = new Quiz222();

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;


        int res = quiz222.countNodes(node1);

        System.out.println(res);
    }

    private int height = 0;
    private int lastLevelNodeCount = 0;
    private boolean endOfTheTree = false;

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        countNodesInternal(root, 0);
        if (height == 1) {
            return 1;
        }

        int nodeCountSum = 0;
        for (int i = 0; i <= height - 2; i++) {
            nodeCountSum = nodeCountSum + (int) Math.pow(2, i);
        }

        nodeCountSum = nodeCountSum + lastLevelNodeCount;

        return nodeCountSum;
    }

    private void countNodesInternal(TreeNode node, int level) {
        if (height == 0 && node == null) {
            height = level;
            //todo 要记得此处最下一层节点数加一
            lastLevelNodeCount++;
            return;
        }

        if (endOfTheTree) {
            return;
        }

        if (node == null && level < height) {
            endOfTheTree = true;
            return;
        }

        //todo:到这一步为空的话，说明是最后最左下节点的右节点，这个if单独对付他
        if (node == null) {
            return;
        }

        if (height != 0 && level == height - 1) {
            lastLevelNodeCount++;
            return;
        }

        countNodesInternal(node.left, level + 1);
        countNodesInternal(node.right, level + 1);
    }

}
