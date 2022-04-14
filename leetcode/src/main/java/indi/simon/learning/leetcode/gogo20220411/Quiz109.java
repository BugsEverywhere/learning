package indi.simon.learning.leetcode.gogo20220411;

import indi.simon.learning.leetcode.commonmodel.ListNode;
import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz109 {

    public static void main(String[] args) {
        Quiz109 quiz109 = new Quiz109();
        ListNode node1 = new ListNode(-10);
        ListNode node2 = new ListNode(-3);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(9);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        TreeNode res = quiz109.sortedListToBST(node1);
        System.out.println(res);
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        List<ListNode> arr = new ArrayList<>();
        while (head != null) {
            arr.add(head);
            head = head.next;
        }
        return sortedListToBSTInternal(arr);
    }

    private TreeNode sortedListToBSTInternal(List<ListNode> listNodeArr) {
        if (listNodeArr.size() == 0) {
            return null;
        }

        ListNode midNode = listNodeArr.get((listNodeArr.size() - 1) / 2);
        TreeNode treeNode = new TreeNode(midNode.val);

        //左子树
        if ((listNodeArr.size() - 1) / 2 == 0) {
            treeNode.left = null;
        } else {
            List<ListNode> leftArr = listNodeArr.subList(0, (listNodeArr.size() - 1) / 2);
            treeNode.left = sortedListToBSTInternal(leftArr);
        }

        //右子树
        if (((listNodeArr.size() - 1) / 2) + 1 < listNodeArr.size()) {
            List<ListNode> rightArr = listNodeArr.subList(((listNodeArr.size() - 1) / 2) + 1, listNodeArr.size());
            treeNode.right = sortedListToBSTInternal(rightArr);
        } else {
            treeNode.right = null;
        }

        return treeNode;
    }


}
