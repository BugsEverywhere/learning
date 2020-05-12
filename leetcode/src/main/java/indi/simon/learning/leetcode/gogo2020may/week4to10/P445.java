package indi.simon.learning.leetcode.gogo2020may.week4to10;

import indi.simon.learning.leetcode.commonmodel.ListNode;

import java.util.concurrent.LinkedBlockingDeque;


public class P445 {

    public static void main(String[] args) {

        ListNode seven = new ListNode(7);
        ListNode two = new ListNode(2);
        ListNode four = new ListNode(4);
        ListNode three = new ListNode(3);
        seven.next = two;
        two.next = four;
        four.next = three;

        ListNode five = new ListNode(5);
        ListNode six = new ListNode(6);
        ListNode fourAgain = new ListNode(4);
        five.next = six;
        six.next = fourAgain;

        ListNode result = addTwoNumbers(seven, five);
        System.out.println(result);

    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null || l1.val == 0) {
            return l2;
        }
        if (l2 == null || l2.val == 0) {
            return l1;
        }

        LinkedBlockingDeque<ListNode> twoEndsQueue1 = new LinkedBlockingDeque<>();
        LinkedBlockingDeque<ListNode> twoEndsQueue2 = new LinkedBlockingDeque<>();

        //todo 链表填充双端队列，从哪边添加别搞反了
        while (l1 != null) {
            twoEndsQueue1.addLast(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            twoEndsQueue2.addLast(l2);
            l2 = l2.next;
        }

        int jinwei = 0;

        LinkedBlockingDeque<ListNode> twoEndsQueue3 = new LinkedBlockingDeque<>();

        while (twoEndsQueue1.size() != 0 || twoEndsQueue2.size() != 0) {
            ListNode l1Node;
            ListNode l2Node;
            int thisResult;
            try {
                if (twoEndsQueue1.size() != 0 && twoEndsQueue2.size() != 0) {
                    l1Node = twoEndsQueue1.takeLast();
                    l2Node = twoEndsQueue2.takeLast();
                    thisResult = l1Node.val + l2Node.val + jinwei;
                } else if (twoEndsQueue1.size() == 0) {
                    l2Node = twoEndsQueue2.takeLast();
                    thisResult = l2Node.val + jinwei;
                } else {
                    l1Node = twoEndsQueue1.takeLast();
                    thisResult = l1Node.val + jinwei;
                }

                if (thisResult >= 10) {
                    jinwei = thisResult / 10;
                    thisResult = thisResult % 10;
                } else {
                    jinwei = 0;
                }
                ListNode l3Node = new ListNode(thisResult);
                twoEndsQueue3.addFirst(l3Node);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //todo 加数的时候之中要记得检查最后的进位是否为0
        if (jinwei != 0) {
            twoEndsQueue3.addFirst(new ListNode(jinwei));
        }

        ListNode lastOne = null;

        while (twoEndsQueue3.size() != 0) {
            try {
                if (lastOne == null) {
                    lastOne = twoEndsQueue3.takeLast();
                    continue;
                }

                ListNode thisOne = twoEndsQueue3.takeLast();
                thisOne.next = lastOne;
                lastOne = thisOne;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return lastOne;
    }

}
