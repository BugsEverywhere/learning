package indi.simon.learning.leetcode.gogo20220328;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz47 {

    public static void main(String[] args) {
        Quiz47 quiz47 = new Quiz47();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        List<List<Integer>> res = quiz47.permuteUnique(nums);
        System.out.println(res);
    }

    private List<List<Integer>> listList;


    public List<List<Integer>> permuteUnique(int[] nums) {
        listList = new ArrayList<>();
        if (nums.length == 0) {
            return listList;
        }
        Arrays.sort(nums);
        permuteUniqueInternal(nums, 0, new Integer[nums.length], 0);
        return listList;
    }

    private void permuteUniqueInternal(int[] nums, int targetIndex, Integer[] newArr, int lastPutIndex) {
        if (targetIndex >= nums.length) {
            listList.add(Arrays.asList(newArr));
            return;
        }
        //遍历递归将该数放在newArr中每一个位置的所有情况
        int cursorIndex;
        if (targetIndex - 1 >= 0 && nums[targetIndex - 1] == nums[targetIndex]) {
            cursorIndex = lastPutIndex + 1;
        } else {
            cursorIndex = 0;
        }

        for (; cursorIndex < newArr.length; cursorIndex++) {
            Integer[] newArrThisRound = Arrays.copyOf(newArr, newArr.length);
            if (newArrThisRound[cursorIndex] == null) {
                newArrThisRound[cursorIndex] = nums[targetIndex];
                permuteUniqueInternal(nums, targetIndex + 1, newArrThisRound, cursorIndex);
            }
        }
    }

}
