package indi.simon.learning.leetcode.gogo20240101;

import indi.simon.learning.leetcode.commonmodel.ListNode;

/**
 * Created by Chen Zhuo on 2024/1/7.
 */
public class Quiz2807 {

    public static void main(String[] args) {
        Quiz2807 quiz2807 = new Quiz2807();


    }

    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode curr = head;

        while (curr != null && curr.next != null) {
            ListNode node = new ListNode(getMaxPrime(curr.val, curr.next.val));
            node.next = curr.next;
            curr.next = node;
            curr = node.next;
        }

        return head;
    }

    private int getMaxPrime(int a, int b) {
        int i = Math.min(a, b);
        for (; i >= 1; i--) {
            if (a % i == 0 && b % i == 0) {
                return i;
            }
        }
        return i;
    }


}
