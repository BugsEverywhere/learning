package indi.simon.learning.leetcode.gogo20221128;

/**
 * @author chenzhuo(zhiyue)
 */
//牛逼啊，一把过的DP
public class Quiz1769 {

    public static void main(String[] args) {
        Quiz1769 quiz1769 = new Quiz1769();
        int[] res = quiz1769.minOperations("001011");
        System.out.println(res);
    }

    public int[] minOperations(String boxes) {
        //用来记录第i个小球左边小球的个数（包括他自身）
        int[] leftBalls = new int[boxes.length()];
        //用来记录第i个小球右边小球的个数（不包括他自身）
        int[] rightBalls = new int[boxes.length()];

        int[] res = new int[boxes.length()];

        //开始遍历计算
        for (int i = 0; i < boxes.length(); i++) {
            if (i == 0) {
                int ballCount = 0;
                for (int j = 1; j < boxes.length(); j++) {
                    if (boxes.charAt(j) == '1') {
                        res[0] += j;
                        ballCount++;
                    }
                }
                rightBalls[0] = ballCount;
                leftBalls[0] = boxes.charAt(0) == '1' ? 1 : 0;
            } else {
                rightBalls[i] = boxes.charAt(i) == '1' ? rightBalls[i - 1] - 1 : rightBalls[i - 1];
                leftBalls[i] = boxes.charAt(i) == '1' ? leftBalls[i - 1] + 1 : leftBalls[i - 1];
                res[i] = res[i - 1] + leftBalls[i - 1] - rightBalls[i - 1];
            }
        }

        return res;
    }


}
