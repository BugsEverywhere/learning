package indi.simon.learning.leetcode.gogo20230410;

import indi.simon.learning.leetcode.commonmodel.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1019 {

    public static void main(String[] args) {

    }

    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            while (next != null) {
                if (next.val > curr.val) {
                    list.add(next.val);
                    break;
                } else {
                    next = next.next;
                }
            }
            //遍历完了也没有比他大的
            if (next == null) {
                list.add(0);
            }
            curr = curr.next;
        }


        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }


}
