package indi.simon.learning.leetcode;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P94InorderTraversal {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(2);

        ReentrantReadWriteLock  reentrantReadWriteLock = new ReentrantReadWriteLock();

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
