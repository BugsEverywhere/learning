package indi.simon.learning.leetcode.gogo20240219;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Chen Zhuo on 2024/2/24.
 */
public class Quiz2583 {

    public static void main(String[] args) {

    }

    public long kthLargestLevelSum(TreeNode root, int k) {
        Map<Integer, Long> levelSumMap = new HashMap<>();
        levelTraversal(levelSumMap, 0, root);
        PriorityQueue<Long> queue = new PriorityQueue<>((o1, o2) -> Long.compare(o2,o1));
        for(Map.Entry<Integer, Long> entry : levelSumMap.entrySet()){
            queue.offer(entry.getValue());
        }
        if(k > queue.size()){
            return -1;
        }
        int i = 0;
        long res = 0;
        while(i < k){
            res = queue.poll();
            i++;
        }
        return res;
    }

    private void levelTraversal(Map<Integer, Long> levelSumMap, int level, TreeNode node) {
        if (node == null) {
            return;
        }
        Long levelSum = levelSumMap.getOrDefault(level, 0L);
        levelSum += node.val;
        levelSumMap.put(level, levelSum);
        levelTraversal(levelSumMap, level + 1, node.left);
        levelTraversal(levelSumMap, level + 1, node.right);
    }
}
