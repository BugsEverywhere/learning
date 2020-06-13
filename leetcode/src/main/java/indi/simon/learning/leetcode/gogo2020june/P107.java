package indi.simon.learning.leetcode.gogo2020june;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class P107 {

    public static void main(String[] args) {

    }

    private static List<List<Integer>> levelOrderBottom(TreeNode root) {
        Map<Integer, List<Integer>> listMap = new HashMap<>();
        levelOrderBottom(root, 1, listMap);
        Deque<List<Integer>> deque = new LinkedBlockingDeque<>();
        Iterator<Map.Entry<Integer, List<Integer>>> it = listMap.entrySet().iterator();
        while (it.hasNext()) {
            List<Integer> list = it.next().getValue();
            deque.addFirst(list);
        }
        return new ArrayList<>(deque);
    }

    private static void levelOrderBottom(TreeNode node, int level, Map<Integer, List<Integer>> listMap) {
        if (node == null) {
            return;
        }

        List<Integer> listThisLevel = listMap.get(level);
        if (listThisLevel == null) {
            listThisLevel = new ArrayList<>();
            listMap.put(level, listThisLevel);
        }
        listThisLevel.add(node.val);

        levelOrderBottom(node.left, level + 1, listMap);
        levelOrderBottom(node.right, level + 1, listMap);
    }


}

//todo 是有改进空间的，可以不需要那个map，也不用deque，直接使用list在每一层add(0, XXX)即可，因为DFS中底层永远是在上层都遍历过之后才被遍历到的