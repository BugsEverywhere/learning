package indi.simon.learning.leetcode.may2020.week4to10;

public class P348 {

    public static void main(String[] args) {

    }


    private static class TicTacToe {

        private int size;
        private int[] player1RowCount;
        private int[] player1ColumnCount;
        private int[] player2RowCount;
        private int[] player2ColumnCount;

        private int player1DiagonalPos = 0;
        private int player1DiagonalNeg = 0;

        private int player2DiagonalPos = 0;
        private int player2DiagonalNeg = 0;

        public TicTacToe(int n) {
            this.size = n;
            player1RowCount = new int[n];
            player1ColumnCount = new int[n];
            player2RowCount = new int[n];
            player2ColumnCount = new int[n];
        }

        private int move(int row, int col, int player) {

            if (player == 1) {
                //todo 是否在对角线上，是否在反对角线上，是否在某一行某一列上，这几种情况不互斥！！！不应该是if else的关系
                if (row == col) {
                    player1DiagonalPos++;
                    if (player1DiagonalPos == size) {
                        return player;
                    }
                }
                if (row == size - 1 - col) {
                    player1DiagonalNeg++;
                    if (player1DiagonalNeg == size) {
                        return player;
                    }
                }
                player1RowCount[row]++;
                if (player1RowCount[row] == size) {
                    return player;
                }
                player1ColumnCount[col]++;
                if (player1ColumnCount[col] == size) {
                    return player;
                }

            } else {
                if (row == col) {
                    player2DiagonalPos++;
                    if (player2DiagonalPos == size) {
                        return player;
                    }
                }
                if (row == size - 1 - col) {
                    player2DiagonalNeg++;
                    if (player2DiagonalNeg == size) {
                        return player;
                    }
                }
                player2RowCount[row]++;
                if (player2RowCount[row] == size) {
                    return player;
                }
                player2ColumnCount[col]++;
                if (player2ColumnCount[col] == size) {
                    return player;
                }

            }
            return 0;
        }


    }


}
