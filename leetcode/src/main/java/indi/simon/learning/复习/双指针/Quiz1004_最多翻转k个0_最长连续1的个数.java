package indi.simon.learning.复习.双指针;

/**
 * Created by Chen Zhuo on 2025/5/10.
 *
 * 给定一个二进制数组 nums 和一个整数 k，假设最多可以翻转 k 个 0 ，则返回执行操作后 数组中连续 1 的最大个数 。
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：[1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 * 示例 2：
 *
 * 输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 * 提示：
 *
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1
 * 0 <= k <= nums.length
 * Related Topics
 * 数组
 * 二分查找
 * 前缀和
 * 滑动窗口
 *
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
            while (rightZeroCnt - leftZeroCnt > k) {
                //0~right区间内的0比0~left区间内多出超过k个了，左指针需要往前进，同时更新[0,left]中0的数量
                if (nums[left] == 0) {
                    leftZeroCnt++;
                }
                ++left;
            }
            //更新答案
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

}
