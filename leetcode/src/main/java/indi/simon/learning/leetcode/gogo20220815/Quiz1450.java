package indi.simon.learning.leetcode.gogo20220815;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1450 {

    public static void main(String[] args) {
        Quiz1450 quiz1450 = new Quiz1450();
        int res = quiz1450.busyStudent(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1}, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10}, 5);
        System.out.println(res);
    }

    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int res = 0;
        for (int i = 0; i < startTime.length; i++) {
            if (queryTime <= endTime[i] && queryTime >= startTime[i]) {
                res++;
            }
        }
        return res;
    }

}
