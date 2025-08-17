package indi.simon.learning.复习.二叉树;

import indi.simon.learning.leetcode.commonmodel.TreeNode;
import java.util.Arrays;

/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 *
 * 输入：root = [1,2]
 * 输出：[1,2]
 * 提示：
 *
 * 树中结点数在范围 [0, 104] 内
 * -1000 <= Node.val <= 1000
 */
public class Quiz297_二叉树的序列化与反序列化 {

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
