package indi.simon.learning.leetcode.gogo20231211;

import java.util.PriorityQueue;

/**
 * Created by Chen Zhuo on 2023/12/17.
 */
public class Quiz1918_needReview {

    public static void main(String[] args) {

    }

    //todo: 暴力解法超出时限，最优解是
    public int kthSmallestSubarraySum(int[] nums, int k) {
        PriorityQueue<Integer> subSum = new PriorityQueue<>();
        cal(nums, 0, subSum);
        int i = 1;
        while(i < k){
            subSum.poll();
            i++;
        }
        return subSum.peek();
    }

    private void cal(int[] nums, int i, PriorityQueue<Integer> subSum) {
        if (i == nums.length - 1) {
            subSum.offer(nums[i]);
            return;
        }

        for (int j = i; j < nums.length; j++) {
            int sum = 0;
            int k = i;
            while (k <= j) {
                sum += nums[k];
                k++;
            }
            subSum.offer(sum);
        }

        cal(nums, i + 1, subSum);
    }

}
