package indi.simon.learning.leetcode.gogo20230116;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz230 {

    public static void main(String[] args) {

    }

    private List<Integer> list;

    public int kthSmallest(TreeNode root, int k) {
        list = new ArrayList<>(k);
        midOrder(root, k);
        return list.get(k - 1);
    }

    private void midOrder(TreeNode node, int k) {
        if (node == null) {
            return;
        }

        if (list.size() >= k) {
            return;
        }

        midOrder(node.left, k);
        list.add(node.val);
        midOrder(node.right, k);
    }

}
