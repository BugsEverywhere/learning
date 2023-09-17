package indi.simon.learning.leetcode.gogo20230911;

import java.util.*;

/**
 * Created by Chen Zhuo on 2023/9/17.
 */
public class Quiz253 {

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{0, 30}, {5, 10}, {15, 20}};
        Quiz253 quiz253 = new Quiz253();
        int res = quiz253.minMeetingRooms(intervals);
        System.out.println(res);
    }

    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        //记录每个管道当前会议结束时间
        PriorityQueue<Integer> endTimeQueue = new PriorityQueue<>();

        for (int i = 0; i < intervals.length; i++) {
            if (endTimeQueue.size() > 0) {
                int earliestEndTime = endTimeQueue.peek();
                if (earliestEndTime <= intervals[i][0]) {
                    endTimeQueue.poll();
                }
                endTimeQueue.offer(intervals[i][1]);
            } else {
                endTimeQueue.offer(intervals[i][1]);
            }
        }

        return endTimeQueue.size();
    }

}
