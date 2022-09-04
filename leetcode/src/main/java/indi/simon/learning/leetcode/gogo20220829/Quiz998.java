package indi.simon.learning.leetcode.gogo20220829;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz998 {

    public static void main(String[] args) {
        Quiz998 quiz998 = new Quiz998();


    }

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        List<Integer> serializedRes = serialize(root);
        serializedRes.add(val);
        return deserialize(serializedRes);
    }

    private List<Integer> serialize(TreeNode node) {
        if (node == null) {
            return null;
        }
        List<Integer> thisNodeList = new ArrayList<>();

        List<Integer> leftRes = serialize(node.left);
        if (leftRes != null) {
            thisNodeList.addAll(leftRes);
        }
        thisNodeList.add(node.val);

        List<Integer> rightRes = serialize(node.right);
        if (rightRes != null) {
            thisNodeList.addAll(rightRes);
        }

        return thisNodeList;
    }

    private TreeNode deserialize(List<Integer> nums) {
        int maxIndex = findMaxArrIndex(nums, 0, nums.size() - 1);
        TreeNode root = new TreeNode(nums.get(maxIndex));
        buildChild(root, maxIndex, nums, 0, nums.size() - 1);
        return root;
    }

    private void buildChild(TreeNode node, int index, List<Integer> nums, int left, int right) {

        if (index < 0 || index >= nums.size()) {
            return;
        }

        if (left < index) {
            int leftMaxIndex = findMaxArrIndex(nums, left, index - 1);
            node.left = new TreeNode(nums.get(leftMaxIndex));
            buildChild(node.left, leftMaxIndex, nums, left, index - 1);
        }

        if (right > index) {
            int rightMaxIndex = findMaxArrIndex(nums, index + 1, right);
            node.right = new TreeNode(nums.get(rightMaxIndex));
            buildChild(node.right, rightMaxIndex, nums, index + 1, right);
        }

    }


    private int findMaxArrIndex(List<Integer> nums, int leftIndex, int rightIndex) {
        int max = Integer.MIN_VALUE;
        int res = -1;
        for (int i = leftIndex; i <= rightIndex; i++) {
            if (nums.get(i) > max) {
                res = i;
                max = nums.get(i);
            }
        }
        return res;
    }


}
