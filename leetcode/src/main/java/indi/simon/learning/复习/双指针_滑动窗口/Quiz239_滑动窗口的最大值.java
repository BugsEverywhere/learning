package indi.simon.learning.复习.双指针_滑动窗口;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回 滑动窗口中的最大值 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * 示例 2：
 * <p>
 * 输入：nums = [1], k = 1
 * 输出：[1]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */
public class Quiz239_滑动窗口的最大值 {

    public static void main(String[] args) {
        Quiz239_滑动窗口的最大值 quiz = new Quiz239_滑动窗口的最大值();
        int[] res = quiz.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.out.println(Arrays.toString(res));
    }


    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        //int[0]是数组中的数本身num，int[1]是下标
        //大顶堆
        PriorityQueue<int[]> heap = new PriorityQueue<>((pair1, pair2) -> Integer.compare(pair2[0], pair1[0]));

        //建堆
        for (int i = 0; i < k; ++i) {
            heap.offer(new int[]{nums[i], i});
        }

        //结果数组
        int[] ans = new int[n - k + 1];

        //结果数组的第一个元素肯定是当前堆顶
        ans[0] = heap.peek()[0];
        //开始移动滑窗
        for (int i = k; i < n; ++i) {
            //元素无论如何都入堆
            heap.offer(new int[]{nums[i], i});
            //循环检查堆顶，如果堆顶元素不在滑窗内，则排出
            while (heap.peek()[1] <= i - k) {
                heap.poll();
            }
            //得到合法堆顶，填入结果数组
            ans[i - k + 1] = heap.peek()[0];
        }
        return ans;
    }

}
