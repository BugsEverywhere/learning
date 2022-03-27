package indi.simon.learning.leetcode.gogo20220321;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz682 {

    public static void main(String[] args) {
        String[] str = new String[]{"5", "2", "C", "D", "+"};
        Quiz682 quiz682 = new Quiz682();
        int res = quiz682.calPoints(str);
        System.out.println(res);
    }

    public int calPoints(String[] ops) {
        List<Integer> scoreArr = new ArrayList<>();
        for (String singleStr : ops) {
            if ("+".equals(singleStr)) {
                scoreArr.add(scoreArr.get(scoreArr.size() - 1) + scoreArr.get(scoreArr.size() - 2));
            } else if ("D".equals(singleStr)) {
                scoreArr.add(scoreArr.get(scoreArr.size() - 1) * 2);
            } else if ("C".equals(singleStr)) {
                scoreArr.remove(scoreArr.size() - 1);
            } else {
                scoreArr.add(Integer.parseInt(singleStr));
            }
        }

        int sum = 0;
        for (Integer singleScore : scoreArr) {
            if (singleScore == null) {
                continue;
            }
            sum = sum + singleScore;
        }
        return sum;
    }
}
