package indi.simon.learning.leetcode.gogo20220704;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 贪心的方式碰到下面这样的局就玩不转了，去程贪吃尽可能多吃，回程无法吃掉所有的樱桃。如果去程能主动舍弃眼前利益，绕远路摘孤立的樱桃，回程就能通吃
public class Quiz741_notfinish {

    public static void main(String[] args) {
        Quiz741_notfinish quiz741Notfinish = new Quiz741_notfinish();
        int[][] grid = new int[][]{
                {1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 1},
                {1, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1}
        };
        int res = quiz741Notfinish.cherryPickup(grid);
        System.out.println(res);
    }

    public int cherryPickup(int[][] grid) {

        int[][] dp = new int[grid.length][grid[0].length];
        Position[][] path = new Position[grid.length][grid[0].length];
        path[0][0] = new Position(-1, -1);

        //首先是正向走
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == -1) {
                    //荆棘，直接下一个
                    dp[i][j] = -1;
                    continue;
                }
                if (i == 0) {
                    if (j == 0) {
                        //起始位置
                        dp[i][j] = grid[i][j];
                    } else {
                        if (dp[i][j - 1] >= 0) {
                            //可行路径
                            dp[i][j] = dp[i][j - 1] + grid[i][j];
                            path[i][j] = new Position(i, j - 1);
                        } else {
                            //荆棘继承
                            dp[i][j] = dp[i][j - 1];
                        }
                    }
                } else {
                    if (j == 0) {
                        if (dp[i - 1][j] == -1) {
                            dp[i][j] = -1;
                        } else {
                            dp[i][j] = dp[i - 1][j] + grid[i][j];
                            path[i][j] = new Position(i - 1, j);
                        }
                    } else {
                        if (dp[i - 1][j] >= 0 || dp[i][j - 1] >= 0) {
                            //上一格或者前一格是可通行路径，取其中的大者与本格相加
                            if (dp[i - 1][j] >= dp[i][j - 1]) {
                                dp[i][j] = grid[i][j] + dp[i - 1][j];
                                path[i][j] = new Position(i - 1, j);
                            } else {
                                dp[i][j] = grid[i][j] + dp[i][j - 1];
                                path[i][j] = new Position(i, j - 1);
                            }
                        } else {
                            //本格不可达
                            dp[i][j] = -1;
                        }
                    }
                }
            }
        }

        if (dp[grid.length - 1][grid[0].length - 1] < 0) {
            return 0;
        }

        //根据所记录的路径清除来时的樱桃
        int row = dp.length - 1;
        int column = dp[0].length - 1;
        while (row >= 0 && column >= 0) {
            grid[row][column] = 0;
            int newRow = path[row][column].getRow();
            int newColumn = path[row][column].getColumn();
            row = newRow;
            column = newColumn;
        }

        //回程
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (grid[i][j] == -1) {
                    //荆棘，直接跳过
                    continue;
                }
                if (i == grid.length - 1) {
                    if (j == grid[0].length - 1) {
                        //终点
                        dp[i][j] = dp[i][j];
                    } else {
                        if (dp[i][j + 1] >= 0) {
                            //可行路径
                            dp[i][j] = dp[i][j + 1] + grid[i][j];
                        } else {
                            dp[i][j] = -1;
                        }
                    }
                } else {
                    if (j == grid[0].length - 1) {
                        if (dp[i + 1][j] >= 0) {
                            dp[i][j] = dp[i + 1][j] + grid[i][j];
                        } else {
                            dp[i][j] = -1;
                        }
                    } else {
                        if (dp[i + 1][j] >= 0 || dp[i][j + 1] >= 0) {
                            dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]) + grid[i][j];
                        } else {
                            dp[i][j] = -1;
                        }
                    }
                }
            }
        }

        return dp[0][0];
    }

    private class Position {

        private int row;
        private int column;

        public Position(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }
    }
}
