package indi.simon.learning.复习.动态规划;

/**
 * Created by Chen Zhuo on 2025/5/11.
 */
public class Quiz_42接雨水 {

    public static void main(String[] args) {
        Quiz_42接雨水 quiz42接雨水 = new Quiz_42接雨水();
        int res = quiz42接雨水.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        System.out.println(res);
    }

    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        //0~i最高的那根柱，闭区间
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        //i~n最高的那根柱，闭区间
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

}
