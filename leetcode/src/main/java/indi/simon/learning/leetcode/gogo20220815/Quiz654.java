package indi.simon.learning.leetcode.gogo20220815;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz654 {

    public static void main(String[] args) {
        Quiz654 quiz654 = new Quiz654();
        TreeNode res = quiz654.constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5});
        System.out.println(res);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int maxIndex = findMaxArrIndex(nums, 0, nums.length - 1);
        TreeNode root = new TreeNode(nums[maxIndex]);
        buildChild(root, maxIndex, nums, 0, nums.length - 1);
        return root;
    }

    private void buildChild(TreeNode node, int index, int[] nums, int left, int right) {

        if (index < 0 || index >= nums.length) {
            return;
        }

        if (left < index) {
            int leftMaxIndex = findMaxArrIndex(nums, left, index - 1);
            node.left = new TreeNode(nums[leftMaxIndex]);
            buildChild(node.left, leftMaxIndex, nums, left, index - 1);
        }

        if (right > index) {
            int rightMaxIndex = findMaxArrIndex(nums, index + 1, right);
            node.right = new TreeNode(nums[rightMaxIndex]);
            buildChild(node.right, rightMaxIndex, nums, index + 1, right);
        }

    }


    private int findMaxArrIndex(int[] nums, int leftIndex, int rightIndex) {
        int max = Integer.MIN_VALUE;
        int res = -1;
        for (int i = leftIndex; i <= rightIndex; i++) {
            if (nums[i] > max) {
                res = i;
                max = nums[i];
            }
        }
        return res;
    }

}
