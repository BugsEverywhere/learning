package indi.simon.learning.复习.二叉树;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 将每个节点及其子节点的值拼接成字符串，如果该字符串之前出现过，那么就将其加入答案。这样做比我那种逐个比对的方式好的地方在于，
// 减少了时间复杂度，如果某个子树出现了多次，序列化成字符串可以很快知道这一点，而不用每一次都与之前的子树进行比对，这样很耗时
public class Quiz652寻找重复子树 {

    public static void main(String[] args) {
        TreeNode node0 = new TreeNode(0);
        TreeNode node1 = new TreeNode(0);
        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(0);
        TreeNode node5 = new TreeNode(0);
        TreeNode node6 = new TreeNode(0);
        TreeNode node7 = new TreeNode(0);
        TreeNode node8 = new TreeNode(0);


        node0.left = node1;
        node0.right = node2;

        node1.left = node3;

        node2.right = node4;

        node3.left = node5;
        node3.right = node6;

        node4.left = node7;
        node4.right = node8;

        Quiz652寻找重复子树 quiz652NeedReview = new Quiz652寻找重复子树();
        List<TreeNode> res = quiz652NeedReview.findDuplicateSubtrees(node0);
        System.out.println(res);
    }

    //todo: =================================================正确的做法
    Map<String, Integer> cntMap = new HashMap<>();
    List<TreeNode> ans = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtreesRight(TreeNode root) {
        dfs(root);
        return ans;
    }

    private String dfs(TreeNode root) {
        if (root == null) {
            return " ";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.val).append("_");
        sb.append(dfs(root.left)).append("#").append(dfs(root.right));
        String key = sb.toString();
        cntMap.put(key, cntMap.getOrDefault(key, 0) + 1);
        if (cntMap.get(key) == 2) {
            ans.add(root);
        }
        return key;
    }

    //===================================================我的傻逼做法，超时

    private Map<Integer, List<TreeNode>> map;
    private List<TreeNode> res;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        map = new HashMap<>();
        res = new ArrayList<>();
        findDuplicateSubtreesInternal(root);
        return res;
    }

    private void findDuplicateSubtreesInternal(TreeNode node) {
        if (node == null) {
            return;
        }
        if (map.containsKey(node.val)) {
            //之前有过这个数的节点，挨个儿把他们拿出来跟本节点比较一下
            boolean addedIntoMap = false;
            int i = 0;
            for (; i < map.get(node.val).size(); i++) {
                TreeNode originNode = map.get(node.val).get(i);
                if (isSame(originNode, node)) {
                    addedIntoMap = true;
                    if (res.contains(originNode)) {
                        continue;
                    }
                    res.add(originNode);
                }
            }

            if (!addedIntoMap) {
                //是一个全新的结构，需要把它加到之前的map里去
                map.get(node.val).add(node);
            }
        } else {
            //之前没有该值的节点，记录之后继续遍历
            List<TreeNode> nodeList = new ArrayList<>();
            nodeList.add(node);
            map.put(node.val, nodeList);
        }
        findDuplicateSubtreesInternal(node.left);
        findDuplicateSubtreesInternal(node.right);
    }

    private boolean isSame(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        } else if ((node1 == null && node2 != null) || (node2 == null && node1 != null)) {
            return false;
        }
        return node1.val == node2.val && isSame(node1.left, node2.left) && isSame(node1.right, node2.right);
    }

}
