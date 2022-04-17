package indi.simon.learning.leetcode.gogo20220411;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz114 {

    public static void main(String[] args) {

    }

    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        flattenInternal(root, list);
        TreeNode lastNode = null;
        for (TreeNode node : list) {
            if (lastNode == null) {
                lastNode = node;
                continue;
            }
            lastNode.left = null;
            lastNode.right = node;
            lastNode = node;
        }
    }

    private void flattenInternal(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }

        list.add(node);

        flattenInternal(node.left, list);
        flattenInternal(node.right, list);

    }


}
