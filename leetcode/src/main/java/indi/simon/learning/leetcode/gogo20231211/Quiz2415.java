package indi.simon.learning.leetcode.gogo20231211;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.*;

/**
 * Created by Chen Zhuo on 2023/12/16.
 */
public class Quiz2415 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        TreeNode node1 = new TreeNode(13);
        TreeNode node2 = new TreeNode(11);
        root.left = node1;
        root.right = node2;
        Quiz2415 quiz2415 = new Quiz2415();
        root = quiz2415.reverseOddLevels(root);
        System.out.println(root);
    }

    private Map<Integer, ArrayDeque<Integer>> map;

    public TreeNode reverseOddLevels(TreeNode root) {
        map = new HashMap<>();
        memory(root, 0);
        revert(root, 0);
        return root;
    }

    private void memory(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (level % 2 != 0) {
            ArrayDeque<Integer> list = map.getOrDefault(level, new ArrayDeque<>());
            list.offer(node.val);
            map.put(level, list);
        }
        memory(node.left, level + 1);
        memory(node.right, level + 1);
    }

    private void revert(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (level % 2 != 0) {
            ArrayDeque<Integer> list = map.get(level);
            node.val = list.pollLast();
        }
        revert(node.left, level + 1);
        revert(node.right, level + 1);
    }


}
