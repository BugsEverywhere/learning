package indi.simon.learning.复习.动态规划;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 这个DP的精髓在于，其每一步状态是当前最值，那么所有的状态就不是一个可枚举的有限集合，这个时候就没办法画出DP表，所以很考验对于状态的抽象能力。
// 况且，此题的官方解答如下，每一步的状态是指到每一个i位时，该位之前的最大连续乘积，以及最小连续乘积，那么每前进一步的状态转移就是本步的两个状态与下一步的i值交叉相乘再取最值，想到这一点也很不容易
public class Quiz152_乘积最大子数组 {

    public static void main(String[] args) {
        int[] nums = new int[]{-4,-3,-2};
        Quiz152_乘积最大子数组 quiz152_乘积最大子数组 = new Quiz152_乘积最大子数组();
        int res = quiz152_乘积最大子数组.maxProduct(nums);
        System.out.println(res);

    }

    public int maxProduct(int[] nums) {
        //代表到第i个数为止，当前最大的乘积
        int maxI = 1;
        //代表到第i个数为止，当前最小的乘积（为了考虑负数的情况）
        int minI = 1;

        int res = Integer.MIN_VALUE;
        for (int num : nums) {
            //自身、自身与maxI相乘、自身与minI相乘，三个状态取最大
            int maxTmp = Math.max(Math.max(num, num * maxI), num * minI);
            //自身、自身与maxI相乘、自身与minI相乘，三个状态取最小
            int minTmp = Math.min(Math.min(num, num * maxI), num * minI);

            //务必注意，maxI和minI参与了计算maxTmp和minTmp，因此务必要在此之后才更新他们
            maxI = maxTmp;
            minI = minTmp;

            res = Math.max(res, maxI);
        }

        return res;
    }
}
