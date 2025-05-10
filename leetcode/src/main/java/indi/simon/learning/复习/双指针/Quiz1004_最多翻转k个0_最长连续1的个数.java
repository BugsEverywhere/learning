package indi.simon.learning.复习.双指针;

/**
 * Created by Chen Zhuo on 2025/5/10.
 */
public class Quiz1004_最多翻转k个0_最长连续1的个数 {

    public static void main(String[] args) {
        Quiz1004_最多翻转k个0_最长连续1的个数 quiz1004最多翻转k个0最长连续1的个数 = new Quiz1004_最多翻转k个0_最长连续1的个数();
        int res = quiz1004最多翻转k个0最长连续1的个数.longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3);
        System.out.println(res);
    }

    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int left = 0;
        //记录0~left内0的个数
        int leftZeroCnt = 0;
        //记录0~right内0的个数
        int rightZeroCnt = 0;
        int ans = 0;
        //虽然是双指针，但是right和left都从0开始
        for (int right = 0; right < n; ++right) {
            if (nums[right] == 0) {
                rightZeroCnt++;
            }
            while (leftZeroCnt < rightZeroCnt - k) {
                //0~right区间内的0比0~left区间内多出超过k个了，左指针需要往前进
                if (nums[left] == 0) {
                    leftZeroCnt++;
                }
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

}
