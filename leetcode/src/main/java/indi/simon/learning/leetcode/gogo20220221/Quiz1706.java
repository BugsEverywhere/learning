package indi.simon.learning.leetcode.gogo20220221;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1706 {

    public static void main(String[] args) {
        int[][] grid = new int[][]{{-1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, 1, -1, -1, 1, 1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, 1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, 1, -1, 1, -1, -1, 1, 1, -1, 1, -1, -1, -1, -1, 1, 1, 1, 1, 1, 1, -1, 1, 1, 1, -1, 1, 1, 1, -1, -1, -1, 1, -1, 1, -1, -1, 1, 1, -1, -1, 1, -1, 1, -1, 1, 1, 1, -1, -1, -1, -1}};
        Quiz1706 quiz1706 = new Quiz1706();
        int[] res = quiz1706.findBall(grid);
        System.out.println(res);
    }

    public int[] findBall(int[][] grid) {
        int[] arr = new int[grid[0].length];
        for (int j = 0; j < grid[0].length; j++) {
            arr[j] = findBallInternal(grid, 0, j);
        }
        return arr;
    }

    private int findBallInternal(int[][] grid, int currentRow, int currentColumn) {
        if (currentRow >= grid.length - 1) {
            if ((currentColumn == 0 && grid[currentRow][currentColumn] == -1) || (currentColumn == grid[0].length - 1 && grid[currentRow][currentColumn] == 1)) {
                return -1;
            } else {
                if (grid[currentRow][currentColumn] == 1 && grid[currentRow][currentColumn + 1] == 1) {
                    return currentColumn + 1;
                } else if (grid[currentRow][currentColumn] == 1 && grid[currentRow][currentColumn + 1] == -1) {
                    return -1;
                } else if (grid[currentRow][currentColumn] == -1 && grid[currentRow][currentColumn - 1] == -1) {
                    return currentColumn - 1;
                } else if (grid[currentRow][currentColumn] == -1 && grid[currentRow][currentColumn - 1] == 1) {
                    return -1;
                }
            }
        }
        if (currentColumn >= grid[0].length - 1) {
            //已经到该行最右侧单元格
            if (grid[currentRow][currentColumn] == 1) {
                //碰壁，没戏了
                return -1;
            } else if (grid[currentRow][currentColumn] == -1 && grid[currentRow][currentColumn - 1] == 1) {
                return -1;
            } else {
                return findBallInternal(grid, currentRow + 1, currentColumn - 1);
            }
        } else if (currentColumn <= 0) {
            //已经到该行最左侧单元格
            if (grid[currentRow][currentColumn] == -1) {
                //碰壁，没戏了
                return -1;
            } else if (grid[currentRow][currentColumn] == 1 && grid[currentRow][currentColumn + 1] == -1) {
                return -1;
            } else {
                return findBallInternal(grid, currentRow + 1, currentColumn + 1);
            }
        } else {
            //中间单元格
            if (grid[currentRow][currentColumn] == 1 && grid[currentRow][currentColumn + 1] == 1) {
                return findBallInternal(grid, currentRow + 1, currentColumn + 1);
            } else if (grid[currentRow][currentColumn] == 1 && grid[currentRow][currentColumn + 1] == -1) {
                return -1;
            } else if (grid[currentRow][currentColumn] == -1 && grid[currentRow][currentColumn - 1] == -1) {
                return findBallInternal(grid, currentRow + 1, currentColumn - 1);
            } else if (grid[currentRow][currentColumn] == -1 && grid[currentRow][currentColumn - 1] == 1) {
                return -1;
            }
        }
        return -1;
    }

}

//todo : 情况考虑不周全导致错了三次，艹
