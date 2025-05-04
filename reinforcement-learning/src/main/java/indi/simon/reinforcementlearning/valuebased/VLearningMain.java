package indi.simon.reinforcementlearning.valuebased;

import indi.simon.reinforcementlearning.TicTacToe;

import java.util.Arrays;

import static indi.simon.reinforcementlearning.Constant.*;
import static indi.simon.reinforcementlearning.Constant.PLAYER_X;

/**
 * V值法的训练-测试类
 */
public class VLearningMain {

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        VLearningAgent agentX = new VLearningAgent(PLAYER_X);
        VLearningAgent agentO = new VLearningAgent(PLAYER_O);

        int episodes = 10000;
        for (int episode = 0; episode < episodes; episode++) {
            game = new TicTacToe();
            while (!game.isGameOver()) {
                String state = Arrays.deepToString(game.getBoard());
                VLearningAgent currentAgent = (game.getCurrentPlayer() == PLAYER_X) ? agentX : agentO;
                int[] action = currentAgent.getAction(state, true, game);

                game.makeMove(action[0], action[1]);
                String nextState = Arrays.deepToString(game.getBoard());

                double reward = 0;
                if (game.isGameOver()) {
                    char winner = game.getWinner();
                    if (winner == currentAgent.getPlayer()) {
                        reward = 1;
                    } else if (winner == EMPTY) {
                        reward = 0.5;
                    } else {
                        reward = -1;
                    }
                }

                currentAgent.updateVTable(state, reward, nextState);
            }
        }

        // 测试训练好的智能体
        game = new TicTacToe();
        while (!game.isGameOver()) {
            String state = Arrays.deepToString(game.getBoard());
            VLearningAgent currentAgent = (game.getCurrentPlayer() == PLAYER_X) ? agentX : agentO;
            int[] action = currentAgent.getAction(state, false, game);
            game.makeMove(action[0], action[1]);
            game.printBoard();
        }

        char winner = game.getWinner();
        if (winner != EMPTY) {
            System.out.println("Winner: " + winner);
        } else {
            System.out.println("Draw!");
        }
    }
}
