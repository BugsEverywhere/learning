package indi.simon.learning.leetcode.gogo20230424;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1031 {

    public static void main(String[] args) {
        Quiz1031 quiz1031 = new Quiz1031();
        int res = quiz1031.maxSumTwoNoOverlap(new int[]{11, 13, 12, 17, 17, 19, 1, 14, 4, 7, 4, 8, 4}, 10, 2);
        System.out.println(res);
    }

    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {

        int i1 = 0;
        int j1 = i1 + firstLen - 1;

        int maxSum = Integer.MIN_VALUE;

        while (i1 >= 0 && j1 < nums.length) {
            int sum1 = 0;
            for (int k = i1; k <= j1; k++) {
                sum1 += nums[k];
            }

            int i2 = 0;
            int j2 = i2 + secondLen - 1;
            int maxSum2 = 0;
            for (; i2 >= 0 && j2 < nums.length; i2++, j2++) {
                //todo: 要注意判断两个区间是否重叠这里，已经不是第一次犯错了，漏判了两个条件
                if ((i1 >= i2 && i1 <= j2) || (j1 <= j2 && j1 >= i2) || (i2 >= i1 && i2 <= j1) || (j2 >= i1 && j2 <= j1)) {
                    //有重叠
                    continue;
                }
                int sum2 = 0;
                for (int k = i2; k <= j2; k++) {
                    sum2 += nums[k];
                }
                maxSum2 = Math.max(maxSum2, sum2);
            }
            maxSum = Math.max(sum1 + maxSum2, maxSum);
            i1++;
            j1++;
        }

        return maxSum;
    }

}
