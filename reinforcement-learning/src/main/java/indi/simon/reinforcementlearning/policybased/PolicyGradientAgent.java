package indi.simon.reinforcementlearning.policybased;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 基于 REINFORCE 算法的策略梯度智能体（Policy Gradient Agent）
 */
public class PolicyGradientAgent {
    private final int inputSize = 9; // 棋盘展平后的大小：3x3
    private final double learningRate = 0.1;
    private final double discountFactor = 0.9;

    private double[] weights; // 策略参数
    private final Random random = new Random();

    // 存储训练轨迹
    private List<double[]> states = new ArrayList<>();
    private List<Integer> actions = new ArrayList<>();
    private List<Double> rewards = new ArrayList<>();

    public PolicyGradientAgent() {
        // 初始化权重为小随机数
        weights = new double[inputSize];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = (random.nextDouble() - 0.5) * 0.1;
        }
    }

    /**
     * 将棋盘状态转换为输入向量（X=1, O=-1, Empty=0）
     */
    public double[] getStateVector(char[][] board) {
        double[] state = new double[inputSize];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'X') state[i * 3 + j] = 1.0;
                else if (board[i][j] == 'O') state[i * 3 + j] = -1.0;
                else state[i * 3 + j] = 0.0;
            }
        }
        return state;
    }

    /**
     * softmax 选择动作
     */
    public int[] getAction(char[][] board, List<int[]> availableMoves) {
        double[] state = getStateVector(board);
        double[] logits = new double[inputSize];

        // 计算每个位置的得分（logits）
        for (int i = 0; i < inputSize; i++) {
            logits[i] = dotProduct(state, weights);
        }

        // softmax 归一化得到概率分布
        double[] probs = new double[inputSize];
        double maxLogit = Arrays.stream(logits).max().getAsDouble();
        double sumExp = 0.0;
        for (int i = 0; i < inputSize; i++) {
            sumExp += Math.exp(logits[i] - maxLogit);
        }
        for (int i = 0; i < inputSize; i++) {
            probs[i] = Math.exp(logits[i] - maxLogit) / sumExp;
        }

        // 根据概率选择合法动作
        double rand = random.nextDouble();
        double cumulative = 0.0;
        for (int i = 0; i < probs.length; i++) {
            if (board[i / 3][i % 3] != ' ') continue;
            cumulative += probs[i];
            if (rand <= cumulative) {
                return new int[]{i / 3, i % 3};
            }
        }

        // 如果没选到（浮点误差），随机选一个合法动作
        return availableMoves.get(random.nextInt(availableMoves.size()));
    }

    /**
     * 存储当前回合的经验
     */
    public void remember(double[] state, int[] action, double reward) {
        states.add(state);
        actions.add(action[0] * 3 + action[1]);
        rewards.add(reward);
    }

    /**
     * 使用策略梯度更新策略网络权重
     */
    public void learn() {
        if (states.isEmpty()) return;

        List<Double> returns = new ArrayList<>();
        double G = 0.0;
        // 反向计算回报 G_t
        for (int t = rewards.size() - 1; t >= 0; t--) {
            G = rewards.get(t) + discountFactor * G;
            returns.add(0, G);
        }

        // 对每一步进行梯度上升更新
        for (int t = 0; t < states.size(); t++) {
            double[] state = states.get(t);
            int action = actions.get(t);
            G = returns.get(t);

            double logGrad = gradientLogPolicy(state, action);

            // 更新权重
            for (int i = 0; i < weights.length; i++) {
                weights[i] += learningRate * G * logGrad * state[i];
            }
        }

        clearMemory();
    }

    /**
     * 计算 log π(a|s) 的梯度
     */
    private double gradientLogPolicy(double[] state, int action) {
        double logits = dotProduct(state, weights);
        double probAction = 0.0;
        double sumExp = 0.0;

        // 计算 softmax 分母
        for (int a = 0; a < inputSize; a++) {
            sumExp += Math.exp(dotProduct(state, weights));
        }

        // 计算当前动作的概率
        probAction = Math.exp(dotProduct(state, weights)) / sumExp;

        // 返回梯度：φ(s,a) - ∑_a' π(a'|s) φ(s,a')
        return state[action] - probAction * state[action];
    }

    /**
     * 向量点积
     */
    private double dotProduct(double[] a, double[] b) {
        double result = 0.0;
        for (int i = 0; i < a.length; i++) {
            result += a[i] * b[i];
        }
        return result;
    }

    /**
     * 清空记忆
     */
    public void clearMemory() {
        states.clear();
        actions.clear();
        rewards.clear();
    }
}
