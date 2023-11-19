package indi.simon.learning.leetcode.gogo20231113;

import indi.simon.learning.leetcode.commonmodel.ImmutableListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2023/11/19.
 */
public class Quiz1265 {

    public static void main(String[] args) {

    }

    private ImmutableListNode immutableListNode;

    public void printLinkedListInReverse(ImmutableListNode head) {
        if (head == null) {
            return;
        }

        List<ImmutableListNode> nodeList = new ArrayList<>();

        while (head != null) {
            nodeList.add(head);
            head = head.getNext();
        }

        for (int i = nodeList.size() - 1; i >= 0; i--) {
            nodeList.get(i).printValue();
        }

    }
}
