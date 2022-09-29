package indi.simon.learning.leetcode.gogo20220912;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1619 {

    public static void main(String[] args) {
        int[] arr = new int[]{6, 2, 7, 5, 1, 2, 0, 3, 10, 2, 5, 0, 5, 5, 0, 8, 7, 6, 8, 0};
        Quiz1619 quiz1619 = new Quiz1619();
        double res = quiz1619.trimMean(arr);
        System.out.println(res);
    }

    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        double threshold = arr.length * 0.05;

        int sum = 0;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i + 1 <= threshold || arr.length  - i <= threshold) {
                continue;
            }

            sum += arr[i];
            count++;
        }

        return (double) sum / (double) count;
    }

}
