package indi.simon.learning.复习.排序;

import java.util.PriorityQueue;

/**
 * @author chenzhuo(zhiyue)
 * <p>
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 * 提示：
 * <p>
 * 1 <= k <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */
public class Quiz215_数组中第k个最大的元素 {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        Quiz215_数组中第k个最大的元素 quiz215_数组中第k个最大的元素 = new Quiz215_数组中第k个最大的元素();
        int res = quiz215_数组中第k个最大的元素.findKthLargest(nums, 2);
        System.out.println(res);

    }

    public int findKthLargest(int[] nums, int k) {
        // 使用一个含有 k 个元素的最小堆，PriorityQueue 底层是动态数组，为了防止数组扩容产生消耗，可以先指定数组的长度
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }
        int n = nums.length;
        for (int i = k; i < n; i++) {
            // 当前元素比堆顶元素大，堆顶弹出，当前元素入堆
            if (nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.peek();
    }

}
