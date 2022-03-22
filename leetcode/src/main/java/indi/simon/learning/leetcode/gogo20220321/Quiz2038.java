package indi.simon.learning.leetcode.gogo20220321;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2038 {

    public static void main(String[] args) {
        Quiz2038 quiz2038 = new Quiz2038();
        boolean res = quiz2038.winnerOfGame("BBBAAAABB");
        System.out.println(res);
    }

    public boolean winnerOfGame(String colors) {
        char[] chars = colors.toCharArray();

        int totalEffectiveACount = 0;
        int consecutiveACount = 0;

        int totalEffectiveBCount = 0;
        int consecutiveBCount = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'A' && (i - 1 >= 0 && chars[i - 1] == 'A') && (i + 1 < chars.length && chars[i + 1] == 'A')) {
                consecutiveACount++;
            } else {
                totalEffectiveACount = totalEffectiveACount + consecutiveACount;
                consecutiveACount = 0;
            }

            if (chars[i] == 'B' && (i - 1 >= 0 && chars[i - 1] == 'B') && (i + 1 < chars.length && chars[i + 1] == 'B')) {
                consecutiveBCount++;
            } else {
                totalEffectiveBCount = totalEffectiveBCount + consecutiveBCount;
                consecutiveBCount = 0;
            }
        }

        if (totalEffectiveACount > totalEffectiveBCount) {
            return true;
        } else {
            return false;
        }
    }

}
