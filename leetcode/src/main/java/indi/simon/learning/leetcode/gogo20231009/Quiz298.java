package indi.simon.learning.leetcode.gogo20231009;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

/**
 * Created by Chen Zhuo on 2023/10/15.
 */
public class Quiz298 {

    public static void main(String[] args) {

    }

    private int res;

    public int longestConsecutive(TreeNode root) {
        res = 1;
        dfs(root, Integer.MAX_VALUE, 0);
        return res;
    }

    private void dfs(TreeNode node, int last, int length) {
        if (node == null) {
            return;
        }
        if (node.val == last + 1) {
            length++;
            this.res = Math.max(res, length);
        } else {
            length = 1;
        }

        dfs(node.left, node.val, length);
        dfs(node.right, node.val, length);
    }

}
