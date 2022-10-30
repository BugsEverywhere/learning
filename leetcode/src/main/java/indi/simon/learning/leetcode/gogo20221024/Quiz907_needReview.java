package indi.simon.learning.leetcode.gogo20221024;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz907_needReview {

    public static void main(String[] args) {
        Quiz907_needReview quiz907NeedReview = new Quiz907_needReview();
        int res = quiz907NeedReview.sumSubarrayMins(new int[]{11, 81, 94, 43, 3});
        System.out.println(res);
    }

    //todo: 官方所谓单调栈
    public int sumSubarrayMinsOfficial(int[] arr) {
        int n = arr.length;
        Deque<Integer> monoStack = new ArrayDeque<>();
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            while (!monoStack.isEmpty() && arr[i] <= arr[monoStack.peek()]) {
                monoStack.pop();
            }
            left[i] = i - (monoStack.isEmpty() ? -1 : monoStack.peek());
            monoStack.push(i);
        }
        monoStack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!monoStack.isEmpty() && arr[i] < arr[monoStack.peek()]) {
                monoStack.pop();
            }
            right[i] = (monoStack.isEmpty() ? n : monoStack.peek()) - i;
            monoStack.push(i);
        }
        long ans = 0;
        final int MOD = 1000000007;
        for (int i = 0; i < n; i++) {
            ans = (ans + (long) left[i] * right[i] * arr[i]) % MOD;
        }
        return (int) ans;
    }

    //todo: 穷举，超时
    public int sumSubarrayMins(int[] arr) {
        int i = 0;
        int sum = 0;
        while (i < arr.length) {
            int minThisRound = Integer.MAX_VALUE;
            for (int j = i; j < arr.length; j++) {
                minThisRound = Math.min(arr[j], minThisRound);
                sum += minThisRound;
                sum = (int) (sum % (Math.pow(10, 9) + 7));
            }
            i++;
        }

        return sum;
    }

}
