package indi.simon.learning.leetcode.bytedance.listtree;

import indi.simon.learning.leetcode.commonmodel.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class DetectCycle {


    public static void main(String[] args) {

    }

    public static ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        Map<ListNode, Boolean> circleFootPrintMap = new HashMap<>();
        ListNode currentNode = head;
        while (currentNode != null) {
            if (circleFootPrintMap.get(currentNode) != null && circleFootPrintMap.get(currentNode)) {
                return currentNode;
            }
            circleFootPrintMap.put(currentNode, true);
            currentNode = currentNode.next;
        }
        return null;
    }


}
