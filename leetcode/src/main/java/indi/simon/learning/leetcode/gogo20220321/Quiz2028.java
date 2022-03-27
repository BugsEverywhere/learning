package indi.simon.learning.leetcode.gogo20220321;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2028 {

    public static void main(String[] args) {
        Quiz2028 quiz2028 = new Quiz2028();
        int[] rolls = new int[]{6,3,4,3,5,3};
        int[] res = quiz2028.missingRolls(rolls, 1, 6);
        System.out.println(res);
    }

    public int[] missingRolls(int[] rolls, int mean, int n) {
        int totalCount = rolls.length + n;
        int sum = mean * totalCount;

        int mSum = 0;
        for (int i = 0; i < rolls.length; i++) {
            mSum = mSum + rolls[i];
        }


        int subMean = (sum - mSum) / n;
        if (subMean > 6 || (subMean == 6 && sum - mSum > subMean * n)) {
            return new int[]{};
        }
        if (subMean <= 0) {
            return new int[]{};
        }
        //todo: 两次提交失败，分别就是没有剩余的每一次投掷的均值超出考虑骰子的上下界的情况

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = subMean;
        }

        for (int i = 0; i < sum - mSum - subMean * n; i++) {
            res[i] = res[i] + 1;
        }

        return res;
    }

}
