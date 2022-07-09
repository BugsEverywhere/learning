package indi.simon.learning.leetcode.gogo20220704;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1217 {

    public static void main(String[] args) {

        Quiz1217 quiz1217 = new Quiz1217();
        int[] position = new int[]{2, 2, 2, 3, 3};
        int res = quiz1217.minCostToMoveChips(position);
        System.out.println(res);
    }

    public int minCostToMoveChips(int[] position) {
        int oddNumCount = 0;
        int evenNumCount = 0;

        for (int i = 0; i < position.length; i++) {
            if (position[i] % 2 == 0) {
                evenNumCount++;
            } else {
                oddNumCount++;
            }
        }

        return Math.min(oddNumCount, evenNumCount);
    }

}
