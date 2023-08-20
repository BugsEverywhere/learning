package indi.simon.learning.leetcode.gogo20230814;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

/**
 * Created by Chen Zhuo on 2023/8/20.
 */
public class Quiz617 {

    public static void main(String[] args) {

    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null){
            return root2;
        }

        if(root2 == null){
            return root1;
        }

        TreeNode node = new TreeNode(root1.val + root2.val);

        node.left = mergeTrees(root1.left,root2.left);
        node.right = mergeTrees(root1.right,root2.right);

        return node;
    }







}
