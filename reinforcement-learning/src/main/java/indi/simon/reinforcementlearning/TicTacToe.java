package indi.simon.reinforcementlearning;

import java.util.ArrayList;
import java.util.List;

import static indi.simon.reinforcementlearning.Constant.*;


public class TicTacToe {

    // 棋盘状态数组
    private char[][] board;
    // 当前玩家符号
    private char currentPlayer;

    // 构造函数，初始化棋盘和当前玩家
    public TicTacToe() {
        // 初始化棋盘为3x3的空格
        board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY; // 将每个位置设置为空格
            }
        }
        currentPlayer = PLAYER_X; // 初始玩家为X
    }

    // 打印当前棋盘状态
    public void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " "); // 打印当前位置的符号
            }
            System.out.println(); // 换行
        }
        System.out.println(); // 额外换行，增加可读性
    }

    // 判断游戏是否结束（有人获胜或棋盘已满）
    public boolean isGameOver() {
        // 如果X或O获胜，或者棋盘已满，则游戏结束
        return isWinner(PLAYER_X) || isWinner(PLAYER_O) || isBoardFull();
    }

    // 获取获胜玩家
    public char getWinner() {
        if (isWinner(PLAYER_X)) {
            return PLAYER_X; // X获胜
        } else if (isWinner(PLAYER_O)) {
            return PLAYER_O; // O获胜
        } else {
            return EMPTY; // 无人获胜
        }
    }

    // 判断指定玩家是否获胜
    private boolean isWinner(char player) {
        // 检查行是否有三连
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true; // 行三连
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true; // 列三连
            }
        }
        // 检查对角线是否有三连
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true; // 主对角线三连
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true; // 副对角线三连
        }
        return false; // 未获胜
    }

    // 判断棋盘是否已满
    private boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return false; // 存在空格，棋盘未满
                }
            }
        }
        return true; // 棋盘已满
    }

    // 执行一步棋（在指定位置放置当前玩家的棋子）
    public void makeMove(int row, int col) {
        if (board[row][col] == EMPTY) {
            board[row][col] = currentPlayer; // 放置当前玩家的棋子
            currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X; // 切换玩家
        } else {
            throw new IllegalArgumentException("Invalid move: position already occupied"); // 抛出异常，位置已被占用
        }
    }

    // 获取当前棋盘上所有可用的落子位置
    public List<int[]> getAvailableMoves() {
        List<int[]> moves = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    moves.add(new int[]{i, j}); // 添加空格位置到可用列表
                }
            }
        }
        return moves; // 返回所有可用位置
    }

    public boolean isAvailable(int i, int j) {
        return board[i][j] == EMPTY;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(char currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

}