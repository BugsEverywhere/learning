package indi.simon.learning.leetcode.gogo20230807;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz807 {

    public static void main(String[] args) {

    }

    public int maxIncreaseKeepingSkyline(int[][] grid) {

        int[] rowMax = new int[grid.length];
        int[] colMax = new int[grid.length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                rowMax[i] = Math.max(rowMax[i], grid[i][j]);
                colMax[j] = Math.max(colMax[j], grid[i][j]);
            }
        }

        int increment = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                int ceiling = Math.min(rowMax[i], colMax[j]);
                increment += ceiling - grid[i][j];
            }
        }

        return increment;
    }

}
