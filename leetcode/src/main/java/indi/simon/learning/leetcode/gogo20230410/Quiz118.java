package indi.simon.learning.leetcode.gogo20230410;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz118 {

    public static void main(String[] args) {
        Quiz118 quiz118 = new Quiz118();
        List<List<Integer>> res = quiz118.generate(5);
        System.out.println(res);
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> rowOne = new ArrayList<>();
        rowOne.add(1);
        res.add(rowOne);

        for (int i = 2; i <= numRows; i++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> lastRow = res.get(res.size() - 1);
            for (int j = 0; j < lastRow.size() + 1; j++) {
                if (j == 0) {
                    row.add(lastRow.get(j));
                } else if (j == lastRow.size()) {
                    row.add(lastRow.get(j - 1));
                } else {
                    row.add(lastRow.get(j - 1) + lastRow.get(j));
                }
            }
            res.add(row);
        }

        return res;
    }

}
