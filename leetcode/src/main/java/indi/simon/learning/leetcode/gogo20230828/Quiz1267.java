package indi.simon.learning.leetcode.gogo20230828;

/**
 * Created by Chen Zhuo on 2023/9/3.
 */
public class Quiz1267 {

    public static void main(String[] args) {
        Quiz1267 quiz1267 = new Quiz1267();
        int res = quiz1267.countServers(new int[][]{
                {1, 0},
                {0, 1}
        });

        System.out.println(res);
    }

    public int countServers(int[][] grid) {

        int totalCnt = 0;
        //遍历所有列
        for (int j = 0; j < grid[0].length; j++) {
            int colCnt = 0;
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][j] == 1) {
                    colCnt++;
                }
            }
            if (colCnt >= 2) {
                totalCnt += colCnt;
                for (int i = 0; i < grid.length; i++) {
                    if (grid[i][j] == 1) {
                        grid[i][j] = 2;
                    }
                }
            }
        }

        //遍历所有行
        for (int i = 0; i < grid.length; i++) {
            int rowCnt = 0;
            int alreadyCnt = 0;
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    rowCnt++;
                } else if (grid[i][j] == 2) {
                    rowCnt++;
                    alreadyCnt++;
                }
            }
            if(rowCnt >= 2){
                totalCnt += rowCnt - alreadyCnt;
            }
        }


        return totalCnt;
    }

}
