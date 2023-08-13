package indi.simon.learning.leetcode.gogo20230807;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz529 {

    public static void main(String[] args) {
        Quiz529 quiz529 = new Quiz529();
        char[][] res = quiz529.updateBoard(new char[][]{
                {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'M'},
                {'E', 'E', 'M', 'E', 'E', 'E', 'E', 'E'},
                {'M', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'M', 'E', 'E', 'E', 'E'}
        }, new int[]{0, 0});
        System.out.println(res);
    }

    public char[][] updateBoard(char[][] board, int[] click) {

        switch (board[click[0]][click[1]]) {
            case 'M':
                board[click[0]][click[1]] = 'X';
                break;
            case 'E':
                updateBoardInternal(board, click[0], click[1]);
                break;
            default:
                break;
        }
        return board;
    }


    private void updateBoardInternal(char[][] board, int rowIndex, int colIndex) {
        int[] dirR = new int[]{-1, 0, 1};
        int[] dirC = new int[]{1, 0, -1};

        int bombCnt = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }
                int newRowIndex = rowIndex + dirR[i];
                int newColIndex = colIndex + dirC[j];
                //todo: 使用下标偏移量数组一定要注意边界判断不能搞错
                if (newRowIndex < 0 || newColIndex < 0 || newRowIndex >= board.length || newColIndex >= board[0].length) {
                    continue;
                }
                if (board[newRowIndex][newColIndex] == 'M' || board[newRowIndex][newColIndex] == 'X') {
                    bombCnt++;
                }
            }
        }

        if (bombCnt == 0) {
            board[rowIndex][colIndex] = 'B';
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 1 && j == 1) {
                        continue;
                    }
                    int newRowIndex = rowIndex + dirR[i];
                    int newColIndex = colIndex + dirC[j];
                    if (newRowIndex < 0 || newColIndex < 0 || newRowIndex >= board.length || newColIndex >= board[0].length) {
                        continue;
                    }
                    if (board[newRowIndex][newColIndex] == 'E') {
                        updateBoardInternal(board, newRowIndex, newColIndex);
                    }
                }
            }
        } else {
            board[rowIndex][colIndex] = (char) ('0' + bombCnt);
        }
    }


}
