package indi.simon.learning.leetcode.gogo20230710;

import indi.simon.learning.leetcode.commonmodel.TreeNode;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz979 {

    public static void main(String[] args) {

    }

    private int totalStep;

    public int distributeCoins(TreeNode root) {
        totalStep = 0;
        needCoinCnt(root);
        return totalStep;
    }

    /**
     * 返回值代表该节点及其所有子节点所能提供的硬币数，正则能对外提供，为负则需要从外面索取
     *
     * @param node
     * @return
     */
    private int needCoinCnt(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftCoin = needCoinCnt(node.left);
        int rightCoin = needCoinCnt(node.right);
        //本节点的硬币，自己需要留一份，因此要减1
        int curr = node.val - 1;
        int currNeedCnt = curr + leftCoin + rightCoin;
        //计算本节点往下内部调动需要多少次
        if (currNeedCnt > 0) {
            //可以支援外部
            totalStep += currNeedCnt;
        } else if (currNeedCnt < 0) {
            //需要转移支付
            totalStep += Math.abs(currNeedCnt);
        }
        return currNeedCnt;
    }


}
