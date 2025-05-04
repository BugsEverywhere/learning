package indi.simon.reinforcementlearning.valuebased;

import indi.simon.reinforcementlearning.TicTacToe;

import java.util.Arrays;

import static indi.simon.reinforcementlearning.Constant.*;

/**
 * Q值法的训练-测试类
 */
public class QLearningMain {

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
                String state = Arrays.deepToString(game.getBoard());
                // 获取当前玩家的智能体
                QLearningAgent currentAgent = (game.getCurrentPlayer() == PLAYER_X) ? agentX : agentO;
                // 根据当前状态选择动作（训练时允许探索）
                int[] action = currentAgent.getAction(state, true, game);

                // 执行动作
                game.makeMove(action[0], action[1]);
                // 获取下一个状态
                String nextState = Arrays.deepToString(game.getBoard());

                // 如果游戏结束，计算奖励并更新Q表
                if (game.isGameOver()) {
                    char winner = game.getWinner();
                    double reward;
                    if(winner == currentAgent.getPlayer()){
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
            String state = Arrays.deepToString(game.getBoard());
            // 获取当前玩家的智能体
            QLearningAgent currentAgent = (game.getCurrentPlayer() == PLAYER_X) ? agentX : agentO;
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