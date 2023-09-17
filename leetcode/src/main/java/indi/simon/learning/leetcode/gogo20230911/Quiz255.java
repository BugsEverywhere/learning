package indi.simon.learning.leetcode.gogo20230911;

/**
 * Created by Chen Zhuo on 2023/9/17.
 */
public class Quiz255 {

    public static void main(String[] args) {
        Quiz255 quiz255 = new Quiz255();
        boolean res = quiz255.verifyPreorder(new int[]{5, 2, 6, 1, 3});
        System.out.println(res);
    }

    public boolean verifyPreorder(int[] preorder) {
        //第i个元素标识i及其之后最小的元素
        int[] afterMinVal = new int[preorder.length];
        int min = Integer.MAX_VALUE;
        for (int i = preorder.length - 1; i >= 0; i--) {
            min = Math.min(min, preorder[i]);
            afterMinVal[i] = min;
        }

        //对每一个元素，在其后一连串小于它的元素之后，必须紧跟着一连串大于它的元素，直至结尾
        for (int i = 0; i < preorder.length; i++) {
            int j;
            for (j = i + 1; j < preorder.length; j++) {
                if (preorder[j] > preorder[i]) {
                    break;
                }
            }
            if (j == preorder.length) {
                //i后面没有大于它的元素了，i本身满足了条件，继续考察后续元素
                continue;
            }
            //j及其后的所有元素中最小的元素都比i大，则满足条件
            if (afterMinVal[j] < preorder[i]) {
                return false;
            }
        }

        return true;
    }
}
