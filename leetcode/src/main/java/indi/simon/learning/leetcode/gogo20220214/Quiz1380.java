package indi.simon.learning.leetcode.gogo20220214;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1380 {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 10, 4, 2}, {9, 3, 8, 7}, {15, 16, 17, 12}};
        Quiz1380 quiz1380 = new Quiz1380();
        List<Integer> res = quiz1380.luckyNumbers(matrix);
        System.out.println(res);
    }

    public List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        Map<Integer, Integer> rowMinMap = new HashMap<>();
        Map<Integer, Integer> columnMaxMap = new HashMap<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (rowMinMap.get(i) == null) {
                    rowMinMap.put(i, j);
                } else if (rowMinMap.get(i) != null && matrix[i][j] < matrix[i][rowMinMap.get(i)]) {
                    //todo:此处一开始错是写成了matrix[i][j] < rowMinMap.get(i)，数组的下标和元素没区分好，粗心大意，下面的列比较同理
                    rowMinMap.put(i, j);
                }

                if (columnMaxMap.get(j) == null) {
                    columnMaxMap.put(j, i);
                } else if (columnMaxMap.get(j) != null && matrix[i][j] > matrix[columnMaxMap.get(j)][j]) {
                    columnMaxMap.put(j, i);
                }
            }
        }

        rowMinMap.forEach((key, value) -> {
            if (columnMaxMap.get(value) != null && columnMaxMap.get(value).equals(key)) {
                result.add(matrix[key][value]);
            }
        });

        return result;
    }


}
