package indi.simon.learning.leetcode.gogo20230807;

import indi.simon.learning.leetcode.commonmodel.ListNode;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz237 {

    public static void main(String[] args) {

    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        if (node.next.next == null) {
            node.next = null;
        } else {
            deleteNode(node.next);
        }
    }

}
