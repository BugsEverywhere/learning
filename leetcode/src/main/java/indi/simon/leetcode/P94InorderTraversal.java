package indi.simon.leetcode;

import indi.simon.leetcode.commonmodel.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P94InorderTraversal {

    public static void main(String[] args) {

    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traversalInternal(result, root);
        return result;
    }

    private static void traversalInternal(List<Integer> nodeList, TreeNode father) {
        if (father.left != null) {
            traversalInternal(nodeList, father.left);
        }
        nodeList.add(father.val);
        if (father.right != null) {
            traversalInternal(nodeList, father.right);
        }
    }


}
