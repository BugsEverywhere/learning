package indi.simon.learning.复习.优先级队列;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author chenzhuo(zhiyue)
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,2,2,3], k = 2
 *
 * 输出：[1,2]
 *
 * 示例 2：
 *
 * 输入：nums = [1], k = 1
 *
 * 输出：[1]
 *
 * 示例 3：
 *
 * 输入：nums = [1,2,1,2,1,2,3,1,3,2], k = 2
 *
 * 输出：[1,2]
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 *
 * 你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 *
 */
//todo: 堆大小matters!!!
public class Quiz347_前k个高频元素 {

    public static void main(String[] args) {
        Quiz347_前k个高频元素 quiz347 = new Quiz347_前k个高频元素();
        int[] res = quiz347.topKFrequent(new int[]{111, 666, 111, 666, 444, 555}, 2);
        System.out.println(res);
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        // 大顶堆
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(m -> m[1]));
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            //需要严格控制堆的大小为k，小于n，这是有原因的
            if (queue.size() == k) {
                //如果堆大小已经为k，则如果堆顶比当前数出现次数还小，则堆排出一个，并且当前数入堆
                // 否则没必要入堆，因为入堆的时间复杂度为logM，M为堆的大小，如果M==n，那最终的时间复杂度又是nlogn了，
                // 题目要求优于nlogn，那nlogk也是优于nlogn，没说一定要O(n)
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }

        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }

}
