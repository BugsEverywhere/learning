package indi.simon.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P46Permute {

    public static void main(String[] args) {
        int[] test = new int[]{1, 2, 3};
        List result = permute(test);
        System.out.println(result);
    }

    private static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteInternal(result, nums, nums.length - 1);
        return result;
    }

    private static void permuteInternal(List<List<Integer>> result, int[] nums, int end) {
        if (end == 0) {
            List<Integer> singleResult = new ArrayList<>(nums.length);
            for (int i = 0; i < nums.length; i++) {
                singleResult.add(nums[i]);
            }
            result.add(singleResult);
            return;
        }
        int[] dealtArr = nums;
        for (int i = 0; i < end + 1; i++) {
            permuteInternal(result, dealtArr, end - 1);
            dealtArr = swap(nums, i, end);
        }
    }

    private static int[] swap(int[] nums, int i, int j) {
        int[] newArr = new int[nums.length];
        System.arraycopy(nums, 0, newArr, 0, nums.length);
        int tmp = newArr[i];
        newArr[i] = newArr[j];
        newArr[j] = tmp;
        return newArr;
    }

}

//todo: 要注意求全排列的时候，每次递归都是用新构建的数组去递归，就是上述swap方法返回新的数组，
// 不要怕增加空间复杂度