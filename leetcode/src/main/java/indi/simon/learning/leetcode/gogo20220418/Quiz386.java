package indi.simon.learning.leetcode.gogo20220418;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz386 {

    public static void main(String[] args) {
        Quiz386 quiz386 = new Quiz386();
        List<Integer> res = quiz386.lexicalOrder(5223);
        System.out.println(res);
    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            lexicalOrderInternal(n, res, Integer.toString(i));
        }
        return res;
    }

    private void lexicalOrderInternal(int n, List<Integer> res, String num) {
        int numInt = Integer.parseInt(num);

        if (numInt > n) {
            return;
        }

        res.add(numInt);

        for (int i = 0; i <= 9; i++) {
            lexicalOrderInternal(n, res, num + i);
        }
    }

}
