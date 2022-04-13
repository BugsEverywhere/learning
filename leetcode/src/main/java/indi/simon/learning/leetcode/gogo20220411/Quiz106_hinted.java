package indi.simon.learning.leetcode.gogo20220411;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz106_hinted {

    public static void main(String[] args) {
        Quiz106_hinted quiz106 = new Quiz106_hinted();
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        int[] postorder = new int[]{9, 15, 7, 20, 3};
        TreeNode res = quiz106.buildTree(inorder, postorder);
        System.out.println(res);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTreeInternal(inorder, postorder);
    }

    private TreeNode buildTreeInternal(int[] inorder, int[] postorder) {
        if (postorder.length == 0 || inorder.length == 0) {
            return null;
        }

        //根节点先撵出来
        TreeNode node = new TreeNode(postorder[postorder.length - 1]);
        //找到根节点在中序遍历中的位置
        int i = 0;
        for (; i < inorder.length; i++) {
            if (inorder[i] == postorder[postorder.length - 1]) {
                break;
            }
        }
        //位置i左侧的元素为左子树，右侧的位右子树
        //找到左子树节点在后序遍历中的位置，填充为新的后序遍历数据
        Set<Integer> leftSet = new HashSet<>();
        for (int index = 0; index < i; index++) {
            leftSet.add(inorder[index]);
        }
        int[] newLeftPostorderArr = new int[i];
        int newLeftPostorderArrIndex = 0;
        for (int k : postorder) {
            if (leftSet.contains(k)) {
                newLeftPostorderArr[newLeftPostorderArrIndex] = k;
                newLeftPostorderArrIndex++;
            }
        }

        //找到右子树节点在后序遍历中的位置，填充为新的后序遍历数据
        Set<Integer> rightSet = new HashSet<>();
        for (int index = i + 1; index < inorder.length; index++) {
            rightSet.add(inorder[index]);
        }
        int[] newRightPostorderArr = new int[inorder.length - 1 - i];
        int newRightPostorderArrIndex = 0;
        for (int k : postorder) {
            if (rightSet.contains(k)) {
                newRightPostorderArr[newRightPostorderArrIndex] = k;
                newRightPostorderArrIndex++;
            }
        }

        int[] newInorderLeftArr = new int[i];
        int[] newInorderRightArr = new int[inorder.length - 1 - i];

        System.arraycopy(inorder, 0, newInorderLeftArr, 0, i);
        if (i + 1 < inorder.length) {
            System.arraycopy(inorder, i + 1, newInorderRightArr, 0, inorder.length - 1 - i);
        }

        node.left = buildTreeInternal(newInorderLeftArr, newLeftPostorderArr);
        node.right = buildTreeInternal(newInorderRightArr, newRightPostorderArr);

        return node;
    }
}
