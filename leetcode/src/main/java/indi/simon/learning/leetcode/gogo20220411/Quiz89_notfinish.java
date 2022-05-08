package indi.simon.learning.leetcode.gogo20220411;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 官方解法不是临场能想出来的，关键知识点，2的n次幂在位运算里面就是1左移n位，任何比2的n次幂小的数，二进制位数都不及他，比如1000是8，有四位，比8小的都不及4位，那么2的3次幂-1，也就是7，
// 二进制撑死也就是111。
public class Quiz89_notfinish {

    public static void main(String[] args) {
        Quiz89_notfinish quiz89 = new Quiz89_notfinish();
        List<Integer> res = quiz89.grayCode(3);
        System.out.println(res);
    }


    public List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<>();
        ret.add(0);
        for (int i = 1; i <= n; i++) {
            int m = ret.size();
            int leftMoveCount = i - 1;
            int temp = (1 << leftMoveCount);
            for (int j = m - 1; j >= 0; j--) {
                int lastNum = ret.get(j);
                int numToBeAdded = lastNum | temp;
                ret.add(numToBeAdded);
            }
        }
        return ret;
    }

}
