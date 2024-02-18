package indi.simon.learning.leetcode.gogo20240212;

/**
 * Created by Chen Zhuo on 2024/2/18.
 */
public class Quiz289 {

    public void gameOfLife(int[][] board) {
        boolean[][] turnBoard = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                turnBoard[i][j] = turn(board, i, j);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (turnBoard[i][j]) {
                    board[i][j] = (board[i][j] == 1) ? 0 : 1;
                }
            }
        }
    }

    private boolean turn(int[][] board, int i, int j) {
        int[] xDir = new int[] { -1, 0, 1 };
        int[] yDir = new int[] { -1, 0, 1 };

        int liveNeighborsCnt = 0;

        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++) {
                int newI = i + xDir[m];
                int newJ = j + yDir[n];
                if (newI == i && newJ == j) {
                    continue;
                }
                if (newI < 0 || newI >= board.length || newJ < 0 || newJ >= board[0].length) {
                    continue;
                }
                if (board[newI][newJ] == 1) {
                    liveNeighborsCnt++;
                }
            }
        }

        if (board[i][j] == 1) {
            if (liveNeighborsCnt < 2 || liveNeighborsCnt > 3) {
                return true;
            } else {
                return false;
            }
        } else {
            if (liveNeighborsCnt == 3) {
                return true;
            } else {
                return false;
            }
        }
    }
}
