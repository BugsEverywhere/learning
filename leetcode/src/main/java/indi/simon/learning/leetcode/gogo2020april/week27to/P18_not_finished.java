package indi.simon.learning.leetcode.gogo2020april.week27to;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P18_not_finished {

    public static void main(String[] args) {

    }

    private static List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();

        if (nums.length == 0) {
            return result;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            //
            if (i > 0 && nums[i + 3] == nums[i - 1]) {
                continue;
            }






        }


        return null;
    }


}
