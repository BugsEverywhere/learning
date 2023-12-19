package indi.simon.learning.leetcode.gogo20231218;

/**
 * Created by Chen Zhuo on 2023/12/19.
 */
public class Quiz1901 {

    public static void main(String[] args) {
        Quiz1901 quiz1901 = new Quiz1901();
        int[] res = quiz1901.findPeakGrid(new int[][]{});

    }

    private int[] res;
    private int[] yDir;
    private int[] xDir;

    public int[] findPeakGrid(int[][] mat) {
        yDir = new int[]{-1, 1, 0, 0};
        xDir = new int[]{0, 0, -1, 1};
        dfs(mat,0,0,-1,-1);
        return res;
    }

    private void dfs(int[][] mat, int i, int j, int lastI, int lastJ) {
        if(res != null){
            return;
        }
        int cnt = 0;
        for (int k = 0; k < 4; k++) {
            if (i + yDir[k] == lastI && j + xDir[k] == lastJ) {
                if (mat[lastI][lastJ] < mat[i][j]) {
                    cnt++;
                }
                continue;
            }
            if (i + yDir[k] < 0 || i + yDir[k] >= mat.length || j + xDir[k] < 0 || j + xDir[k] >= mat[0].length) {
                cnt++;
            } else if (mat[i + yDir[k]][j + xDir[k]] >= mat[i][j]) {
                dfs(mat, i + yDir[k], j + xDir[k], i, j);
            } else if (mat[i + yDir[k]][j + xDir[k]] < mat[i][j]) {
                cnt++;
            }
        }
        if (cnt == 4) {
            res = new int[]{i, j};
        }
    }

}
