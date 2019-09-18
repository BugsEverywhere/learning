package main.java.indi.simon.leetcode;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class ListNodeDelete19 {

    public static void main(String[] args) {

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ArrayList<ListNode> arrList = new ArrayList<>();
        ListNode currentNode = head;
        arrList.add(currentNode);
        while (currentNode.next != null) {
            arrList.add(currentNode.next);
            currentNode = currentNode.next;
        }
        if (n < arrList.size() && n > 1) {
            ListNode theOneBefore = arrList.get(arrList.size() - n - 1);
            theOneBefore.next = arrList.get(arrList.size() - n + 1);
            return head;
        } else if (n == 1 && arrList.size() >= 2) {
            arrList.get(arrList.size() - 2).next = null;
            return head;
        } else if (arrList.size() == 1) {
            if (n == 1) {
                return null;
            } else if (n == 0) {
                return head;
            }
        }
        return head.next;
    }

}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

//todo: 注意到了n有可能是链表长度-1的情况很不错，但是忽视了n有可能是1的情况，这种时候是没有arrList.size() - n + 1节点的