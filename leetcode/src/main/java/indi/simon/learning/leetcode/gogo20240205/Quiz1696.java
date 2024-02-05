package indi.simon.learning.leetcode.gogo20240205;

import java.util.*;

/**
 * Created by Chen Zhuo on 2024/2/5.
 */
public class Quiz1696 {

    public static void main(String[] args) {
        Quiz1696 quiz1696 = new Quiz1696();
        int res = quiz1696.maxResult(new int[]{-123}, 10);
        System.out.println(res);
    }

    public int maxResult(int[] nums, int k) {
        //element[0] represents the index in nums, element[1] represents the max score in the window
        PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[1], o1[1]));
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (heap.size() == 0) {
                //todo: 注意初始状态也需要判断
                if(i == nums.length - 1){
                    res = nums[i];
                } else {
                    heap.offer(new int[]{i, nums[i]});
                }
            } else {
                int[] maxEle = heap.peek();
                while (maxEle[0] < i - k) {
                    heap.poll();
                    maxEle = heap.peek();
                }
                if(i == nums.length - 1){
                    res = nums[i] + maxEle[1];
                } else {
                    heap.offer(new int[]{i, nums[i] + maxEle[1]});
                }
            }
        }

        return res;
    }

}
