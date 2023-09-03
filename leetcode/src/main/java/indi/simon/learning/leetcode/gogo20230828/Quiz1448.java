package indi.simon.learning.leetcode.gogo20230828;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

/**
 * Created by Chen Zhuo on 2023/9/3.
 */
public class Quiz1448 {

    public static void main(String[] args) {

    }

    private int goodNodeCnt;

    public int goodNodes(TreeNode root) {
        goodNodeCnt = 0;
        goodNodesInternal(root, root.val);
        return goodNodeCnt;
    }


    private void goodNodesInternal(TreeNode node, int maxSoFar) {
        if (node == null) {
            return;
        }
        if (maxSoFar <= node.val) {
            goodNodeCnt++;
            goodNodesInternal(node.left, node.val);
            goodNodesInternal(node.right, node.val);
        } else {
            goodNodesInternal(node.left, maxSoFar);
            goodNodesInternal(node.right, maxSoFar);
        }
    }

}
