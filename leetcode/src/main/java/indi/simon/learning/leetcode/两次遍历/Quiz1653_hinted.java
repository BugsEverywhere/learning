package indi.simon.learning.leetcode.两次遍历;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 一开始思路是对的，两次遍历，正一趟反一趟，但是是要统计字符之间隔板的左b数和右a数，不是字母本身左b数右a数
public class Quiz1653_hinted {

    public static void main(String[] args) {
        Quiz1653_hinted quiz1653Hinted = new Quiz1653_hinted();
        int res = quiz1653Hinted.minimumDeletions("b");
        System.out.println(res);
    }

    public int minimumDeletions(String s) {

        int[] leftBCount = new int[s.length() + 1];
        int[] rightACount = new int[s.length() + 1];

        for (int i = 1; i <= s.length(); i++) {
            if (s.charAt(i - 1) == 'b') {
                leftBCount[i] = leftBCount[i - 1] + 1;
            } else {
                leftBCount[i] = leftBCount[i - 1];
            }
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == 'a') {
                rightACount[i] = rightACount[i + 1] + 1;
            } else {
                rightACount[i] = rightACount[i + 1];
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < s.length() + 1; i++) {
            res = Math.min(res, leftBCount[i] + rightACount[i]);
        }

        return res;
    }

}
