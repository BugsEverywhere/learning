package indi.simon.learning.leetcode.gogo20240219;

/**
 * Created by Chen Zhuo on 2024/2/24.
 */
public class Quiz498 {

    //如果m!=n，也视为m==n处理，只不过越界时不填充值即可
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int k = m + n - 1;

        int[] res = new int[m * n];

        int index = 0;
        for (int i = 0; i < k; i++) {
            if (i % 2 == 0) {
                // goes up
                int r = i;
                int c = 0;
                while (r >= 0) {
                    if (c >= 0 && c < mat[0].length && r < mat.length && r >= 0) {
                        res[index] = mat[r][c];
                        index++;
                    }
                    c++;
                    r--;
                }
            } else {
                // goes down
                int r = 0;
                int c = i;
                while (c >= 0) {
                    if (c >= 0 && c < mat[0].length && r < mat.length && r >= 0) {
                        res[index] = mat[r][c];
                        index++;
                    }
                    c--;
                    r++;
                }
            }
        }
        return res;
    }


}
