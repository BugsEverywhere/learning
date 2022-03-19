package indi.simon.learning.leetcode.gogo20220314;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz606 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode leftLeft = new TreeNode(3);
        TreeNode leftRight = new TreeNode(4);

        root.left = left;
        left.left = leftLeft;
        left.right = leftRight;

        Quiz606 quiz606 = new Quiz606();

        String res = quiz606.tree2str(root);
        System.out.println(res);
    }

    public String tree2str(TreeNode root) {
        if (root == null) {
            return "()";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        String leftStr = tree2strInternal(root.left);
        String rightStr = tree2strInternal(root.right);
        if (leftStr == null && rightStr == null) {
            return sb.toString();
        } else if (leftStr == null && rightStr != null) {
            sb.append("()").append(rightStr);
            return sb.toString();
        } else if (leftStr != null && rightStr == null) {
            return sb.append(leftStr).toString();
        } else {
            return sb.append(leftStr).append(rightStr).toString();
        }
    }

    private String tree2strInternal(TreeNode node) {
        if (node == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(node.val);
        String leftStr = tree2strInternal(node.left);
        String rightStr = tree2strInternal(node.right);
        if (leftStr == null && rightStr == null) {
            sb.append(")");
            return sb.toString();
        } else if (leftStr == null && rightStr != null) {
            sb.append("()").append(rightStr).append(")");
            return sb.toString();
        } else if (leftStr != null && rightStr == null) {
            return sb.append(leftStr).append(")").toString();
        } else {
            return sb.append(leftStr).append(rightStr).append(")").toString();
        }
    }

    //todo: 在递归的时候，如果最外层逻辑和底下的逻辑有不一致的地方，比如此题，最外层不用加括号，那么最外层的逻辑写在原始方法里，其余层的逻辑写在递归方法里，这里注意
    // 最外层的逻辑也要写全，第二次提交错了就是最外层没写全的原因

}
