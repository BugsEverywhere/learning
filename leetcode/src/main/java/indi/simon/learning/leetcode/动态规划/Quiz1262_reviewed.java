package indi.simon.learning.leetcode.动态规划;


/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1262_reviewed {

    public static void main(String[] args) {
        int[] arr = new int[]{3,6,5,1,8};
        Quiz1262_reviewed quiz1262Reviewed = new Quiz1262_reviewed();
        int sum = quiz1262Reviewed.maxSumDivThree(arr);
        System.out.println(sum);
    }

    //牛人的DP
    private int maxSumDivThreeOfficial(int[] nums) {
        int[] dp = new int[3];
        for (int num : nums) {
            int a, b, c;
            a = dp[0] + num;
            b = dp[1] + num;
            c = dp[2] + num;
            dp[a % 3] = Math.max(dp[a % 3], a);
            dp[b % 3] = Math.max(dp[b % 3], b);
            dp[c % 3] = Math.max(dp[c % 3], c);
        }
        return dp[0];
    }

    //todo: 自己的超时DP
    public int maxSumDivThreeTimeExceed(int[] nums) {
        //dp[i][0]代表截止nums[i]为止，可被3整除的最大和，
        //dp[i][1]代表截止nums[i]为止，被3除余1的最大和，
        //dp[i][2]代表截止nums[i]为止，被3除余2的最大和，
        int[][] dp = new int[nums.length][3];
        dp[0][0] = nums[0] % 3 == 0 ? nums[0] : 0;
        dp[0][1] = nums[0] % 3 == 1 ? nums[0] : 0;
        dp[0][2] = nums[0] % 3 == 2 ? nums[0] : 0;

        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                int tmp0 = nums[i] + dp[j][0];
                int tmp1 = nums[i] + dp[j][1];
                int tmp2 = nums[i] + dp[j][2];

                if (tmp0 % 3 == 0) {
                    dp[i][0] = Math.max(dp[i][0], tmp0);
                    if (tmp1 % 3 == 1) {
                        dp[i][1] = Math.max(dp[i][1], tmp1);
                    }
                    if (tmp2 % 3 == 2) {
                        dp[i][2] = Math.max(dp[i][2], tmp2);
                    }
                } else if (tmp0 % 3 == 1) {
                    dp[i][1] = Math.max(dp[i][1], tmp0);
                    if (tmp1 % 3 == 2) {
                        dp[i][2] = Math.max(dp[i][2], tmp1);
                    }
                    if (tmp2 % 3 == 0) {
                        dp[i][0] = Math.max(dp[i][0], tmp2);
                    }
                } else if (tmp0 % 3 == 2) {
                    dp[i][2] = Math.max(dp[i][2], tmp0);
                    if (tmp1 % 3 == 0) {
                        dp[i][0] = Math.max(dp[i][0], tmp1);
                    }
                    if (tmp2 % 3 == 1) {
                        dp[i][1] = Math.max(dp[i][1], tmp2);
                    }
                }
            }
            max = Math.max(max, dp[i][0]);
        }

        return max;
    }



    //todo: 自己的有问题的DP，比较一下这个和牛人DP的区别吧
    public int maxSumDivThree(int[] nums) {
        //dp[i][0]代表截止nums[i]为止，可被3整除的最大和，
        //dp[i][1]代表截止nums[i]为止，被3除余1的最大和，
        //dp[i][2]代表截止nums[i]为止，被3除余2的最大和，
        int[][] dp = new int[nums.length][3];
        dp[0][0] = nums[0] % 3 == 0 ? nums[0] : 0;
        dp[0][1] = nums[0] % 3 == 1 ? nums[0] : 0;
        dp[0][2] = nums[0] % 3 == 2 ? nums[0] : 0;

        for (int i = 1; i < nums.length; i++) {
            int j = i - 1;
            int tmp0 = nums[i] + dp[j][0];
            int tmp1 = nums[i] + dp[j][1];
            int tmp2 = nums[i] + dp[j][2];

            dp[i][tmp0 % 3] = Math.max(dp[j][tmp0 % 3], tmp0);
            dp[i][tmp1 % 3] = Math.max(dp[j][tmp1 % 3], tmp1);
            dp[i][tmp2 % 3] = Math.max(dp[j][tmp2 % 3], tmp2);

            dp[i][0] = Math.max(dp[j][0],dp[i][0]);
            dp[i][1] = Math.max(dp[j][1],dp[i][1]);
            dp[i][2] = Math.max(dp[j][2],dp[i][2]);
        }

        return dp[nums.length - 1][0];
    }


}
