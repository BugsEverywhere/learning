package indi.simon.learning.leetcode.gogo20230213;


/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1139 {

    public static void main(String[] args) {
        Quiz1139 quiz1139 = new Quiz1139();
        int maxLength = quiz1139.largest1BorderedSquare(new int[][]{{1,1,0,0}});
        System.out.println(maxLength);
    }

    public int largest1BorderedSquare(int[][] grid) {
        int maxLength = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                maxLength = Math.max(maxLength, dfs(grid, i, j, 1, 1, 1));
            }
        }
        return maxLength * maxLength;
    }

    private int dfs(int[][] grid, int i, int j, int length, int dir, int step) {
        if (i >= grid.length || i < 0 || j > grid[0].length || j < 0) {
            return 1;
        }
        if (grid[i][j] == 0) {
            return 1;
        }

        int maxLength = 1;

        if (dir == 1) {
            //仍然在第一行往右走的过程
            if (j + 1 < grid[0].length && grid[i][j + 1] == 1) {
                //继续向右探索
                maxLength = Math.max(maxLength, dfs(grid, i, j + 1, length + 1, dir, step + 1));
            }
            if (i + 1 < grid.length && grid[i + 1][j] == 1) {
                //向下探索，同时锚定length
                maxLength = Math.max(maxLength, dfs(grid, i + 1, j, length, dir + 1, 2));
            }
        } else {
            if (step == length) {
                //需要拐弯了
                switch (dir) {
                    case 2:
                        if (grid[i][j - 1] == 0) {
                            return 1;
                        } else {
                            maxLength = Math.max(maxLength, dfs(grid, i, j - 1, length, dir + 1, 2));
                        }
                        break;
                    case 3:
                        if (grid[i - 1][j] == 0) {
                            return 1;
                        } else {
                            maxLength = Math.max(maxLength, dfs(grid, i - 1, j, length, dir + 1, 2));
                        }
                        break;
                    case 4:
                        return length;
                    default:
                        return 1;
                }

            } else {
                switch (dir) {
                    case 2:
                        if (i + 1 < grid.length && grid[i + 1][j] == 1) {
                            maxLength = Math.max(maxLength, dfs(grid, i + 1, j, length, dir, step + 1));
                        }
                        break;
                    case 3:
                        if (grid[i][j - 1] == 1) {
                            maxLength = Math.max(maxLength, dfs(grid, i, j - 1, length, dir, step + 1));
                        }
                        break;
                    case 4:
                        if (grid[i - 1][j] == 1) {
                            maxLength = Math.max(maxLength, dfs(grid, i - 1, j, length, dir, step + 1));
                        }
                        break;
                    default:
                        return 1;
                }

            }
        }
        return maxLength;
    }


}
