package indi.simon.learning.leetcode.tobereviewed;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P15ThreeNumSumEqualsZero {

    public static void main(String[] args) {

        int[] test = {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
        List<List<Integer>> result = threeSum(test);
        System.out.println(result);

    }

    private static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        if (nums[0] > 0 || nums[nums.length - 1] < 0) {
            return new ArrayList<>();
        }
        int i = 0;
        List<List<Integer>> list = new ArrayList<>();
        while (i < nums.length - 2) {
            int z = nums.length - 1;
            if (i > 0 && nums[i - 1] == nums[i]) {
                i++;
                continue;
            }
            if (nums[i] > 0) {
                break;
            }
            int j = i + 1;
            Integer lastJ = null;
            Integer lastZ = null;
            while (z > j) {
                if (lastJ != null && lastJ.equals(nums[j])) {
                    lastJ = nums[j];
                    j++;
                    continue;
                }
                if (lastZ != null && lastZ.equals(nums[z])) {
                    lastZ = nums[z];
                    z--;
                    continue;
                }
                if (nums[i] + nums[j] + nums[z] == 0) {
                    List<Integer> integerList = new ArrayList<>();
                    integerList.add(nums[i]);
                    integerList.add(nums[j]);
                    integerList.add(nums[z]);
                    list.add(integerList);
                    lastJ = nums[j];
                    lastZ = nums[z];
                    j++;
                    z--;
                    continue;
                }
                if (nums[i] + nums[j] + nums[z] > 0) {
                    lastZ = nums[z];
                    z--;
                }
                if (nums[i] + nums[j] + nums[z] < 0) {
                    lastJ = nums[j];
                    j++;
                }
            }
            i++;
        }
        return list;
    }
}


//todo: 排序之后三指针！i从0开始逐个往后遍历整个数组，在i遍历的每一轮循环中，z从最后开始往前遍历，j从i+1
// 开始往后遍历。中间各种判断三数和小于零大于零该如何移动指针的情形，好(nue)玩(xin)极了！


