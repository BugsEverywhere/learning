package indi.simon.learning.leetcode.bytedance.listtree;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class ZigzagLevelOrder {

    public static void main(String[] args) {

    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        zigzagLevelOrderInternal(Arrays.asList(root), false, result);
        return result;
    }

    private static void zigzagLevelOrderInternal(List<TreeNode> nodeOnThisLevel, boolean revert, List<List<Integer>> result) {
        List<Integer> resultThisLevel = new ArrayList<>();
        int nullCount = 0;
        if (revert) {
            for (int i = nodeOnThisLevel.size() - 1; i >= 0; i--) {
                if (nodeOnThisLevel.get(i) == null) {
                    nullCount++;
                    continue;
                }
                resultThisLevel.add(nodeOnThisLevel.get(i).val);
            }
        } else {
            for (int i = 0; i <= nodeOnThisLevel.size() - 1; i++) {
                if (nodeOnThisLevel.get(i) == null) {
                    nullCount++;
                    continue;
                }
                resultThisLevel.add(nodeOnThisLevel.get(i).val);
            }
        }
        //如果本层节点全为空，直接返回
        if (nullCount == nodeOnThisLevel.size()) {
            return;
        }
        result.add(resultThisLevel);

        List<TreeNode> nextLevelNodes = new ArrayList<>();
        for (TreeNode node : nodeOnThisLevel) {
            if (node == null) {
                continue;
            }
            nextLevelNodes.add(node.left);
            nextLevelNodes.add(node.right);
        }
        zigzagLevelOrderInternal(nextLevelNodes, !revert, result);
    }


}
