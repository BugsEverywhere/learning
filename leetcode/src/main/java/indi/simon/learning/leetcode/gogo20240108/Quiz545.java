package indi.simon.learning.leetcode.gogo20240108;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Zhuo on 2024/1/13.
 */
public class Quiz545 {

    public static void main(String[] args) {
        TreeNode one = new TreeNode(1);
//        TreeNode two = new TreeNode(2);
//        TreeNode three = new TreeNode(3);
//        TreeNode four = new TreeNode(4);
//
//        one.right = two;
//        two.left = three;
//        two.right = four;

        Quiz545 quiz545NeedReview = new Quiz545();
        List<Integer> res = quiz545NeedReview.boundaryOfBinaryTree(one);

        System.out.println(res);
    }

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.add(root.val);
        //首先是左边界
        if (root.left != null) {
            find(res, root.left, 1);
        }
        //然后是叶子结点
        if (root.left != null || root.right != null) {
            find(res, root.left, 2);
            find(res, root.right, 2);
        }
        //然后是右边界
        if (root.right != null) {
            List<Integer> rightFrontier = new ArrayList<>();
            find(rightFrontier, root.right, 3);
            //右边界逆序塞入结果集
            for (int i = rightFrontier.size() - 1; i >= 0; i--) {
                res.add(rightFrontier.get(i));
            }
        }

        return res;
    }

    private void find(List<Integer> res, TreeNode node, int i) {
        if (node == null) {
            return;
        }
        if (i == 1) {
            //需要找左边界
            if (node.left == null && node.right == null) {
                //叶子结点不在左边界
                return;
            }
            res.add(node.val);
            if (node.left != null) {
                find(res, node.left, i);
            } else {
                find(res, node.right, i);
            }
        } else if (i == 2) {
            //找叶子结点
            if (node.left == null && node.right == null) {
                res.add(node.val);
            } else {
                find(res, node.left, i);
                find(res, node.right, i);
            }
        } else {
            //找右边界
            if (node.left == null && node.right == null) {
                //叶子结点不在右边界
                return;
            }
            res.add(node.val);
            if (node.right != null) {
                find(res, node.right, i);
            } else {
                find(res, node.left, i);
            }
        }
    }

}
