package indi.simon.leetcode.bytedance.listtree;

import indi.simon.leetcode.commonmodel.ListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
