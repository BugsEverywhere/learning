package indi.simon.learning.复习.位运算;

/**
 * @author chenzhuo(zhiyue)
 *
 * 给你两个整数 left 和 right ，表示区间 [left, right] ，返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。
 *
 * 示例 1：
 *
 * 输入：left = 5, right = 7
 * 输出：4
 * 示例 2：
 *
 * 输入：left = 0, right = 0
 * 输出：0
 * 示例 3：
 *
 * 输入：left = 1, right = 2147483647
 * 输出：0
 *
 *
 * 提示：
 *
 * 0 <= left <= right <= 231 - 1
 *
 */
//todo:
public class Quiz201_数字范围按位与 {

    public static void main(String[] args) {
        Quiz201_数字范围按位与 quiz201数字范围按位与 = new Quiz201_数字范围按位与();
        int res = quiz201数字范围按位与.rangeBitwiseAnd(4, 11);
        System.out.println(res);
    }

    public int rangeBitwiseAnd(int m, int n) {

        int shift = 0;
        // 找到公共前缀
        while (m < n) {
            m >>= 1;
            n >>= 1;
            ++shift;
        }
        return m << shift;
    }

}
