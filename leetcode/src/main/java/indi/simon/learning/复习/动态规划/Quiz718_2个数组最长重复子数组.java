package indi.simon.learning.复习.动态规划;

/**
 * Created by Chen Zhuo on 2024/2/18.
 */
public class Quiz718_2个数组最长重复子数组 {

    public static void main(String[] args) {

    }

    public int findLength(int[] A, int[] B) {
        int n = A.length, m = B.length;
        //todo: dp[i][j]代表A从i开始的[i~n]子数组与B从j开始的[j~m]子数组的最长重复子数组，
        // 加一个长度的哨兵是为了减少dp数组初始化的逻辑，也可以去掉哨兵，那么就需要初始化最后一行和最后一列
        int[][] dp = new int[n + 1][m + 1];
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (A[i] == B[j]) {
                    //只有当A当前元素与B当前元素相等时才需要状态继承并转移
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}
