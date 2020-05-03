package indi.simon.learning.leetcode.april2020.week27to;

import indi.simon.learning.leetcode.commonmodel.TreeNode;
import java.util.Arrays;

public class P297_not_finished {

    public static void main(String[] args) {

        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode sixteen = new TreeNode(16);

        one.left = two;
        one.right = three;
        three.left = four;
        three.right = five;
        five.right = sixteen;

        //System.out.println(serialize(one));

        TreeNode root = deserialize(serialize(one));
        System.out.println(root);
    }

    private static String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        int levelCount = levelCount(root);
        int arrSize = (int) Math.pow(2, levelCount);
        Integer[] arr = new Integer[arrSize];
        arr[0] = root.val;
        midTraverse(root, arr, 0);
        return Arrays.toString(arr);
    }

    private static int levelCount(TreeNode fatherNode) {
        if (fatherNode == null) {
            return 0;
        }
        return Math.max(levelCount(fatherNode.left) + 1, levelCount(fatherNode.right) + 1);
    }

    private static void midTraverse(TreeNode father, Integer[] arr, int fatherIndex) {
        if (father.left != null) {
            arr[2 * fatherIndex + 1] = father.left.val;
        }
        if (father.right != null) {
            arr[2 * fatherIndex + 2] = father.right.val;
        }
        if (father.left != null) {
            midTraverse(father.left, arr, 2 * fatherIndex + 1);
        }
        if (father.right != null) {
            midTraverse(father.right, arr, 2 * fatherIndex + 2);
        }
    }


    private static TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        data = data.substring(1, data.length() - 1);
        String[] seperatedStrArr = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(seperatedStrArr[0].trim()));
        buildTree(root, seperatedStrArr, 0);
        return root;
    }

    private static void buildTree(TreeNode father, String[] arr, int index) {
        if (father == null || arr.length == 0) {
            return;
        }
        if(index * 2 + 2 < arr.length){
            String leftContent = arr[index * 2 + 1].trim();
            father.left = "null".equals(leftContent) ? null : new TreeNode(Integer.parseInt(leftContent));
            buildTree(father.left, arr, index * 2 + 1);

            String rightContent = arr[index * 2 + 2].trim();
            father.right = "null".equals(rightContent) ? null : new TreeNode(Integer.parseInt(rightContent));
            buildTree(father.right, arr, index * 2 + 2);
        }
    }

}
