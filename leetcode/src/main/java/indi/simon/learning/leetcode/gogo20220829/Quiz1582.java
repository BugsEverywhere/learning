package indi.simon.learning.leetcode.gogo20220829;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1582 {

    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {0, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 0}};
        Quiz1582 quiz1582 = new Quiz1582();
        int res = quiz1582.numSpecial(mat);
        System.out.println(res);
    }

    public int numSpecial(int[][] mat) {
        boolean[] excludedColumns = new boolean[mat[0].length];
        boolean[] excludedRows = new boolean[mat.length];
        int res = 0;

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    continue;
                }
                //todo: 此处不应该想着偷懒，只要是1，就不应该continue，要确保 excludedRows[i] 和 excludedColumns[j] 都要被更新到
                if (!excludedColumns[j] && !excludedRows[i] && searchRow(mat, i, j) && searchColumn(mat, i, j)) {
                    res++;
                }
                excludedRows[i] = true;
                excludedColumns[j] = true;
            }
        }

        return res;
    }

    private boolean searchRow(int[][] mat, int row, int column) {
        for (int j = column + 1; j < mat[0].length; j++) {
            if (mat[row][j] == 1) {
                return false;
            }
        }
        return true;
    }

    private boolean searchColumn(int[][] mat, int row, int column) {
        for (int i = row + 1; i < mat.length; i++) {
            if (mat[i][column] == 1) {
                return false;
            }
        }
        return true;
    }

}
