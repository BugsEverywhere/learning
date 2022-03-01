package indi.simon.learning.leetcode.gogo20220221;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1020 {

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0, 0, 0, 1, 1, 1, 0, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 0, 1, 1, 1}, {0, 0, 0, 1, 1, 1, 0, 1, 0, 0}, {0, 1, 1, 0, 0, 0, 1, 0, 1, 0}, {0, 1, 1, 1, 1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 1, 1, 1, 0, 1}, {0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 1, 0, 0, 1, 0, 1, 0, 1}, {1, 0, 1, 0, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0, 0, 1}};
//        int[][] grid = new int[][]{{0, 1, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}};
        Quiz1020 quiz1020 = new Quiz1020();
        int res = quiz1020.numEnclaves(grid);
        System.out.println(res);
    }

    Boolean[][] totalMem = null;

    public int numEnclaves(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        totalMem = new Boolean[grid.length][grid[0].length];
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                if (!numEnclavesInternal(grid, i, j, new boolean[grid.length][grid[0].length])) {
                    sum++;
                } else {
                    totalMem[i][j] = true;
                    //todo: 备忘录在递归方法外面赋值，防止在一次递归中影响
                }
            }
        }
        return sum;
    }

    private boolean numEnclavesInternal(int[][] grid, int currentRow, int currentColumn, boolean[][] beenHereBefore) {
        //todo: 像这种有路径的递归，一定要加上路径记事本，就是此处的beenHereBefore
        if (beenHereBefore[currentRow][currentColumn]) {
            return false;
        }

        if ((currentRow == 0 || currentRow == grid.length - 1 || currentColumn == 0 || currentColumn == grid[0].length - 1) && grid[currentRow][currentColumn] == 1) {
            return true;
        } else if (currentRow == 0 || currentRow == grid.length - 1 || currentColumn == 0 || currentColumn == grid[0].length - 1) {
            return false;
        }

        //备忘录看一下
        if (totalMem[currentRow][currentColumn] != null) {
            return totalMem[currentRow][currentColumn];
        }

        beenHereBefore[currentRow][currentColumn] = true;

        boolean below = false;
        if (grid[currentRow + 1][currentColumn] == 1) {
            below = numEnclavesInternal(grid, currentRow + 1, currentColumn, beenHereBefore);
        }

        boolean up = false;
        if (grid[currentRow - 1][currentColumn] == 1) {
            up = numEnclavesInternal(grid, currentRow - 1, currentColumn, beenHereBefore);
        }

        boolean left = false;
        if (grid[currentRow][currentColumn - 1] == 1) {
            left = numEnclavesInternal(grid, currentRow, currentColumn - 1, beenHereBefore);
        }

        boolean right = false;
        if (grid[currentRow][currentColumn + 1] == 1) {
            right = numEnclavesInternal(grid, currentRow, currentColumn + 1, beenHereBefore);
        }

        return below || up || left || right;

    }

}
