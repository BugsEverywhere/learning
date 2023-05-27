package indi.simon.learning.leetcode.gogo20230515;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1072 {

    public static void main(String[] args) {
        Quiz1072 quiz1072 = new Quiz1072();
        int res = quiz1072.maxEqualRowsAfterFlips(new int[][]{
                {1, 1},
                {0, 1},
                {1, 0},
                {0, 0},
                {1, 0},
                {1, 0},
                {0, 1},
                {1, 0},
                {0, 0},
                {1, 0}
        });
        System.out.println(res);
    }

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        if (matrix[0].length == 1) {
            return matrix.length;
        }

        //用于记录与key行"成对"的所有行，所谓成对就是与key行所有列都相反，那么就成对
        Map<Integer, Set<Integer>> rowNeqPairMap = new HashMap<>();
        //用于记录与key行"成对"的所有行，所谓成对就是与key行所有列都相等，那么就成对
        Map<Integer, Set<Integer>> rowEqPairMap = new HashMap<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (j == i) {
                    continue;
                }
                Set<Integer> eqPairRows = rowEqPairMap.getOrDefault(i, new HashSet<>());
                Set<Integer> neqPairRows = rowNeqPairMap.getOrDefault(i, new HashSet<>());
                int pairNum = isEqPairRow(matrix, i, j);
                if (pairNum == 0) {
                    eqPairRows.add(j);
                } else if (pairNum == 1) {
                    neqPairRows.add(j);
                }
                rowEqPairMap.put(i, eqPairRows);
                rowNeqPairMap.put(i, neqPairRows);
            }
        }

        int maxPairCnt = 0;
        for (int i = 0; i < matrix.length; i++) {
            maxPairCnt = Math.max(rowNeqPairMap.get(i).size() + rowEqPairMap.get(i).size() + 1, maxPairCnt);
        }

        return maxPairCnt;
    }

    private int isEqPairRow(int[][] matrix, int iRow, int jRow) {
        boolean eqMark = matrix[iRow][0] == matrix[jRow][0];
        for (int k = 1; k < matrix[0].length; k++) {
            if (eqMark && matrix[iRow][k] == matrix[jRow][k]) {
                continue;
            } else if (!eqMark && matrix[iRow][k] != matrix[jRow][k]) {
                continue;
            } else {
                return -1;
            }
        }
        //两行所有列的值都相反，他俩成对
        return eqMark ? 0 : 1;
    }


    //todo: 官方哈希
    public int maxEqualRowsAfterFlipsOfficial(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            char[] arr = new char[n];
            Arrays.fill(arr, '0');
            for (int j = 0; j < n; j++) {
                // 如果 matrix[i][0] 为 1，则对该行元素进行翻转
                arr[j] = (char) ('0' + (matrix[i][j] ^ matrix[i][0]));
            }
            String s = new String(arr);
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        int res = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            res = Math.max(res, entry.getValue());
        }
        return res;
    }


}
