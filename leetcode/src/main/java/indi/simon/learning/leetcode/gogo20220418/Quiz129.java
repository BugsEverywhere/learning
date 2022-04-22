package indi.simon.learning.leetcode.gogo20220418;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz129 {

    public static void main(String[] args) {
        Quiz129 quiz129 = new Quiz129();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        int res = quiz129.sumNumbers(node1);
        System.out.println(res);
    }

    private List<Integer> numList;

    public int sumNumbers(TreeNode root) {
        numList = new ArrayList<>();
        sumNumbersInternal(root, new ArrayList<>());
        int sum = 0;
        for (int singleNum : numList) {
            sum += singleNum;
        }
        return sum;
    }

    private void sumNumbersInternal(TreeNode node, List<String> bitStrList) {
        bitStrList.add(Integer.toString(node.val));

        if (node.left == null && node.right == null) {
            //叶子节点了，清算
            StringBuilder stringBuilder = new StringBuilder();
            for (String singleBit : bitStrList) {
                stringBuilder.append(singleBit);
            }
            String numStr = stringBuilder.toString();
            numList.add(Integer.parseInt(numStr));
        }

        if (node.left != null) {
            sumNumbersInternal(node.left, bitStrList);
        }

        if (node.right != null) {
            sumNumbersInternal(node.right, bitStrList);
        }

        //擦掉脚印
        bitStrList.remove(bitStrList.size() - 1);
    }
}
