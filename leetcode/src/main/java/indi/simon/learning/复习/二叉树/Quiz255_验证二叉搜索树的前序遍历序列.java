package indi.simon.learning.复习.二叉树;

import java.util.Stack;

/**
 * Created by Chen Zhuo on 2023/9/17.
 * <p>
 * 给定一个 无重复元素 的整数数组 preorder ， 如果它是以二叉搜索树的先序遍历排列 ，返回 true 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入: preorder = [5,2,1,3,6]
 * 输出: true
 * 示例 2：
 * <p>
 * 输入: preorder = [5,2,6,1,3]
 * 输出: false
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= preorder.length <= 104
 * 1 <= preorder[i] <= 104
 * preorder 中 无重复元素
 */
public class Quiz255_验证二叉搜索树的前序遍历序列 {

    public static void main(String[] args) {
        Quiz255_验证二叉搜索树的前序遍历序列 quiz255 = new Quiz255_验证二叉搜索树的前序遍历序列();
        boolean res = quiz255.verifyPreorder(new int[]{5, 2, 6, 1, 3});
        System.out.println(res);
    }

    public boolean verifyPreorder(int[] preorder) {
        //afterMinVal[i]表示i及其之后最小的元素
        int[] afterMinVal = new int[preorder.length];
        int min = Integer.MAX_VALUE;
        for (int i = preorder.length - 1; i >= 0; i--) {
            min = Math.min(min, preorder[i]);
            afterMinVal[i] = min;
        }

        //对每一个元素，在其后一连串小于它的元素之后，必须紧跟着一连串大于它的元素，直至结尾
        for (int i = 0; i < preorder.length; i++) {
            //找到i之后第一个比i大的数，理论上这应该是树中i节点的右子节点
            int j;
            for (j = i + 1; j < preorder.length; j++) {
                if (preorder[j] > preorder[i]) {
                    break;
                }
            }
            if (j == preorder.length) {
                //i后面没有大于它的元素了，i本身满足了条件（说明树中i没有右子节点了），继续考察后续元素
                continue;
            }
            //j及其后的所有元素都比i大，则i是合格的。否则，哪怕只有一个元素（所以只用看j之后最小的元素）比i还小，那就不满足
            if (afterMinVal[j] < preorder[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * 官方的O(1)空间复杂度解法
     *
     * @param preorder
     * @return
     */
    public boolean verifyPreorderO1(int[] preorder) {
        Stack<Integer> stack = new Stack<Integer>();
        int min = Integer.MIN_VALUE;
        int length = preorder.length;
        for (int i = 0; i < length; i++) {
            int num = preorder[i];
            if (num <= min) {
                return false;
            }
            while (!stack.isEmpty() && num > stack.peek()) {
                min = stack.pop();
            }
            stack.push(num);
        }
        return true;
    }
}
