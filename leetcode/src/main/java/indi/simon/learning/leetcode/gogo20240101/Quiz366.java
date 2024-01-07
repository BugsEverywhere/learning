package indi.simon.learning.leetcode.gogo20240101;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Zhuo on 2024/1/7.
 */
public class Quiz366 {

    public static void main(String[] args) {

    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        while (root.right != null || root.left != null) {
            List<Integer> list = new ArrayList<>();
            collect(list, root);
            res.add(list);
        }
        List<Integer> rootList = new ArrayList<>();
        rootList.add(root.val);
        res.add(rootList);
        return res;
    }

    private boolean collect(List<Integer> list, TreeNode node) {
        if (node.right == null && node.left == null) {
            list.add(node.val);
            return true;
        }

        if (node.left != null) {
            boolean leftRes = collect(list, node.left);
            if (leftRes) {
                node.left = null;
            }
        }

        if (node.right != null) {
            boolean rightRes = collect(list, node.right);
            if (rightRes) {
                node.right = null;
            }
        }

        return false;
    }

}
