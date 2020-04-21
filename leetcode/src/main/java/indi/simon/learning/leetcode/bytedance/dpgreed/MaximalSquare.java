package indi.simon.learning.leetcode.bytedance.dpgreed;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class MaximalSquare {

    private static int maxSquare = 0;

    public static void main(String[] args) {

        char[][] matrix = new char[][]{{'0', '0', '0', '1'}, {'1', '1', '0', '1'}, {'1', '1', '1', '1'}, {'0', '1', '1', '1'}, {'0', '1', '1', '1'}};
        int result = maximalSquare(matrix);
        System.out.println(result);

    }

    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    int p = i;
                    int q = j;
                    while (p < matrix.length && q < matrix[0].length && matrix[p][q] == '1') {
                        p++;
                        q++;
                    }
                    p--;
                    q--;
                    while (!isSquare(i, j, p, q, matrix)) {
                        p--;
                        q--;
                    }
                    int area = (p - i + 1) * (p - i + 1);
                    if (area > maxSquare) {
                        maxSquare = area;
                    }
                }
            }
        }
        return maxSquare;
    }

    private static boolean isSquare(int i, int j, int p, int q, char[][] matrix) {
        for (int x = i; x <= p; x++) {
            for (int y = j; y <= q; y++) {
                if (matrix[x][y] != '1') {
                    return false;
                }
            }
        }
        return true;
    }


}
