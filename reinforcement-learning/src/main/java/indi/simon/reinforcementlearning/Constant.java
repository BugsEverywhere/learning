package indi.simon.reinforcementlearning;

/**
 * Created by Chen Zhuo on 2025/5/4.
 */
public class Constant {

    // 定义棋盘大小为3x3
    public static final int BOARD_SIZE = 3;
    // 定义空格字符
    public static final char EMPTY = ' ';
    // 定义玩家X的符号
    public static final char PLAYER_X = 'X';
    // 定义玩家O的符号
    public static final char PLAYER_O = 'O';
    // 定义学习率（控制更新Q表时的步长）
    public static final double LEARNING_RATE = 0.1;
    // 定义折扣因子（对未来奖励的权重）
    public static final double DISCOUNT_FACTOR = 0.9;
    // 定义探索率（控制随机选择动作的概率）
    public static final double EXPLORATION_RATE = 0.1;
    
    
}
