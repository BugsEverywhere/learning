package indi.simon.learning.leetcode.gogo20230206;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2335 {

    public static void main(String[] args) {
        Quiz2335 quiz2335 = new Quiz2335();
        int res = quiz2335.fillCups(new int[]{5, 4, 4});
        System.out.println(res);
    }

    public int fillCups(int[] amount) {
        int res = 0;

        while (amount[0] > 0 || amount[1] > 0 || amount[2] > 0) {
            Arrays.sort(amount);

            amount[2]--;
            amount[1]--;

            if (amount[2] < 0) {
                amount[2]++;
            }

            if (amount[1] < 0) {
                amount[1]++;
            }
            res++;
        }
        return res;
    }

}
