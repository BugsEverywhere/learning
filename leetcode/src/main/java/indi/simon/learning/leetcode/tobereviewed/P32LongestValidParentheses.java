package indi.simon.learning.leetcode.tobereviewed;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P32LongestValidParentheses {

    public static void main(String[] args) {

        int count = longestValidParentheses("()(())");

        System.out.println(count);
    }

    public static int longestValidParentheses(String s) {
        if (s.length() == 0) {
            return 0;
        }
        char[] charArr = s.toCharArray();
        int longestCount = 0;
        for (int i = 0; i < charArr.length; i++) {
            int leftBucketCountThisRound = 0;
            int j = i;
            if (charArr[i] != '(') {
                continue;
            }
            leftBucketCountThisRound++;
            while (leftBucketCountThisRound >= 0 && j < charArr.length - 1) {
                j++;
                if (charArr[j] == '(') {
                    leftBucketCountThisRound++;
                } else {
                    leftBucketCountThisRound--;
                }
                if (leftBucketCountThisRound == 0) {
                    longestCount = j - i + 1 > longestCount ? j - i + 1 : longestCount;
                }
            }
            if (j == charArr.length - 1) {
                //j已经到了末尾字符，则需要判断一下
                if (leftBucketCountThisRound == 0 && j - i > longestCount) {
                    //左括号的数量刚好减到0，说明是有效字符串,而且i往后遍历不可能比这更长了，直接返回
                    return j - i + 1;
                } else if (leftBucketCountThisRound > 0 && j - i - leftBucketCountThisRound + 1 > longestCount) {
                    //左括号的数量大于0，说明左括号多了，不是有效字符，继续遍历后面的
                    continue;
                } else if (leftBucketCountThisRound < 0) {
                    //左括号的数量小于0，说明是最后一个右括号导致leftBucketCountThisRound为-1
                    if(j - i > longestCount){
                        return j - i;
                    }
                }
            }
        }
        return longestCount;
    }

}

//todo: 我的思路是双指针，那这样最坏的复杂度是n方的，期间各种边界条件需要注意。
// 最需要注意的一点是，如果维护了一个全局的最值，那在循环中的每次打到符合条件的情况时都要记得更新这个最值，比如
// 这里的while循环中，需要保持longestCount的最大，这样longestCount才有意义，而不是在while循环结束之后采取更新