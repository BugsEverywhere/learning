package indi.simon.leetcode.bytedance.listtree;

import indi.simon.leetcode.commonmodel.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class GetIntersectionNode {

    public static void main(String[] args) {

    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if (headA == null || headB == null) {
            return null;
        }
        Set<ListNode> aSet = new HashSet<>();
        Set<ListNode> bSet = new HashSet<>();

        ListNode aCurrentNode = headA;
        ListNode bCurrentNode = headB;

        while (aCurrentNode != null || bCurrentNode != null) {
            if (aCurrentNode != null) {
                aSet.add(aCurrentNode);
            }
            if (bCurrentNode != null) {
                bSet.add(bCurrentNode);
            }

            if (aCurrentNode != null && bSet.contains(aCurrentNode)) {
                return aCurrentNode;
            }
            if (bCurrentNode != null && aSet.contains(bCurrentNode)) {
                return bCurrentNode;
            }

            aCurrentNode = aCurrentNode == null ? null : aCurrentNode.next;
            bCurrentNode = bCurrentNode == null ? null : bCurrentNode.next;
        }

        return null;
    }

}
