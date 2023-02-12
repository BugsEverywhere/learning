package indi.simon.learning.leetcode.滑动窗口;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1658_reviewed {

    public static void main(String[] args) {
        Quiz1658_reviewed quiz1658Reviewed = new Quiz1658_reviewed();
        int res = quiz1658Reviewed.minOperations(new int[]{1, 1}, 3);
        System.out.println(res);
    }

    //todo: 官方滑动窗口，只要O(n)的复杂度，同样是双指针，但是右指针没必要从最右端开始往左移，这里的解答给出了
    // 右指针和左指针本来紧挨着，而且往同一个方向移动的方法。前缀和与后缀和比x大的话，就右移右指针，相反，前缀和
    // 与后缀和比x小就右移左指针，碰到与x相等的就记录当前的步数，这样其实不会错过所有满足条件的前缀和后缀，而且
    // 一趟扫描完事儿，比递归快多了
    public int minOperationsOfficial(int[] nums, int x) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if (sum < x) {
            return -1;
        }
        int right = 0;
        int lsum = 0, rsum = sum;
        int ans = n + 1;
        for (int left = -1; left < n; ++left) {
            if (left != -1) {
                lsum += nums[left];
            }
            while (right < n && lsum + rsum > x) {
                rsum -= nums[right];
                ++right;
            }
            if (lsum + rsum == x) {
                ans = Math.min(ans, (left + 1) + (n - right));
            }
        }
        return ans > n ? -1 : ans;
    }


    //todo: 自己的递归超时
    private int minStep;

    public int minOperations(int[] nums, int x) {
        minStep = Integer.MAX_VALUE;
        minOperationsInternal(nums, x, 0, nums.length - 1, 0, 0);
        if (minStep == Integer.MAX_VALUE) {
            return -1;
        } else {
            return minStep;
        }
    }

    /**
     * @param nums
     * @param x
     * @param i
     * @param j
     * @param sumForNow
     * @param step
     */
    private void minOperationsInternal(int[] nums, int x, int i, int j, int sumForNow, int step) {
        if (i > j) {
            return;
        }

        if (sumForNow + nums[i] < x) {
            minOperationsInternal(nums, x, i + 1, j, sumForNow + nums[i], step + 1);
        } else if (sumForNow + nums[i] == x) {
            minStep = Math.min(step + 1, minStep);
        }

        if (sumForNow + nums[j] < x) {
            minOperationsInternal(nums, x, i, j - 1, sumForNow + nums[j], step + 1);
        } else if (sumForNow + nums[j] == x) {
            minStep = Math.min(step + 1, minStep);
        }
    }


}
