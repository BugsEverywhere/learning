package indi.simon.learning.leetcode;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P79GridFindWord {

    public static void main(String[] args) {
        char[][] board = {{'C', 'A', 'A'},
                {'A', 'A', 'A'},
                {'B', 'C', 'D'}};
        boolean result = exist(board, "AAB");
        System.out.println(result);
    }

    public static boolean exist(char[][] board, String word) {
        char[] targetArr = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (targetArr[0] != board[i][j]) {
                    continue;
                }
                boolean[][] cameBefore = new boolean[board.length][board[0].length];
                cameBefore[i][j] = true;
                if (findCharInternal(targetArr, 1, board, cameBefore, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }


    private static boolean findCharInternal(char[] targetArr, int nextIndex, char[][] board, boolean[][] cameBefore, int i, int j) {
        if (nextIndex >= targetArr.length) {
            return true;
        }
        if (i > 0 && board[i - 1][j] == targetArr[nextIndex] && !cameBefore[i - 1][j]) {
            cameBefore[i - 1][j] = true;
            if (findCharInternal(targetArr, nextIndex + 1, board, cameBefore, i - 1, j)) {
                return true;
            }
            cameBefore[i - 1][j] = false;
        }
        if (i < board.length - 1 && board[i + 1][j] == targetArr[nextIndex] && !cameBefore[i + 1][j]) {
            cameBefore[i + 1][j] = true;
            if (findCharInternal(targetArr, nextIndex + 1, board, cameBefore, i + 1, j)) {
                return true;
            }
            cameBefore[i + 1][j] = false;
        }
        if (j < board[0].length - 1 && board[i][j + 1] == targetArr[nextIndex] && !cameBefore[i][j + 1]) {
            cameBefore[i][j + 1] = true;
            if (findCharInternal(targetArr, nextIndex + 1, board, cameBefore, i, j + 1)) {
                return true;
            }
            cameBefore[i][j + 1] = false;
        }
        if (j > 0 && board[i][j - 1] == targetArr[nextIndex] && !cameBefore[i][j - 1]) {
            cameBefore[i][j - 1] = true;
            if (findCharInternal(targetArr, nextIndex + 1, board, cameBefore, i, j - 1)) {
                return true;
            }
            cameBefore[i][j - 1] = false;
        }
        return false;
    }

}

// todo: 学到一招，回溯的时候，如果涉及到之前走过的路径不能走这样的约束，是可以通过记录原来走过的路径来避免的，
//  但是回溯完某条支线，发现走不通回来的时候，要抹掉刚这个路径！让它重新变为没有走过