package indi.simon.learning.复习.二叉树;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

/**
 * Created by Chen Zhuo on 2023/10/22.
 *
 * 给定一个二叉树，找到其中最大的二叉搜索树（BST）子树，并返回该子树的大小。其中，最大指的是子树节点数最多的。
 *
 * 二叉搜索树（BST）中的所有节点都具备以下属性：
 *
 * 左子树的值小于其父（根）节点的值。
 *
 * 右子树的值大于其父（根）节点的值。
 *
 * 注意：子树必须包含其所有后代。
 *
 * 示例 1：
 *
 * 输入：root = [10,5,15,1,8,null,7]
 * 输出：3
 * 解释：本例中最大的 BST 子树是高亮显示的子树。返回值是子树的大小，即 3 。
 * 示例 2：
 *
 * 输入：root = [4,2,7,2,3,5,null,2,null,null,null,null,null,1]
 * 输出：2
 *
 *
 * 提示：
 *
 * 树上节点数目的范围是 [0, 104]
 * -104 <= Node.val <= 104
 *
 *
 * 进阶:  你能想出 O(n) 时间复杂度的解法吗？
 */

public class Quiz333_最大二叉搜索子树 {

    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            return countNodes(root);
        }
        return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
    }

    /**
     * 判断是否是二叉搜索树
     *
     * @param root
     * @param l
     * @param r
     * @return
     */
    public boolean isValidBST(TreeNode root, long l, long r) {
        if (root == null) {
            return true;
        }
        if (root.val <= l || root.val >= r) {
            return false;
        }
        return isValidBST(root.left, l, root.val) && isValidBST(root.right, root.val, r);
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

}
