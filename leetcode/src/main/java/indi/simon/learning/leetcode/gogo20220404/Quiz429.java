package indi.simon.learning.leetcode.gogo20220404;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import indi.simon.learning.leetcode.commonmodel.Node;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz429 {

    public static void main(String[] args) {

    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> levelList = new ArrayList<>();
        levelOrderInternal(root,0,levelList);
        return levelList;
    }

    private void levelOrderInternal(Node node, int level, List<List<Integer>> levelList) {
        if (node == null) {
            return;
        }

        List<Integer> thisLevelNodeList;

        if (levelList.size() >= level + 1) {
            thisLevelNodeList = levelList.get(level);
        } else {
            thisLevelNodeList = new ArrayList<>();
            levelList.add(thisLevelNodeList);
        }

        thisLevelNodeList.add(node.val);

        for (Node singleNode : node.children) {
            levelOrderInternal(singleNode, level + 1, levelList);
        }

    }


}
