package indi.simon.learning.leetcode.gogo20240212;

import indi.simon.learning.leetcode.commonmodel.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Zhuo on 2024/2/18.
 */
public class Quiz589 {

    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        traverasl(list, root);
        return list;
    }

    private void traverasl(List<Integer> list, Node node){
        if(node == null){
            return;
        }
        list.add(node.val);
        List<Node> children = node.children;
        if(children == null){
            return;
        }
        for(Node child : children){
            traverasl(list, child);
        }
    }
}
