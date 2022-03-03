package indi.simon.learning.leetcode.gogo20220228;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz16 {

    public static void main(String[] args) {
        Quiz16 quiz16 = new Quiz16();
        int[] nums = new int[]{1, 2, 4, 8, 16, 32, 64, 128};
        int res = quiz16.threeSumClosest(nums, 82);
        System.out.println(res);
    }


    //====================================================================================================解法1，回溯，超时
//    private int minGap = Integer.MAX_VALUE;
//    private List<Integer> numList;
//
//    public int threeSumClosest(int[] nums, int target) {
//        threeSumClosestInternal(nums, new ArrayList<>(), 0, target);
//        return numList.stream().reduce(Integer::sum).get();
//    }
//
//    private void threeSumClosestInternal(int[] nums, List<Integer> numSelected, int currentIndex, int target) {
//
//        if (numSelected.size() >= 3) {
//            int sum = numSelected.stream().reduce(Integer::sum).get();
//            int gap = Math.abs(sum - target);
//            if (gap < minGap) {
//                numList = numSelected;
//                minGap = gap;
//            }
//            return;
//        }
//
//        if (currentIndex >= nums.length) {
//            //结束了还没选到3个数，非法情况，直接返回
//            return;
//        }
//
//        //本下标不入选
//        threeSumClosestInternal(nums, new ArrayList<>(numSelected), currentIndex + 1, target);
//
//        //本下标入选
//        numSelected.add(nums[currentIndex]);
//        threeSumClosestInternal(nums, new ArrayList<>(numSelected), currentIndex + 1, target);
//
//    }
    //====================================================================================================解法1，回溯，超时

    //====================================================================================================解法2，错误的双指针，超时限
//    private int minAbsGap = Integer.MAX_VALUE;
//    private int minAbsGapSum = 0;
//
//    public int threeSumClosest(int[] nums, int target) {
//
//        Arrays.sort(nums);
//        int leftP = 0;
//        int rightP = nums.length - 1;
//        findClosestInternal(nums, leftP, rightP, target);
//        return minAbsGapSum;
//    }
//
//    private void findClosestInternal(int[] nums, int leftP, int rightP, int target) {
//        if (rightP - leftP < 2) {
//            return;
//        }
//        Integer lastGap = null;
//        int i;
//        int originLeftP = leftP;
//        int originRightP = rightP;
//        for (i = originLeftP + 1; i < originRightP; i++) {
//            int gap = nums[leftP] + nums[rightP] + nums[i] - target;
//            if (gap == 0) {
//                //正好找到
//                minAbsGapSum = nums[leftP] + nums[rightP] + nums[i];
//                break;
//            } else if (gap > 0) {
//                //三数和与目标值差值大于0
//                if (lastGap == null) {
//                    //这是本轮第一个i，和与target的差值就大于0，后面不用遍历了，肯定都越来越大
//                    if (Math.abs(gap) < minAbsGap) {
//                        minAbsGap = Math.abs(gap);
//                        minAbsGapSum = nums[leftP] + nums[rightP] + nums[i];
//                    }
//                    //因为是过大，则把右指针往左移
//                    rightP--;
//                    break;
//                } else {
//                    //找到中间发现一个大于0的组合了，后面也不用找了，肯定越来越大
//                    if (Math.abs(gap) < minAbsGap) {
//                        //左指针往右移
//                        minAbsGap = Math.abs(gap);
//                        minAbsGapSum = nums[leftP] + nums[rightP] + nums[i];
//                    }
//                    findClosestInternal(nums, leftP + 1, rightP, target);
//                    findClosestInternal(nums, leftP, rightP - 1, target);
//                    return;
//                }
//            } else {
//                //三数和与目标值差值小于0，i可继续右移
//                if (Math.abs(gap) < minAbsGap) {
//                    minAbsGap = Math.abs(gap);
//                    minAbsGapSum = nums[leftP] + nums[rightP] + nums[i];
//                }
//            }
//            lastGap = gap;
//        }
//        if (minAbsGapSum == target) {
//            //找到了，直接返回
//            return;
//        }
//        if (i == originRightP) {
//            //i轮一遍下来，三数和与目标值差值都小于0，左指针右移
//            leftP++;
//        }
//        findClosestInternal(nums, leftP, rightP, target);
//    }
    //====================================================================================================解法2，错误的双指针，超时限

    private int minAbsGap = Integer.MAX_VALUE;
    private int minAbsGapSum = 0;

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int leftP = i + 1;
            int rightP = nums.length - 1;
            while (leftP < rightP) {
                int sum = nums[i] + nums[leftP] + nums[rightP];
                int gap = Math.abs(sum - target);
                if (gap < minAbsGap) {
                    minAbsGap = gap;
                    minAbsGapSum = sum;
                }
                if (sum > target) {
                    rightP--;
                } else if (sum == target) {
                    return sum;
                } else {
                    leftP++;
                }
            }
        }
        return minAbsGapSum;
    }
    //todo: 三数之和，要遍历所有可能的三数的组合，一开始的想法是左右两个固定指针，中间一个游标遍历，固定指针逐步收缩窗口。但是这样会存在窗口从右缩还是从左缩的问题，也就是leftP++还是rightP--
    // 正确的做法是一个固定指针i从左往右遍历，在[i+1,n)的区间放两个游标，这样只要三数和大于target，就rightP--，小于就leftP++，简单可穷举。。。

    //todo: 前几次试过的方法一、方法二，maybe在真实面试中会通过~


}
