package indi.simon.learning.复习.动态规划;

/**
 * @author chenzhuo(zhiyue)
 *
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 *
 */
public class Quiz416_分割等和子集 {

    public static void main(String[] args) {
        Quiz416_分割等和子集 quiz416分割等和子集 = new Quiz416_分割等和子集();
        int[] nums = new int[]{100};
        boolean res = quiz416分割等和子集.canPartition(nums);
        System.out.println(res);
    }

    public boolean canPartition(int[] nums) {

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        if (sum % 2 != 0) {
            return false;
        }

        //dp[i][j]表示到第i个数为止，组成j的可能性，可以组成j则为true，否则为false
        //由于所有数是正整数，因此第0列没用，不会存在dp[i][0]的状态
        boolean[][] dp = new boolean[nums.length][sum + 1];
        dp[0][nums[0]] = true;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (j >= nums[i]) {
                    //情况1：前i-1个数已经能组成数字j了，那前i个数肯定也能组成数字j
                    //情况2：前i-1个数可以组成数字j-nums[i]，那到了第i个数，加上nums[i]就能组成数字j
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                } else {
                    //j如果本身比nums[i]更小，那直接继承前i-1个数的状态即可
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[nums.length - 1][sum / 2];
    }
}
