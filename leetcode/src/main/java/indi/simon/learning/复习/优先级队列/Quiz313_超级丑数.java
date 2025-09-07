package indi.simon.learning.复习.优先级队列;

import java.util.*;

/**
 * 超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
 *
 * 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
 *
 * 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
 *
 * 示例 1：
 *
 * 输入：n = 12, primes = [2,7,13,19]
 * 输出：32
 * 解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
 * 示例 2：
 *
 * 输入：n = 1, primes = [2,3,5]
 * 输出：1
 * 解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
 * 提示：
 *
 * 1 <= n <= 105
 * 1 <= primes.length <= 100
 * 2 <= primes[i] <= 1000
 * 题目数据 保证 primes[i] 是一个质数
 * primes 中的所有值都 互不相同 ，且按 递增顺序 排列
 *
 *
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
