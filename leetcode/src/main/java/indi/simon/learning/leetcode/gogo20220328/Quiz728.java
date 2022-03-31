package indi.simon.learning.leetcode.gogo20220328;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz728 {

    public static void main(String[] args) {
        Quiz728 quiz728 = new Quiz728();
        List<Integer> res = quiz728.selfDividingNumbers(1, 22);
        System.out.println(res);
    }

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            String numStr = Integer.toString(i);
            char[] charArr = numStr.toCharArray();
            boolean isOk = true;
            for (char singleChar : charArr) {
                if (singleChar == '0') {
                    isOk = false;
                    break;
                }
                if (i % (singleChar - '0') != 0) {
                    isOk = false;
                    break;
                }
            }
            if (isOk) {
                res.add(i);
            }
        }
        return res;
    }

}
