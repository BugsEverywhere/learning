package indi.simon.learning.leetcode.gogo20230911;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Chen Zhuo on 2023/9/17.
 */
public class Quiz250 {

    public static void main(String[] args) {

    }

    private int cnt;

    public int countUnivalSubtrees(TreeNode root) {
        cnt = 0;
        countUnivalSubtreesInternal(root);
        return cnt;
    }

    /**
     * 返回该node下所有的节点值
     *
     * @param node
     * @return
     */
    //todo: 可以仅返回boolean，优化项
    private Set<Integer> countUnivalSubtreesInternal(TreeNode node) {
        if (node == null) {
            return new HashSet<>();
        }

        Set<Integer> res = new HashSet<>();
        res.add(node.val);

        Set<Integer> left = countUnivalSubtreesInternal(node.left);
        Set<Integer> right = countUnivalSubtreesInternal(node.right);

        Set<Integer> childSet = new HashSet<>(left);
        childSet.addAll(right);
        if (childSet.size() == 0) {
            cnt++;
        } else if (childSet.size() == 1 && childSet.iterator().next() == node.val) {
            cnt++;
        }


        res.addAll(left);
        res.addAll(right);

        return res;
    }

}
