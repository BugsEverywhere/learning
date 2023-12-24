package indi.simon.learning.leetcode.gogo20231218;

/**
 * Created by Chen Zhuo on 2023/12/24.
 */
public class Quiz1954 {

    public static void main(String[] args) {
        Quiz1954 quiz1954 = new Quiz1954();
        long res = quiz1954.minimumPerimeter(1000000000);
        System.out.println(res);
    }

    public long minimumPerimeter(long neededApples) {
        long x = 0;
        long y = 0;

        long sum = 0;
        while (sum < neededApples) {
            x++;
            y = (y + 8*(x-1))+4+8*(x-1) + 8*x;
            sum += y;
        }
        return x * 8;
    }
}
