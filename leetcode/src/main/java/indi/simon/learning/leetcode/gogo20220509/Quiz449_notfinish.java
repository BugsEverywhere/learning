package indi.simon.learning.leetcode.gogo20220509;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz449_notfinish {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node2.right = node3;
        Quiz449_notfinish quiz449Notfinish = new Quiz449_notfinish();
        String str = quiz449Notfinish.serialize(node1);
        System.out.println(str);
        TreeNode node = quiz449Notfinish.deserialize(str);
        System.out.println(node);
    }

    private Map<Integer, Integer> mem = new HashMap<>();
    private int maxIndex = Integer.MIN_VALUE;

    public String serialize(TreeNode root) {
        if (root == null) {
            return "null";
        }
        serializeInternal(root, 0);
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j <= maxIndex; j++) {
            Integer singleNum = mem.get(j);
            if (singleNum != null) {
                builder.append(j).append("_").append(singleNum.toString()).append(",");
            }
        }
        return builder.toString();
    }

    private void serializeInternal(TreeNode node, int index) {
        mem.put(index, node.val);
        if (node.left != null) {
            serializeInternal(node.left, 2 * index + 1);
        }
        if (node.right != null) {
            serializeInternal(node.right, 2 * index + 2);
        }
        maxIndex = Math.max(maxIndex, index);
    }

    public TreeNode deserialize(String data) {
        Map<Integer, Integer> mem = new HashMap<>();
        if ("null".equals(data)) {
            return null;
        }
        String[] strArr = data.split(",");
        for (String singleNumStr : strArr) {
            if ("".equals(singleNumStr)) {
                continue;
            }
            String[] indexNumArr = singleNumStr.split("_");
            mem.put(Integer.parseInt(indexNumArr[0]), Integer.parseInt(indexNumArr[1]));
        }

        return recover(mem, 0);
    }

    private TreeNode recover(Map<Integer, Integer> mem, int index) {
        if (mem.get(index) == null) {
            return null;
        }
        TreeNode node = new TreeNode(mem.get(index));
        node.left = recover(mem, 2 * index + 1);
        node.right = recover(mem, 2 * index + 2);

        return node;
    }


}
