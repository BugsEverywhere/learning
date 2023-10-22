package indi.simon.learning.leetcode.gogo20231022;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.*;

/**
 * Created by Chen Zhuo on 2023/10/22.
 */
public class Quiz314 {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(0);
        TreeNode node5 = new TreeNode(1);
        TreeNode node6 = new TreeNode(7);

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;

        Quiz314 quiz314 = new Quiz314();
        List<List<Integer>> res = quiz314.verticalOrder(root);
        System.out.println(res);
    }

    private int sequence;

    public List<List<Integer>> verticalOrder(TreeNode root) {
        sequence = 0;
        Map<Integer, PriorityQueue<int[]>> map = new HashMap<>();
        verticalOrderInternal(root, 0, 0, map);
        List<Integer> offsetList = new ArrayList<>(map.keySet());
        Collections.sort(offsetList);
        List<List<Integer>> res = new ArrayList<>();
        for (Integer offset : offsetList) {
            PriorityQueue<int[]> priorityQueue = map.get(offset);
            List<Integer> list = new ArrayList<>();
            while (priorityQueue.size() > 0) {
                int[] pair = priorityQueue.poll();
                list.add(pair[0]);
            }
            res.add(list);
        }
        return res;
    }

    private void verticalOrderInternal(TreeNode node, int offSet, int level, Map<Integer, PriorityQueue<int[]>> map) {
        if (node == null) {
            return;
        }
        PriorityQueue<int[]> thisQueue = map.getOrDefault(offSet, new PriorityQueue<>((o1, o2) -> {
            if (o1[2] < o2[2]) {
                return -1;
            } else if (o1[2] == o2[2]) {
                if (o1[1] < o2[1]) {
                    return -1;
                } else if (o1[1] > o2[1]) {
                    return 1;
                } else {
                    return Integer.compare(o1[3],o2[3]);
                }
            } else {
                return 1;
            }

        }));
        thisQueue.add(new int[]{node.val, offSet, level, sequence});
        sequence++;
        map.put(offSet, thisQueue);

        verticalOrderInternal(node.left, offSet - 1, level + 1, map);
        verticalOrderInternal(node.right, offSet + 1, level + 1, map);
    }

}
