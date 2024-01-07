package indi.simon.learning.leetcode.gogo20240101;

import indi.simon.learning.leetcode.commonmodel.ListNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Chen Zhuo on 2024/1/7.
 */
public class Quiz2487 {

    public static void main(String[] args) {

    }

    public ListNode removeNodes(ListNode head) {
        List<ListNode> list = new ArrayList<>();

        ListNode curr = head;
        while (curr != null) {
            list.add(curr);
            curr = curr.next;
        }

        ListNode max = null;
        for (int i = list.size() - 1; i >= 0; i--) {
            ListNode o = list.get(i);
            if (max == null) {
                max = o;
                //todo: 此处要注意大于和等于时都需要赋值max
            } else if(o.val >= max.val){
                o.next = max;
                max = o;
            }
        }
        return max;
    }

}
