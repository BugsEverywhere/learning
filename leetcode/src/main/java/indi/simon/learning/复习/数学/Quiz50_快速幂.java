package indi.simon.learning.复习.数学;

/**
 * @author chenzhuo(zhiyue)
 * <p>
 * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xn ）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 * <p>
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 * <p>
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 * 提示：
 * <p>
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * n 是一个整数
 * 要么 x 不为零，要么 n > 0 。
 * -104 <= xn <= 104
 */
//todo: 快速幂与机械算幂体现了O(logN)和O(N)的区别
public class Quiz50_快速幂 {

    public static void main(String[] args) {
        Quiz50_快速幂 quiz50 = new Quiz50_快速幂();
        double res = quiz50.myPow(2, -2147483648);
        System.out.println(res);
    }

    /**
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        return n >= 0 ? quickMul(x, n) : 1.0 / quickMul(x, -n);
    }

    public double quickMul(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        //todo: 关键一步，幂次减半递归，加快速度，O(N)变O(logN)
        double y = quickMul(x, n / 2);
        //按照n是奇数还是偶数次幂返回
        return n % 2 == 0 ?
                //偶数次幂
                y * y :
                //奇数次幂
                y * y * x;
    }

}
