package indi.simon.learning.leetcode.gogo20220815;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1422 {

    public static void main(String[] args) {
        Quiz1422 quiz1422 = new Quiz1422();
        int res = quiz1422.maxScore("1111");
        System.out.println(res);
    }

    public int maxScore(String s) {
        int rightScore = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                rightScore++;
            }
        }

        int res = 0;
        int leftScore = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') {
                leftScore++;
            } else {
                rightScore--;
            }
            res = Math.max(res, rightScore + leftScore);
        }

        return res;
    }

}
