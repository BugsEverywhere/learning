package indi.simon.learning.复习.快慢指针;

import indi.simon.learning.leetcode.commonmodel.ListNode;


/**
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 * 不允许修改 链表。
 *
 *
 *
 */
public class Quiz142 {

    public static void main(String[] args) {

    }

    //todo: 快慢指针，快指针2倍速于慢指针，假设在环中某处相遇，a为环外长度，b为入环处到相遇处的距离，c为相遇处顺时针回到入环处的距离，有：
    // a+b+n(b+c)=2(a+b) 因此有：a=c+(n−1)(b+c)，因此从起点到入环处的距离a恰好等于c加上n-1倍环长，那么让慢指针从相遇处开始走c距离之后
    // 就是入环处了，由于c未知，那么使用另一个慢指针从起点开始走，他俩相遇处就是入环处
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }

}
