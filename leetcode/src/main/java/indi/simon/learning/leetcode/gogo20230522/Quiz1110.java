package indi.simon.learning.leetcode.gogo20230522;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1110 {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);

        node1.left = node2;
        node2.left = node4;
        node2.right = node3;

        Quiz1110 quiz1110 = new Quiz1110();
        List<TreeNode> list = quiz1110.delNodes(node1, new int[]{2, 3});
        System.out.println(list);
    }

    List<TreeNode> list;

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Set<Integer> deleteSet = new HashSet<>();
        for (int delete : to_delete) {
            deleteSet.add(delete);
        }
        if (!deleteSet.contains(root.val)) {
            list.add(root);
            delNodesRecursive(root, 1, root.left, deleteSet);
            delNodesRecursive(root, 2, root.right, deleteSet);
        } else {
            delNodesRecursive(null, 1, root.left, deleteSet);
            delNodesRecursive(null, 2, root.right, deleteSet);
        }

        return list;
    }

    private void delNodesRecursive(TreeNode father, int leftOrRight, TreeNode node, Set<Integer> delete) {
        if (node == null) {
            return;
        }
        if (delete.contains(node.val)) {
            if (father != null) {
                if (leftOrRight == 1) {
                    father.left = null;
                } else {
                    father.right = null;
                }
            }
            delNodesRecursive(null, 1, node.left, delete);
            delNodesRecursive(null, 2, node.right, delete);
        } else {
            if (father == null) {
                list.add(node);
            }
            delNodesRecursive(node, 1, node.left, delete);
            delNodesRecursive(node, 2, node.right, delete);
        }
    }


}
