package indi.simon.reinforcementlearning.valuebased;

import indi.simon.reinforcementlearning.TicTacToe;

import java.util.*;

import static indi.simon.reinforcementlearning.Constant.*;

/**
 * 基于状态价值（V值）进行学习的智能体，不直接产出action，
 * 其getAction的实现是基于ε-greedy + V值评估才能得到一个action
 */
public class VLearningAgent {
    // V表，存储状态的价值
    private Map<String, Double> vTable;
    // 智能体的玩家符号
    private char player;

    // 构造函数
    public VLearningAgent(char player) {
        this.player = player;
        this.vTable = new HashMap<>();
    }

    // 获取状态价值
    private double getStateValue(String state) {
        return vTable.getOrDefault(state, 0.0);
    }

    // 更新 V 值
    public void updateVTable(String state, double reward, String nextState) {
        double currentValue = getStateValue(state);
        double nextValue = getStateValue(nextState);

        double tdTarget = reward + DISCOUNT_FACTOR * nextValue;
        double tdError = tdTarget - currentValue;

        // TD 更新规则
        vTable.put(state, currentValue + LEARNING_RATE * tdError);
    }

    // 根据当前状态选择动作（ε-greedy + V值评估）
    public int[] getAction(String state, boolean explore, TicTacToe game) {
        List<int[]> availableMoves = game.getAvailableMoves();

        if (explore && Math.random() < EXPLORATION_RATE) {
            // 探索：随机选择动作
            return availableMoves.get(new Random().nextInt(availableMoves.size()));
        } else {
            // 利用：选择使下一状态 V 值最大的动作
            int bestMoveIndex = -1;
            double bestValue = -Double.MAX_VALUE;

            for (int i = 0; i < availableMoves.size(); i++) {
                int[] move = availableMoves.get(i);

                // 模拟执行该动作，得到下一个状态
                TicTacToe tempGame = copyGameState(game);
                tempGame.makeMove(move[0], move[1]);
                String nextState = Arrays.deepToString(tempGame.getBoard());

                double value = getStateValue(nextState);
                if (game.getCurrentPlayer() == PLAYER_X) {
                    value = -value; // O 玩家视角取负数
                }

                if (value > bestValue) {
                    bestValue = value;
                    bestMoveIndex = i;
                }
            }

            return availableMoves.get(bestMoveIndex);
        }
    }

    // 复制当前游戏状态用于模拟下一步
    private TicTacToe copyGameState(TicTacToe game) {
        TicTacToe copy = new TicTacToe();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                copy.getBoard()[i][j] = game.getBoard()[i][j];
            }
        }
        copy.setCurrentPlayer(game.getCurrentPlayer());
        return copy;
    }

    // 重置 V 表
    public void reset() {
        vTable.clear();
    }

    public char getPlayer() {
        return player;
    }

    public void setPlayer(char player) {
        this.player = player;
    }
}
