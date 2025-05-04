package indi.simon.learning;

import java.util.*;

public class TicTacToe {
    // 定义棋盘大小为3x3
    private static final int BOARD_SIZE = 3;
    // 定义空格字符
    private static final char EMPTY = ' ';
    // 定义玩家X的符号
    private static final char PLAYER_X = 'X';
    // 定义玩家O的符号
    private static final char PLAYER_O = 'O';
    // 定义学习率（控制更新Q表时的步长）
    private static final double LEARNING_RATE = 0.1;
    // 定义折扣因子（对未来奖励的权重）
    private static final double DISCOUNT_FACTOR = 0.9;
    // 定义探索率（控制随机选择动作的概率）
    private static final double EXPLORATION_RATE = 0.1;

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

    // Q-learning智能体类
    public static class QLearningAgent {
        // Q表，存储状态-动作值对
        private Map<String, double[]> qTable;
        // 智能体的玩家符号
        private char player;

        // 构造函数，初始化智能体的玩家符号和Q表
        public QLearningAgent(char player) {
            this.player = player; // 设置玩家符号
            this.qTable = new HashMap<>(); // 初始化Q表
        }

        // 更新Q表中的值
        public void updateQTable(String state, int[] action, double reward, String nextState) {
            // 获取当前状态的Q值数组
            double[] qValues = qTable.getOrDefault(state, new double[9]);
            // 获取下一个状态的Q值数组
            double[] nextQValues = qTable.getOrDefault(nextState, new double[9]);

            // 计算动作索引
            int actionIndex = action[0] * BOARD_SIZE + action[1];
            // 计算下一个状态的最大Q值
            double maxNextQValue = Arrays.stream(nextQValues).max().orElse(0.0);

            // 更新当前状态的动作值
            qValues[actionIndex] += LEARNING_RATE * (reward + DISCOUNT_FACTOR * maxNextQValue - qValues[actionIndex]);
            // 将更新后的Q值存入Q表
            qTable.put(state, qValues);
        }

        // 根据当前状态选择动作（探索或利用）
        public int[] getAction(String state, boolean explore, TicTacToe game) {
            // 获取当前状态的Q值数组
            double[] qValues = qTable.getOrDefault(state, new double[9]);
            // 获取当前状态的所有可用动作
            List<int[]> availableMoves = game.getAvailableMoves();

            // 如果探索且随机数小于探索率，则随机选择动作
            if (explore && Math.random() < EXPLORATION_RATE) {
                return availableMoves.get(new Random().nextInt(availableMoves.size()));
            } else {
                // 否则选择Q值最大的动作
                int maxIndex = Integer.MAX_VALUE;
                int maxQval = Integer.MIN_VALUE;
                //如果Q值最大的动作有多个，则选择第一个
                for (int i = 0; i < 9; i++) {
                    if (!game.isAvailable(i / BOARD_SIZE, i % BOARD_SIZE)) {
                        continue;
                    }
                    if (qValues[i] > maxQval) {
                        maxQval = (int) qValues[i];
                        maxIndex = i; // 更新最大Q值索引
                    }
                }
                int i = maxIndex / BOARD_SIZE;
                int j = maxIndex % BOARD_SIZE;
                return new int[]{i, j}; // 返回对应的动作
            }
        }

        // 重置Q表
        public void reset() {
            qTable.clear(); // 清空Q表
        }
    }

    // 主方法，用于训练和测试两个智能体
    public static void main(String[] args) {
        // 创建游戏实例
        TicTacToe game = new TicTacToe();
        // 创建两个智能体，分别对应玩家X和玩家O
        QLearningAgent agentX = new QLearningAgent(PLAYER_X);
        QLearningAgent agentO = new QLearningAgent(PLAYER_O);

        // 训练回合数
        int episodes = 10000;
        for (int episode = 0; episode < episodes; episode++) {
            // 每回合重新初始化游戏
            game = new TicTacToe();
            while (!game.isGameOver()) {
                // 获取当前状态
                String state = Arrays.deepToString(game.board);
                // 获取当前玩家的智能体
                QLearningAgent currentAgent = (game.currentPlayer == PLAYER_X) ? agentX : agentO;
                // 根据当前状态选择动作（训练时允许探索）
                int[] action = currentAgent.getAction(state, true, game);

                // 执行动作
                game.makeMove(action[0], action[1]);
                // 获取下一个状态
                String nextState = Arrays.deepToString(game.board);

                // 如果游戏结束，计算奖励并更新Q表
                if (game.isGameOver()) {
                    char winner = game.getWinner();
                    double reward;
                    if(winner == currentAgent.player){
                        //胜利则奖励1
                        reward = 1;
                    } else if(winner == EMPTY){
                        //平局则奖励0.5
                        reward = 0.5;
                    } else {
                        //输了则奖励-1
                        reward = -1;
                    }
                    currentAgent.updateQTable(state, action, reward, nextState);
                } else {
                    // 如果游戏未结束，更新Q表但奖励为0
                    currentAgent.updateQTable(state, action, 0.0, nextState);
                }
            }
        }

        // 使用训练好的智能体进行一局游戏
        game = new TicTacToe();
        while (!game.isGameOver()) {
            // 获取当前状态
            String state = Arrays.deepToString(game.board);
            // 获取当前玩家的智能体
            QLearningAgent currentAgent = (game.currentPlayer == PLAYER_X) ? agentX : agentO;
            // 根据当前状态选择动作（测试时不探索）
            int[] action = currentAgent.getAction(state, false, game);
            // 执行动作
            game.makeMove(action[0], action[1]);
            // 打印当前棋盘状态
            game.printBoard();
        }

        // 获取获胜玩家
        char winner = game.getWinner();
        if (winner != EMPTY) {
            System.out.println("Winner: " + winner); // 输出获胜玩家
        } else {
            System.out.println("Draw!"); // 输出平局
        }
    }
}