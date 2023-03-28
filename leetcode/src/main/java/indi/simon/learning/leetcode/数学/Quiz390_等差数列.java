package indi.simon.learning.leetcode.数学;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 也是采用的循环来实现，但是考虑了等差数列
public class Quiz390_等差数列 {

    public static void main(String[] args) {

    }

    public int lastRemaining(int n) {
        //数列首位
        int a1 = 1;
        //趟次，偶数为正向，奇数为负向
        int k = 0;
        //等差数列的元素个数
        int cnt = n;
        //等差数列的差值
        int step = 1;
        while (cnt > 1) {
            if (k % 2 == 0) {
                // 正向
                a1 = a1 + step;
            } else {
                // 反向
                a1 = (cnt % 2 == 0) ? a1 : a1 + step;
            }
            k++;
            //数列元素减一半
            cnt = cnt >> 1;
            //等差数列的差值翻一倍
            step = step << 1;
        }
        return a1;
    }

}
