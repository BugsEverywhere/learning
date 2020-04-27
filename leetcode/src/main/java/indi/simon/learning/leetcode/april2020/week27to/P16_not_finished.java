package indi.simon.learning.leetcode.april2020.week27to;

import java.util.Arrays;

public class P16_not_finished {

    public static void main(String[] args) {


    }

    public static int threeSumClosest(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        Arrays.sort(nums);

        int i = 0;
        int j = nums.length - 1;
        int k = i + 1;

        int closestNum;
        int gapAbs = Integer.MAX_VALUE;

        boolean lastOneSmallerThanTarget = nums[i] + nums[j] + nums[k] < target;

        if (nums[i] + nums[j] + nums[k] == target) {
            return target;
        }

//        for (; i < j - 1 && k > i && k < j; ) {
//            if (nums[i] + nums[j] + nums[k] == target) {
//                return target;
//            }
//
//            if (nums[i] + nums[k] + nums[j] > target && lastOneSmallerThanTarget) {
//                //本次比target大，并且上次比target小，则本次和上次的i,j,k组合都是
//                if(){
//
//                }
//            }
//
//
//        }

        return -1;
    }
}
