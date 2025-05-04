package indi.simon.reinforcementlearning.valuebased;

import indi.simon.reinforcementlearning.TicTacToe;

import java.util.*;

import static indi.simon.reinforcementlearning.Constant.*;

/**
 * 基于状态-行动价值（Q值）进行学习的智能体，可以直接产出action，
 * 这里getAction也用到了ε-greedy
 */
public class QLearningAgent {
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

    // 重置Q表，一般是在进行比如说1万轮学习之后需要重置一次
    public void reset() {
        qTable.clear(); // 清空Q表
    }

    public char getPlayer() {
        return player;
    }

    public void setPlayer(char player) {
        this.player = player;
    }
}
