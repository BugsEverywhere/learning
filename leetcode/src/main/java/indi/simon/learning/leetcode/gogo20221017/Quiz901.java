package indi.simon.learning.leetcode.gogo20221017;

import indi.simon.learning.leetcode.commonmodel.ListNode;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz901 {

    public static void main(String[] args) {

    }

    private ListNode tail;

    public Quiz901() {

    }

    public int next(int price) {
        if (tail == null) {
            tail = new ListNode(price);
            return 1;
        }
        ListNode currNode = new ListNode(price);
        currNode.next = tail;
        ListNode node = tail;
        int res = 1;
        while (node != null) {
            if (node.val > price) {
                break;
            }
            node = node.next;
            res++;
        }
        tail = currNode;
        return res;
    }

}
