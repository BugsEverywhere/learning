package indi.simon.learning.leetcode.gogo2020may.week4to10;

import indi.simon.learning.leetcode.commonmodel.ListNode;

import java.util.HashSet;
import java.util.Set;

public class P141 {

    public static void main(String[] args) {

    }

    public static Set<ListNode> posSet = new HashSet<>();

    private static boolean hasCycle(ListNode head) {
        while (head != null) {
            if (posSet.contains(head)) {
                return true;
            }
            posSet.add(head);
            head = head.next;
        }
        return false;
    }


}
