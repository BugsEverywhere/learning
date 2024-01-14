package indi.simon.learning.leetcode.gogo20240108;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2024/1/13.
 */
public class Quiz894_needReview {

    public static void main(String[] args) {
        Quiz894_needReview quiz894NeedReview = new Quiz894_needReview();
        List<TreeNode> res = quiz894NeedReview.allPossibleFBT(7);
        System.out.println(res);
    }

    Map<Integer, List<TreeNode>> memo = new HashMap();

    public List<TreeNode> allPossibleFBT(int N) {
        if (!memo.containsKey(N)) {
            List<TreeNode> ans = new LinkedList();
            if (N == 1) {
                ans.add(new TreeNode(0));
            } else if (N % 2 == 1) {
                for (int x = 0; x < N; ++x) {
                    int y = N - 1 - x;
                    for (TreeNode left: allPossibleFBT(x))
                        for (TreeNode right: allPossibleFBT(y)) {
                            TreeNode bns = new TreeNode(0);
                            bns.left = left;
                            bns.right = right;
                            ans.add(bns);
                        }
                }
            }
            memo.put(N, ans);
        }

        return memo.get(N);
    }

}
