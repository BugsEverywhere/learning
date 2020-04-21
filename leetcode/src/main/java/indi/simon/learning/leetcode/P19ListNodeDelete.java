package indi.simon.learning.leetcode;

import indi.simon.learning.leetcode.commonmodel.ListNode;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P19ListNodeDelete {

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

//todo: 此处我的做法用了另一个ArrayList来存链表，然后去对数组做随机访问，这样空间复杂度会高。
// 标准的解法是使用双指针！！又是双指针！！两个指针相差为n，这样当后面那个指针遍历到结尾的时候，
// 前面的指针指向的就是需要删除的节点了！
// 注意到了n有可能是链表长度-1的情况很不错，但是忽视了n有可能是1的情况，这种时候是没有arrList.size() - n + 1节点的