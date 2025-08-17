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
     * 考虑二叉搜索树的特点：
     *
     * 每个节点的左子树只包含小于当前节点的数；
     * 每个节点的右子树只包含大于当前节点的数；
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * 二叉树的前序遍历首先访问根节点然后遍历左子树，最后遍历右子树，对于左子树和右子树的遍历分别也是前序遍历。从根节点开始，访问当前节点之后，如果当前节点有左子节点，则下一步访问左子节点，直到被访问的节点没有左子节点为止，然后访问右子节点。根据二叉搜索树的特点，左子节点的值小于父节点的值，右子节点的值大于父节点的值，因此，如果访问到的节点值小于上一个节点值，则说明当前节点是上一个节点的左子节点，如果访问到的节点值大于上一个节点值，则说明当前节点是某个节点的右子节点。
     *
     * 基于上述发现，可以维护一个单调栈，从栈底到栈顶的元素单调递减，另外维护一个当前的最小值，初始化为负无穷。
     *
     * 对给定的先序遍历序列，依次遍历其中的每个值。如果栈为空，或者当前值小于栈顶的值，则将当前值压入栈内。如果当前值大于栈顶的值，说明当前值是某个节点的右子节点的值，因此将栈内小于当前值的元素全部弹出，然后将当前值压入栈内。
     *
     * 最后弹出的元素是当前值所在节点的父节点的元素，即当前值所在节点是该父节点的右子节点，因此将最小值设为最后弹出的元素。根据二叉搜索树的特点，在父节点的右子树中的任何节点的值都必须大于父节点的值，即此时的最小值，如果在先序遍历序列中发现一个值小于或等于最小值，则该先序遍历序列不是二叉搜索树的正确先序遍历序列。
     *
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/verify-preorder-sequence-in-binary-search-tree/solutions/2477592/yan-zheng-qian-xu-bian-li-xu-lie-er-cha-h6tvp/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
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
