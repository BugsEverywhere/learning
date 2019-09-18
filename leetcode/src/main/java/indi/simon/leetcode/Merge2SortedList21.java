package main.java.indi.simon.leetcode;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class Merge2SortedList21 {

    public static void main(String[] args) {


    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 != null) {
            return l2;
        } else if (l1 != null && l2 == null) {
            return l1;
        } else if (l1 == null && l2 == null) {
            return null;
        }
        ListNode l1CurrentNode;
        ListNode l2CurrentNode;
        ListNode firstOne;
        ListNode lastOne;
        if (l1.val >= l2.val) {
            firstOne = l2;
            lastOne = l2;
            l2CurrentNode = l2.next;
            l1CurrentNode = l1;
        } else {
            firstOne = l1;
            lastOne = l1;
            l1CurrentNode = l1.next;
            l2CurrentNode = l2;
        }
        while (l1CurrentNode != null && l2CurrentNode != null) {
            if (l1CurrentNode.val >= l2CurrentNode.val) {
                lastOne.next = l2CurrentNode;
                lastOne = l2CurrentNode;
                l2CurrentNode = l2CurrentNode.next;
            } else {
                lastOne.next = l1CurrentNode;
                lastOne = l1CurrentNode;
                l1CurrentNode = l1CurrentNode.next;
            }
        }
        if (l1CurrentNode == null) {
            lastOne.next = l2CurrentNode;
        } else {
            lastOne.next = l1CurrentNode;
        }
        return firstOne;
    }

}

//todo:两个或者多个链表的合并时，开始先确定一个开始的节点，然后就是递归和循环确定后面的节点，
// 各个节点的指针在循环中从哪里开始一定要明确，只有刚刚确定的第一个节点需要拿后继节点作为本链表的开始节点。

//todo:在循环中迭代赋值的顺序要注意，不能乱，先给目标链表赋值，然后才是各个链表自己的自增。