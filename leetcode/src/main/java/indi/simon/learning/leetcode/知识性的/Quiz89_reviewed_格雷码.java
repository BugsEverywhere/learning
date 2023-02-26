package indi.simon.learning.leetcode.知识性的;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 官方解法不是临场能想出来的，关键知识点，2的n次幂在位运算里面就是1左移n位，任何比2的n次幂小的数，二进制位数都不及他，比如1000是8，有四位，比8小的都不及4位，那么2的3次幂-1，也就是7，
// 二进制撑死也就是111。这里官方的解法是，n位格雷码排列可以从n-1位格雷码排列得到，也就是把n-1位格雷码排列镜像一下，就得到了n位格雷码排列。因为镜像了一下，整个排列的长度翻一倍，那么相当于在
// 二进制上，整个排列的所有数字都在最高位多了一位，那么在原来n-1位排列的顶部多出一位0，镜像出来的这一半，顶部都补一个1，那么就从n-1位格雷码形成了一个n位格雷码排列
public class Quiz89_reviewed_格雷码 {

    public static void main(String[] args) {
        Quiz89_reviewed_格雷码 quiz89 = new Quiz89_reviewed_格雷码();
        List<Integer> res = quiz89.grayCode(3);
        System.out.println(res);
    }


    public List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<>();
        ret.add(0);
        for (int i = 1; i <= n; i++) {
            int m = ret.size();
            int leftMoveCount = i - 1;
            //镜像的另一半排列需要在最高位补的1先左移到固定位置
            int temp = (1 << leftMoveCount);
            //然后从后往前遍历上一次的排列，依次顶部补1即可，补的方式就是跟temp或一下
            for (int j = m - 1; j >= 0; j--) {
                int lastNum = ret.get(j);
                int numToBeAdded = lastNum | temp;
                ret.add(numToBeAdded);
            }
        }
        return ret;
    }

}
