package indi.simon.learning.leetcode.gogo20220328;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz59 {

    public static void main(String[] args) {
        Quiz59 quiz59 = new Quiz59();
        int[][] res = quiz59.generateMatrix(1);
        System.out.println(res);
    }

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int currentRow = 0;
        int currentColumn = 0;
        int currentNum = 1;
        int direction = 1;
        while (currentNum <= n * n) {
            if (direction == 1) {
                //往左走
                if (currentColumn < n && res[currentRow][currentColumn] == 0) {
                    res[currentRow][currentColumn] = currentNum;
                    currentNum++;
                    currentColumn++;
                } else {
                    //转向，还原
                    direction = 2;
                    currentColumn--;
                    currentRow++;
                }
            } else if (direction == 2) {
                //往下走
                if (currentRow < n && res[currentRow][currentColumn] == 0) {
                    res[currentRow][currentColumn] = currentNum;
                    currentNum++;
                    currentRow++;
                } else {
                    //转向，还原
                    direction = 3;
                    currentRow--;
                    currentColumn--;
                }
            } else if (direction == 3) {
                //往右走
                if (currentColumn >= 0 && res[currentRow][currentColumn] == 0) {
                    res[currentRow][currentColumn] = currentNum;
                    currentNum++;
                    currentColumn--;
                } else {
                    //转向
                    direction = 4;
                    currentColumn++;
                    currentRow--;
                }
            } else if (direction == 4) {
                //往上走
                if (currentRow >= 0 && res[currentRow][currentColumn] == 0) {
                    res[currentRow][currentColumn] = currentNum;
                    currentNum++;
                    currentRow--;
                } else {
                    //转向
                    direction = 1;
                    currentRow++;
                    currentColumn++;
                }
            }
        }

        return res;
    }

}
