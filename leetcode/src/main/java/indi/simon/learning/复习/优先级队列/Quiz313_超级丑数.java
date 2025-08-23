package indi.simon.learning.复习.优先级队列;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz313_超级丑数 {

    public static void main(String[] args) {
        Quiz313_超级丑数 quiz313NeedReview = new Quiz313_超级丑数();
        int res = quiz313NeedReview.nthSuperUglyNumber(800, new int[]{37, 43, 59, 61, 67, 71, 79, 83, 89, 97, 101, 103, 113, 127, 131, 157, 163, 167, 173, 179, 191, 193, 197, 199, 211, 229, 233, 239, 251, 257});
        System.out.println(res);
    }

    //todo: 宫酱的优先级队列，从最小的丑数1入队开始，并且每次从队头取当前最小丑数，循环与primes中的质数相乘，然后入队，
    // 这样第n个出队的就是所求的超级丑数
    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.add(1);
        while (n-- > 0) {
            int x = q.poll();
            if (n == 0) {
                return x;
            }
            for (int k : primes) {
                if (k <= Integer.MAX_VALUE / x) {
                    q.add(k * x);
                }
                if (x % k == 0) {
                    break;
                }
            }
        }
        return -1; // never
    }


}
