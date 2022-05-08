package indi.simon.learning.leetcode.gogo20220502;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 这个DP的精髓在于，其每一步状态是当前最值，那么所有的状态就不是一个可枚举的有限集合，这个时候就没办法画出DP表，所以很考验对于状态的抽象能力。
// 况且，此题的官方解答如下，每一步的状态是指到每一个i位时，该位之前的最大连续乘积，以及最小连续乘积，那么每前进一步的状态转移就是本步的两个状态与下一步的i值交叉相乘再取最值，想到这一点也很不容易
public class Quiz152_reviewed {

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 0, -1};
        Quiz152_reviewed quiz152_reviewed = new Quiz152_reviewed();
        int res = quiz152_reviewed.maxProduct(nums);
        System.out.println(res);

    }

    public int maxProduct(int[] nums) {

        int maxI = 1;
        int minI = 1;

        int maxMultipleSoFar = Integer.MIN_VALUE;
        for (int num : nums) {
            int thisMax = Math.max(Math.max(num, num * maxI), num * minI);
            int thisMin = Math.min(Math.min(num, num * maxI), num * minI);
            maxI = thisMax;
            minI = thisMin;
            maxMultipleSoFar = Math.max(maxMultipleSoFar, maxI);
        }

        return maxMultipleSoFar;
    }
}
