package indi.simon.learning.leetcode.gogo20220411;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1672 {

    public static void main(String[] args) {

    }

    public int maximumWealth(int[][] accounts) {
        int maxProperty = Integer.MIN_VALUE;
        for (int i = 0; i < accounts.length; i++) {
            int propertyThisRichGuy = 0;
            for (int j = 0; j < accounts[0].length; j++) {
                propertyThisRichGuy += accounts[i][j];
            }
            if (propertyThisRichGuy > maxProperty) {
                maxProperty = propertyThisRichGuy;
            }
        }
        return maxProperty;
    }
}
