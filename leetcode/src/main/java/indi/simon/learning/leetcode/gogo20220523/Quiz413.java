package indi.simon.learning.leetcode.gogo20220523;


/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz413 {

    public static void main(String[] args) {
        Quiz413 quiz413 = new Quiz413();
        int[] nums = new int[]{1, 2, 3, 4, 5, 11, 17, 18, 19, 20};
        int res = quiz413.numberOfArithmeticSlices(nums);
        System.out.println(res);
    }

    public int numberOfArithmeticSlices(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        //第一位标识到下标i为止累计有多少个等差数列，第二位标识目前为止连续等差数列的长度
        int[][] dp = new int[nums.length][2];

        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] != nums[i - 1] - nums[i - 2]) {
                //没有成团
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = 0;
            } else {
                //满三个成团了
                if (dp[i - 1][1] == 0) {
                    //第一个成团的
                    dp[i][0] = dp[i - 1][0] + 1;
                    dp[i][1] = 3;
                } else {
                    //后续成团的
                    dp[i][0] = dp[i - 1][0] + dp[i - 1][1] - 1;
                    dp[i][1] = dp[i - 1][1] + 1;
                }
            }
        }

        return dp[nums.length - 1][0];
    }

}
