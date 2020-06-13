package indi.simon.learning.leetcode.gogo2020june;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

public class P108 {

    public static void main(String[] args) {

        int[] arr = new int[]{-10, -3, 0, 5, 9};
        TreeNode node = sortedArrayToBST(arr);

        System.out.println(node);

    }

    private static TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        return sortedArrayToBSTInternal(nums, 0, nums.length - 1);
    }

    private static TreeNode sortedArrayToBSTInternal(int[] nums, int min, int max) {
        //todo 这种类二分查找的递归的终止条件，永远都是max<min
        if (max < min) {
            return null;
        }
        if (min == max) {
            return new TreeNode(nums[min]);
        }

        int mid = (max + min) / 2;

        TreeNode midNode = new TreeNode(nums[mid]);

        midNode.left = sortedArrayToBSTInternal(nums, min, mid - 1);
        midNode.right = sortedArrayToBSTInternal(nums,  mid + 1, max);

        return midNode;

    }

}
