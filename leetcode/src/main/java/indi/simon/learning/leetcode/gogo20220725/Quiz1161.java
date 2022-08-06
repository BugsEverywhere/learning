package indi.simon.learning.leetcode.gogo20220725;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1161 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(989);
        TreeNode node10250 = new TreeNode(10250);
        TreeNode node98693 = new TreeNode(98693);
        TreeNode node89388 = new TreeNode(-89388);
        TreeNode node32127 = new TreeNode(-32127);

        root.right = node10250;
        node10250.left = node98693;
        node10250.right = node89388;
        node89388.right = node32127;

        Quiz1161 quiz1161 = new Quiz1161();
        int res = quiz1161.maxLevelSum(root);
        System.out.println(res);
    }

    public int maxLevelSum(TreeNode root) {
        Map<Integer, Integer> map = new TreeMap<>();
        maxLevelSumInternal(root, map, 1);

        int maxLevelSum = Integer.MIN_VALUE;
        int minLevel = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> s : map.entrySet()) {
            if (s.getValue() > maxLevelSum) {
                maxLevelSum = s.getValue();
                minLevel = s.getKey();
            } else if (s.getValue() == maxLevelSum && s.getKey() < minLevel) {
                minLevel = s.getValue();
            }
        }

        return minLevel;
    }

    private void maxLevelSumInternal(TreeNode node, Map<Integer, Integer> levelSumMap, int level) {
        if (node == null) {
            return;
        }

        int thisLevelSum;
        if (levelSumMap.containsKey(level)) {
            thisLevelSum = levelSumMap.get(level) + node.val;
        } else {
            thisLevelSum = node.val;
        }

        levelSumMap.put(level, thisLevelSum);

        maxLevelSumInternal(node.left, levelSumMap, level + 1);
        maxLevelSumInternal(node.right, levelSumMap, level + 1);

    }

}
