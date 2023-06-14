package indi.simon.learning.leetcode.gogo20230612;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1375 {

    public static void main(String[] args) {

    }

    public int numTimesAllBlue(int[] flips) {
        int maxMark = 0;
        int cnt = 0;
        for (int i = 0; i < flips.length; i++) {
            maxMark = Math.max(maxMark, flips[i]);
            if (maxMark == i + 1) {
                cnt++;
            }
        }

        return cnt;
    }

}
