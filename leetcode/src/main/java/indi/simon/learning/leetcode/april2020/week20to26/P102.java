package indi.simon.learning.leetcode.april2020.week20to26;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.*;

public class P102 {

    private static Map<Integer, List<Integer>> listMap = new LinkedHashMap<>();
    private static int level = 0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode nine = new TreeNode(9);
        TreeNode twenty = new TreeNode(20);
        TreeNode fifteen = new TreeNode(15);
        TreeNode seven = new TreeNode(7);

        root.right = twenty;
        root.left = nine;
        twenty.right = seven;
        twenty.left = fifteen;

        System.out.print(Arrays.toString(levelOrder(root).toArray()));


    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        recursiveTraver(root);
        Iterator<Map.Entry<Integer, List<Integer>>> it = listMap.entrySet().iterator();
        while (it.hasNext()) {
            list.add(it.next().getValue());
        }
        return list;
    }

    private static void recursiveTraver(TreeNode node) {
        //todo: null的情况第一次提交忘了，虽然递归的时候有在上层判空，但是架不住root直接丢个空进来啊，以后null统一在最外层函数一进来就处理掉吧
        if (node == null) {
            return;
        }
        List<Integer> thisLevelList = listMap.get(level);
        if (thisLevelList == null) {
            thisLevelList = new ArrayList<>();
            listMap.put(level, thisLevelList);
        }

        thisLevelList.add(node.val);

        level++;
        if (node.left != null) {
            recursiveTraver(node.left);
        }
        if (node.right != null) {
            recursiveTraver(node.right);
        }
        level--;
    }

}
