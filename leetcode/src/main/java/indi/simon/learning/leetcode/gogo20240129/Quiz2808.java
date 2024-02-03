package indi.simon.learning.leetcode.gogo20240129;

import java.util.*;

/**
 * Created by Chen Zhuo on 2024/2/1.
 */
public class Quiz2808 {

    public static void main(String[] args) {
        Quiz2808 quiz2808 = new Quiz2808();
        List<Integer> nums = new ArrayList<>();
        nums.add(19);
        nums.add(20);
        nums.add(7);
        nums.add(7);
        nums.add(20);
        int res = quiz2808.minimumSeconds(nums);
        System.out.println(res);
    }

    //todo: 坑挺多的，首先不需要先找出现次数最多的数，取nums中任何数的最大间距的最小值即可，
    // 其次，一次for循环找到该最大间距最小值，不要把复杂度上升到N方
    public int minimumSeconds(List<Integer> nums) {
        //val[0]表示key目前的最大间距，val[1]表示nums中上一个key的下标，val[2]表示nums中第一个key的下标
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            if (map.containsKey(nums.get(i))) {
                int[] arr = map.get(nums.get(i));
                //当前下标与上一个key的距离
                //todo: 此处注意要与后头最终计算结果时约定好，所谓间距如果是指两个下标之间相隔了几个数，那么此处要减1
                int gap = i - arr[1] - 1;
                //更新
                arr[0] = Math.max(arr[0], gap);
                arr[1] = i;
            } else {
                int[] arr = new int[3];
                arr[1] = i;
                arr[2] = i;
                map.put(nums.get(i), arr);
            }
        }

        //todo: 如果整个nums中就一个数，提前返回
        if(map.size() == 1){
            return 0;
        }

        //所有数的最大间距中的最小值，需要先算出所有数的最大间距，然后取其中最小的
        int minMaxGap = Integer.MAX_VALUE;
        //完事儿之后还要更新首尾相距距离
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] arr = entry.getValue();
            int headAndTail = nums.size() - 1 - arr[1] + arr[2];
            arr[0] = Math.max(arr[0], headAndTail);
            minMaxGap = Math.min(arr[0], minMaxGap);
        }

        return (minMaxGap / 2) + (minMaxGap % 2);
    }

}
