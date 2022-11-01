package indi.simon.learning.leetcode.gogo20221031;


import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz481 {

    public static void main(String[] args) {
        Quiz481 quiz481 = new Quiz481();
        int res = quiz481.magicalString(1);
        System.out.println(res);
    }

    public int magicalString(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        int i = 1;
        int oneCount = 1;
        int currNum = 1;
        while (i < n) {
            int thisCount;
            if (i == 1) {
                //初始状态，1后面加入两个2
                list.add(2);
                list.add(2);
                currNum = 2;
            } else if (list.size() <= n) {
                //currNum转变一下
                currNum = currNum == 1 ? 2 : 1;
                //仅放入n个数即可
                //本次需要放入 thisCount 个 currNum
                thisCount = list.get(i);
                while (thisCount > 0) {
                    list.add(currNum);
                    thisCount--;
                }
            }
            if (list.get(i) == 1) {
                oneCount++;
            }
            i++;
        }
        return oneCount;
    }

}
