package indi.simon.learning.复习.双指针;

import java.util.Arrays;

public class Quiz16_最接近的三数之和 {

    public static void main(String[] args) {
        Quiz16_最接近的三数之和 quiz16 = new Quiz16_最接近的三数之和();

    }

    public int threeSumClosest(int[] nums, int target) {
        //todo: 上来先排序，聚拢所有相等的数
        Arrays.sort(nums);
        int n = nums.length;
        // 存放结果
        int bestSum = Integer.MAX_VALUE;

        for (int i = 0; i < n; ++i) {
            //todo: i要跳过重复的
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 使用双指针枚举 j 和 k
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                //todo: 加速trick
                if (sum == target) {
                    return target;
                }

                // 更新答案
                if (Math.abs(sum - target) < Math.abs(bestSum - target)) {
                    bestSum = sum;
                }

                // 继续往下走，不管之前答案是否被更新
                if (sum < target) {
                    // 较小，右移j
                    int j0 = j + 1;
                    // todo: j移动时要跳过重复的
                    while (j0 < k && nums[j0] == nums[j]) {
                        ++j0;
                    }
                    j = j0;
                } else {
                    // 较大，左移k
                    int k0 = k - 1;
                    // todo: k移动时要跳过重复的
                    while (j < k0 && nums[k0] == nums[k]) {
                        --k0;
                    }
                    k = k0;
                }
            }
        }
        return bestSum;
    }
}
