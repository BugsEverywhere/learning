package indi.simon.learning.leetcode.gogo20221010;

import indi.simon.learning.leetcode.commonmodel.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz817 {

    public static void main(String[] args) {
        Quiz817 quiz817 = new Quiz817();
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(1);


        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        int res = quiz817.numComponents(node1, new int[]{4});
        System.out.println(res);
    }

    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int res = 0;

        while (head != null) {
            if (!set.contains(head.val)) {
                head = head.next;
                continue;
            }
            while (head != null) {
                if (set.contains(head.val)) {
                    set.remove(head.val);
                    head = head.next;
                } else {
                    break;
                }
            }
            res++;
            if (head != null) {
                head = head.next;
            }
        }

        return res;
    }

}
