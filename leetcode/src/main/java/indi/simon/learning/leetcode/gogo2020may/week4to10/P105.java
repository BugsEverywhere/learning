package indi.simon.learning.leetcode.gogo2020may.week4to10;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

public class P105 {

    public static void main(String[] args) {

    }


    private static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }

        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }

        TreeNode root = new TreeNode(preorder[0]);
        int rootIndex = find(inorder, root.val);
        if (rootIndex != 0) {
            //说明有左子树
            int[] subInorderArr = new int[rootIndex];
            System.arraycopy(inorder, 0, subInorderArr, 0, rootIndex);
            int[] subPreorderArr = new int[rootIndex];
            System.arraycopy(preorder, 1, subPreorderArr, 0, rootIndex);
            root.left = buildTree(subPreorderArr, subInorderArr);
        }

        if (rootIndex != inorder.length - 1) {
            //说明有右子树
            int[] subInorderArr = new int[inorder.length - rootIndex - 1];
            System.arraycopy(inorder, rootIndex + 1, subInorderArr, 0, inorder.length - rootIndex - 1);
            int[] subPreorderArr = new int[inorder.length - rootIndex - 1];
            System.arraycopy(preorder, rootIndex + 1, subPreorderArr, 0, inorder.length - rootIndex - 1);
            root.right = buildTree(subPreorderArr, subInorderArr);
        }

        return root;
    }

    private static int find(int[] inOrderArr, int target) {
        for (int i = 0; i < inOrderArr.length; i++) {
            if (inOrderArr[i] == target) {
                return i;
            }
        }
        return -1;
    }


}
