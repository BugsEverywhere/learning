package indi.simon.learning.leetcode.gogo20230828;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2023/9/3.
 */
//todo: 第一次的时候考虑得有点多，但是仔细想想，dp[i]和factor1以及factor2的结果集是相乘的关系，
// arr[i]的两个因子factor1和factor2无外乎两种关系：
// 1. factor1==factor2，此时dp[i]=dp[factor1] * dp[factor2]
// 2. factor1!=factor2，此时dp[i]=dp[factor1] * dp[factor2] * 2，因为左右子节点可以交换
// 在实际操作的时候两种情况可以统一，因为从i向后遍历时，如果factor1==factor2，那么这种情况只会遍历一次，不会出现第二次，
// 而其余的均是factor1!=factor2的情况，会遍历两次，一次是arr[i] = factor1 * factor2，一次是arr[i] = factor2 * factor1，
// 正好满足上面需要乘以2的情况，所以不需要特殊处理
public class Quiz823_hinted {

    public static void main(String[] args) {
        Quiz823_hinted quiz823Hinted = new Quiz823_hinted();
        int res = quiz823Hinted.numFactoredBinaryTrees(new int[]{2, 4});
        System.out.println(res);
    }

    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        Map<Integer, Integer> numIndexMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            numIndexMap.put(arr[i], i);
        }

        //dp[i]表示符合题意的，以arr[i]为根节点的二叉树的数目
        long[] dp = new long[arr.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j >= 0; j--) {
                int factor1 = arr[j];
                if (arr[i] % factor1 != 0) {
                    continue;
                }
                int factor2 = arr[i] / factor1;
                if (!numIndexMap.containsKey(factor2)) {
                    continue;
                }
                dp[i] += dp[j] * dp[numIndexMap.get(factor2)];
            }
        }

        long sum = 0;
        for (long singleCnt : dp) {
            sum += singleCnt;
        }

        return (int) (sum % (Math.pow(10, 9) + 7));
    }

}
