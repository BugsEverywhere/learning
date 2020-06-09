package indi.simon.learning.leetcode.tobereviewed;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P11MostWaterContainer_NotFinish {

    public static void main(String[] args) {

    }

    private static int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;

        int maxQuantum = Integer.MIN_VALUE;

        while (i != j) {
            int quantumThisRound = Math.min(height[i], height[j]) * (j - i);
            if (quantumThisRound > maxQuantum) {
                maxQuantum = quantumThisRound;
            }
            if (height[i] > height[j]) {
                j--;
            } else {
                i++;
            }
        }

        return maxQuantum;
    }


}
