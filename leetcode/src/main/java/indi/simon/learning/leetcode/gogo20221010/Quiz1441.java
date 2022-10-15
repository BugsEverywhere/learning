package indi.simon.learning.leetcode.gogo20221010;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1441 {

    public static void main(String[] args) {
        Quiz1441 quiz1441 = new Quiz1441();
        List<String> res = quiz1441.buildArray(new int[]{1,2}, 4);
        System.out.println(res);
    }

    public List<String> buildArray(int[] target, int n) {
        List<String> res = new ArrayList<>();
        int index = 1;

        for (int i = 0; i < target.length && index <= n; i++) {
            while (index < target[i]) {
                res.add("Push");
                res.add("Pop");
                index++;
            }
            res.add("Push");
            index++;
        }

        return res;
    }

}
