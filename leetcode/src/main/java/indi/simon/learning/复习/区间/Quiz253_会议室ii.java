package indi.simon.learning.复习.区间;

import java.util.*;

/**
 * Created by Chen Zhuo on 2023/9/17.
 *
 * 给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，返回 所需会议室的最小数量 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[0,30],[5,10],[15,20]]
 * 输出：2
 * 示例 2：
 *
 * 输入：intervals = [[7,10],[2,4]]
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= intervals.length <= 104
 * 0 <= starti < endi <= 106
 */
//todo: 带贪心性质的区间题
public class Quiz253_会议室ii {

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{0, 30}, {5, 10}, {15, 20}};
        Quiz253_会议室ii quiz253 = new Quiz253_会议室ii();
        int res = quiz253.minMeetingRooms(intervals);
        System.out.println(res);
    }

    public int minMeetingRooms(int[][] intervals) {
        //按开始时间排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        //记录会议结束时间的优先级队列，结束时间入队列后会正序排好序，且最终留在队列中的会议都是时间无法调和的会议
        PriorityQueue<Integer> endTimeQueue = new PriorityQueue<>();

        for (int[] interval : intervals) {
            if (!endTimeQueue.isEmpty()) {
                //如果队尾的会议结束时间早于本次会议，则弹出（腾出会议室）
                int earliestEndTime = endTimeQueue.peek();
                if (earliestEndTime <= interval[0]) {
                    endTimeQueue.poll();
                }
                //当前会议结束时间压入
                endTimeQueue.offer(interval[1]);
            } else {
                endTimeQueue.offer(interval[1]);
            }
        }

        return endTimeQueue.size();
    }

}
