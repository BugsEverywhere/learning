package indi.simon.learning.leetcode.gogo20220711;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1252 {

    public static void main(String[] args) {
        int[][] indices = new int[][]{};


    }

    public int oddCells(int m, int n, int[][] indices) {
        boolean[] rowMem = new boolean[m];
        boolean[] columnMem = new boolean[n];

        for (int i = 0; i < indices.length; i++) {
            rowMem[indices[i][0]] = !rowMem[indices[i][0]];
            columnMem[indices[i][1]] = !columnMem[indices[i][1]];
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((!rowMem[i] && columnMem[j]) || (rowMem[i] && !columnMem[j])) {
                    count++;
                }
            }
        }

        return count;
    }
}
