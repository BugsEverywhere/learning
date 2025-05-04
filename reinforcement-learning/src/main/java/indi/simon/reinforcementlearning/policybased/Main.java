package indi.simon.reinforcementlearning.policybased;

import indi.simon.reinforcementlearning.TicTacToe;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PolicyGradientAgent agentX = new PolicyGradientAgent();
        PolicyGradientAgent agentO = new PolicyGradientAgent();

        int episodes = 1000;
        for (int episode = 0; episode < episodes; episode++) {
            TicTacToe game = new TicTacToe();
            playGame(game, agentX, agentO);
            if ((episode + 1) % 100 == 0) {
                System.out.println("Episode " + (episode + 1) + " completed.");
            }
        }

        // 测试训练后的表现
        TicTacToe testGame = new TicTacToe();
        while (!testGame.isGameOver()) {
            testGame.printBoard();
            List<int[]> moves = testGame.getAvailableMoves();
            if (testGame.getCurrentPlayer() == 'X') {
                int[] action = agentX.getAction(testGame.getBoard(), moves);
                testGame.makeMove(action[0], action[1]);
            } else {
                int[] action = agentO.getAction(testGame.getBoard(), moves);
                testGame.makeMove(action[0], action[1]);
            }
        }
        testGame.printBoard();
        char winner = testGame.getWinner();
        System.out.println(winner != ' ' ? "Winner: " + winner : "Draw!");
    }

    private static void playGame(TicTacToe game, PolicyGradientAgent agentX, PolicyGradientAgent agentO) {
        while (!game.isGameOver()) {
            List<int[]> moves = game.getAvailableMoves();
            PolicyGradientAgent currentAgent = game.getCurrentPlayer() == 'X' ? agentX : agentO;
            double[] state = currentAgent.getStateVector(game.getBoard());
            int[] action = currentAgent.getAction(game.getBoard(), moves);
            game.makeMove(action[0], action[1]);

            double reward = 0;
            if (game.isGameOver()) {
                char winner = game.getWinner();
                if (winner == 'X') reward = 1;
                else if (winner == 'O') reward = -1;
                else reward = 0.5;
            }

            currentAgent.remember(state, action, reward);
        }

        agentX.learn();
        agentO.learn();
    }
}
