package indi.simon.learning.leetcode.tobereviewed;

import java.util.Arrays;

public class P27 {

    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int targetCount = 0;
        for (int i = 0; i < nums.length; i++) {
            //todo val很可能不在nums中，因此不要用小于，直接使用!=跳过
            if (nums[i] != val) {
                continue;
            }
            int j;
            for (j = i + 1; j < nums.length && i < nums.length; j++) {
                if (val == nums[j]) {
                    continue;
                }
                nums[i] = nums[j];
                i++;
            }
            //todo 窗口大小在里层循环外面计算，这样是因为有可能val是排序后的数组中最大最后的元素
            targetCount = j - i;
            break;
        }
        return nums.length - targetCount;
    }
}

//todo 也是双指针，快指针和慢指针，因为已经排好序，当慢指针找到val的时候，快指针出发，找到下一个比val大的数，找到之后j-i即为数组中val的个数，将nums[j]赋值给nums[i]即可