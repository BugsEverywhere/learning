package indi.simon.learning.leetcode.广度优先搜索;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.*;

/**
 * Created by Chen Zhuo on 2024/2/7.
 */
public class Quiz2641_reviewed {

    public static void main(String[] args) {

    }

    //todo: 官方的BFS，关键是要学会这种BFS层序遍历二叉树的方法，普通的BFS无法做到区分层与层，
    // 此时使用这种变通的BFS，使用两个队列，外层队列存放father层节点，while循环内对外层队列for循环，将子节点存放入内存队列
    public TreeNode replaceValueInTreeV2(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);
        root.val = 0;
        while (!queue.isEmpty()) {
            Queue<TreeNode> queue2 = new ArrayDeque<TreeNode>();
            int sum = 0;
            for (TreeNode fa : queue) {
                if (fa.left != null) {
                    queue2.offer(fa.left);
                    sum += fa.left.val;
                }
                if (fa.right != null) {
                    queue2.offer(fa.right);
                    sum += fa.right.val;
                }
            }
            for (TreeNode fa : queue) {
                int childSum = (fa.left != null ? fa.left.val : 0) +
                        (fa.right != null ? fa.right.val : 0);
                if (fa.left != null) {
                    fa.left.val = sum - childSum;
                }
                if (fa.right != null) {
                    fa.right.val = sum - childSum;
                }
            }
            queue = queue2;
        }
        return root;
    }


    //==============================================================todo: 超时解法
    public TreeNode replaceValueInTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Map<Integer, Map<TreeNode, List<Integer>>> levels = new HashMap<>();
        findCousins(root.left, root, levels, 0);
        findCousins(root.right, root, levels, 0);

        root.val = 0;

        replace(root.left, root, levels, 0);
        replace(root.right, root, levels, 0);

        return root;
    }

    /**
     * find all brothers and cousins for all the nodes
     *
     * @param node
     * @param father
     * @param levels
     */
    private void findCousins(TreeNode node, TreeNode father, Map<Integer, Map<TreeNode, List<Integer>>> levels, int depth) {
        if (node == null) {
            return;
        }
        Map<TreeNode, List<Integer>> thisLevel = levels.getOrDefault(depth, new HashMap<>());
        List<Integer> brothers = thisLevel.getOrDefault(father, new ArrayList<>());
        brothers.add(node.val);
        thisLevel.put(father, brothers);
        levels.put(depth, thisLevel);

        findCousins(node.left, node, levels, depth + 1);
        findCousins(node.right, node, levels, depth + 1);

    }

    private void replace(TreeNode node, TreeNode father, Map<Integer, Map<TreeNode, List<Integer>>> levels, int depth) {
        if (node == null) {
            return;
        }

        Map<TreeNode, List<Integer>> thisLevel = levels.get(depth);
        int cousinSum = 0;
        for (Map.Entry<TreeNode, List<Integer>> thisLevelFathers : thisLevel.entrySet()) {
            if (thisLevelFathers.getKey() == father) {
                continue;
            }
            for (Integer cousinVal : thisLevelFathers.getValue()) {
                cousinSum += cousinVal;
            }
        }
        node.val = cousinSum;

        replace(node.left, node, levels, depth + 1);
        replace(node.right, node, levels, depth + 1);

    }


}
