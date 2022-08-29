package indi.simon.learning.leetcode.gogo20220815;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1302 {

    public static void main(String[] args) {

    }

    private int maxLayer;
    private int maxLayerSum;

    public int deepestLeavesSum(TreeNode root) {
        maxLayer = 0;
        maxLayerSum = 0;
        findMaxLayer(root, 0);
        deepestLeavesSumInternal(root, 0);
        return maxLayerSum;
    }

    private void findMaxLayer(TreeNode node, int currLayer) {
        if (node == null) {
            return;
        }
        if (currLayer > maxLayer) {
            maxLayer = currLayer;
        }
        findMaxLayer(node.left, currLayer + 1);
        findMaxLayer(node.right, currLayer + 1);
    }

    private void deepestLeavesSumInternal(TreeNode node, int currLayer) {
        if (node == null) {
            return;
        }

        if (currLayer == maxLayer) {
            maxLayerSum += node.val;
        } else {
            deepestLeavesSumInternal(node.left, currLayer + 1);
            deepestLeavesSumInternal(node.right, currLayer + 1);
        }

    }
}
