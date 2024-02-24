package indi.simon.learning.leetcode.gogo20240219;

/**
 * Created by Chen Zhuo on 2024/2/24.
 */
public class Quiz1109 {

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];

        for (int i = 0; i < bookings.length; i++) {
            for (int j = bookings[i][0] - 1; j <= bookings[i][1] - 1; j++) {
                res[j] += bookings[i][2];
            }
        }

        return res;
    }
}
