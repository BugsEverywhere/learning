package indi.simon.learning.leetcode.gogo20240205;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2024/2/9.
 */
public class Quiz337 {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(1);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.right = node6;

        Quiz337 quiz337 = new Quiz337();
        int res = quiz337.rob(node1);
        System.out.println(res);
    }

    private Map<TreeNode, Map<Boolean, Integer>> mem;

    public int rob(TreeNode root) {
        mem = new HashMap<>();
        if (root == null) {
            return 0;
        }

        int robRes = robInternal(root, true);
        int notRobRes = robInternal(root, false);

        return Math.max(robRes, notRobRes);
    }

    private int robInternal(TreeNode node, boolean canRob) {
        if (node == null) {
            return 0;
        }

        if (mem.containsKey(node) && mem.get(node).containsKey(canRob)) {
            return mem.get(node).get(canRob);
        }

        if (canRob) {
            //可选择打劫这家，也可以不打劫这家
            //不打劫这家
            int notRobRes = robInternal(node.left, true) + robInternal(node.right, true);
            //打劫这家
            int robRes = node.val + robInternal(node.left, false) + robInternal(node.right, false);
            int r = Math.max(notRobRes, robRes);
            Map<Boolean, Integer> m = mem.getOrDefault(node, new HashMap<>());
            m.put(canRob, r);
            mem.put(node, m);
            return r;
        } else {
            //只能不打劫这家，考虑子节点
            int leftRes = robInternal(node.left, true);
            int rightRes = robInternal(node.right, true);
            int r = leftRes + rightRes;
            Map<Boolean, Integer> m = mem.getOrDefault(node, new HashMap<>());
            m.put(canRob, r);
            mem.put(node, m);
            return r;
        }
    }

}
