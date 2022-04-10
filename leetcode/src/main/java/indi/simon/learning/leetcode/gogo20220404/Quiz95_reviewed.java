package indi.simon.learning.leetcode.gogo20220404;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz95_reviewed {

    public static void main(String[] args) {
        Quiz95_reviewed quiz95 = new Quiz95_reviewed();
        List<TreeNode> res = quiz95.generateTrees(3);
        System.out.println(res);

    }


    public List<TreeNode> generateTreesOfficial(int n) {
        if (n == 0) {
            return new LinkedList<>();
        }
        return generateTrees(1, n);
    }

    //todo: 官方解法几乎每一个点我都想到了。。。只可惜没能串起来。临门一脚就是，既然左子树是1到i-1的所有可能，右子树是i+1到n的所有可能，那么
    // 左右节点的返回结果应该是List<TreeNode>，也就是说递归方法的返回值应该是一个List，而非单个TreeNode。然后本层节点的所有形状就由左List
    // 与右List的所有可能组合而成的一个List，再往上返回

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }
        // 枚举可行根节点
        for (int i = start; i <= end; i++) {
            // 获得所有可行的左子树集合
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            // 获得所有可行的右子树集合
            List<TreeNode> rightTrees = generateTrees(i + 1, end);
            // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currTree = new TreeNode(i);
                    currTree.left = left;
                    currTree.right = right;
                    allTrees.add(currTree);
                }
            }
        }
        return allTrees;
    }

    //===================================================================依葫芦画瓢

    public List<TreeNode> generateTrees(int n) {
        return generateTreesInternal(1, n);
    }

    //todo: 递归方法的入参只需要左右范围就行了，返回的就是左右范围里各个节点作为本层节点的所有情况
    private List<TreeNode> generateTreesInternal(int leftFrontier, int rightFrontier) {
        List<TreeNode> treeNodeList = new ArrayList<>();
        if (leftFrontier > rightFrontier) {
            treeNodeList.add(null);
            //todo: 注意这里要添加null节点，保证在上一层左子节点List或者右子节点list遍历的时候会遍历到这种情况
            return treeNodeList;
        }

        //遍历区间内所有节点作为本层根节点的情况
        for (int i = leftFrontier; i <= rightFrontier; i++) {
            List<TreeNode> leftNodeList = generateTreesInternal(leftFrontier, i - 1);
            List<TreeNode> rightNodeList = generateTreesInternal(i + 1, rightFrontier);
            for (TreeNode leftNode : leftNodeList) {
                for (TreeNode rightNode : rightNodeList) {
                    TreeNode node = new TreeNode(i);
                    node.left = leftNode;
                    node.right = rightNode;
                    treeNodeList.add(node);
                }
            }
        }
        return treeNodeList;
    }
}
