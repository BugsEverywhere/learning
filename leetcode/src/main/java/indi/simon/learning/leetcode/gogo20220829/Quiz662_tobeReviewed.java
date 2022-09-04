package indi.simon.learning.leetcode.gogo20220829;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz662_tobeReviewed {

    public static void main(String[] args) {
        TreeNode one = new TreeNode(1);
        TreeNode three = new TreeNode(3);
        TreeNode two = new TreeNode(2);
        TreeNode five = new TreeNode(5);
        TreeNode nine = new TreeNode(9);

        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);

        one.left = three;
        one.right = two;

        three.left = five;
        two.right = nine;

        five.left = six;
        nine.left = seven;

        Quiz662_tobeReviewed quiz662TobeReviewed = new Quiz662_tobeReviewed();
        int res = quiz662TobeReviewed.widthOfBinaryTree(one);
        System.out.println(res);
    }


    //层序遍历，超内存的
    public int widthOfBinaryTree(TreeNode root) {
        List<List<TreeNode>> levelList = new ArrayList<>();
        widthOfBinaryTreeInternal(root, levelList, 0);
        //从最下一层开始遍历
        int maxWidth = 0;
        for (int i = levelList.size() - 1; i >= 0; i--) {
            List<TreeNode> thisLevelList = levelList.get(i);
            int j = 0;
            int k = thisLevelList.size() - 1;
            while (j <= k) {
                if (thisLevelList.get(j) != null && thisLevelList.get(k) == null) {
                    k--;
                } else if (thisLevelList.get(j) == null && thisLevelList.get(k) != null) {
                    j++;
                } else if (thisLevelList.get(j) == null && thisLevelList.get(k) == null) {
                    k--;
                    j++;
                } else {
                    maxWidth = Math.max(maxWidth, k - j + 1);
                    break;
                }
            }

        }
        return maxWidth;
    }

    private void widthOfBinaryTreeInternal(TreeNode node, List<List<TreeNode>> levelList, int level) {

        if (node != null && levelList.size() < level + 1) {
            levelList.add(new ArrayList<>());
        } else if (node == null && levelList.size() < level + 1) {
            return;
        }
        List<TreeNode> thisLevelList = levelList.get(level);
        thisLevelList.add(node);

        widthOfBinaryTreeInternal(node == null ? null : node.left, levelList, level + 1);
        widthOfBinaryTreeInternal(node == null ? null : node.right, levelList, level + 1);
    }

}
