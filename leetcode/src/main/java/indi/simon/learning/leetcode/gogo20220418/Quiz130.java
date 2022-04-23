package indi.simon.learning.leetcode.gogo20220418;

/**
 * @author chenzhuo(zhiyue)
 */
//TODO: 需要注意，这种二维表格的深度优先搜索，脚印的处理，使用boolean[][]比较正确，如果单纯判断上一步是否走过，容易stackoverFlow，比如下面全O的场景
// 官方解答给出一种比较简便的思路，那就是在边界上的所有O，以及他们所相连的O都不符合，找出他们，打上标记，然后遍历整个board，除了刚刚标记的O其他全置为X
public class Quiz130 {

    public static void main(String[] args) {
        char[][] board = new char[][]{{'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O'}};
        Quiz130 quiz130 = new Quiz130();
        quiz130.solve(board);
        System.out.println(board);
    }

    public void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    continue;
                }
                boolean isSeiged = isSeiged(board, i, j, new boolean[board.length][board[0].length]);
                if (isSeiged) {
                    solveInternal(board, i, j);
                }
            }
        }
    }

    private boolean isSeiged(char[][] board, int i, int j, boolean[][] footprint) {
        boolean upSeiged = false;
        footprint[i][j] = true;
        if (i - 1 >= 0 && board[i - 1][j] == 'X') {
            upSeiged = true;
        } else if (i - 1 >= 0 && board[i - 1][j] == 'O' && !footprint[i - 1][j]) {
            upSeiged = isSeiged(board, i - 1, j, footprint);
        } else if (i - 1 >= 0 && footprint[i - 1][j]) {
            upSeiged = true;
        }

        boolean downSeiged = false;
        if (i + 1 < board.length && board[i + 1][j] == 'X') {
            downSeiged = true;
        } else if (i + 1 < board.length && board[i + 1][j] == 'O' && !footprint[i + 1][j]) {
            downSeiged = isSeiged(board, i + 1, j, footprint);
        } else if (i + 1 < board.length && footprint[i + 1][j]) {
            downSeiged = true;
        }

        boolean rightSeiged = false;
        if (j + 1 < board[0].length && board[i][j + 1] == 'X') {
            rightSeiged = true;
        } else if (j + 1 < board[0].length && board[i][j + 1] == 'O' && !footprint[i][j + 1]) {
            rightSeiged = isSeiged(board, i, j + 1, footprint);
        } else if (j + 1 < board[0].length && footprint[i][j + 1]) {
            rightSeiged = true;
        }

        boolean leftSeiged = false;
        if (j - 1 >= 0 && board[i][j - 1] == 'X') {
            leftSeiged = true;
        } else if (j - 1 >= 0 && board[i][j - 1] == 'O' && !footprint[i][j - 1]) {
            leftSeiged = isSeiged(board, i, j - 1, footprint);
        } else if (j - 1 >= 0 && footprint[i][j - 1]) {
            leftSeiged = true;
        }

        return upSeiged && downSeiged && rightSeiged && leftSeiged;
    }


    private void solveInternal(char[][] board, int i, int j) {
        board[i][j] = 'X';
        if (i - 1 >= 0 && board[i - 1][j] == 'O') {
            solveInternal(board, i - 1, j);
        }

        if (i + 1 < board.length && board[i + 1][j] == 'O') {
            solveInternal(board, i + 1, j);
        }

        if (j + 1 < board[0].length && board[i][j + 1] == 'O') {
            solveInternal(board, i, j + 1);
        }

        if (j - 1 >= 0 && board[i][j - 1] == 'O') {
            solveInternal(board, i, j - 1);
        }

    }
}
