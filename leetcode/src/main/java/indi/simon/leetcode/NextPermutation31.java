package indi.simon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class NextPermutation31 {

    public static void main(String[] args) {

        int[] test = new int[]{1, 2};

        nextPermutation(test);

        System.out.println(Arrays.toString(test));

    }

    public static void nextPermutation(int[] nums) {
        if (nums.length < 2) {
            return;
        }
        boolean backwardsBiggerOrSmaller;
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            backwardsBiggerOrSmaller = false;
        } else {
            backwardsBiggerOrSmaller = true;
        }
        int j;
        for (j = nums.length - 1; j >= 1; --j) {
            if (backwardsBiggerOrSmaller) {
                //倒排升序
                if (nums[j] <= nums[j - 1]) {
                    //下一个大于等于当前的
                    continue;
                } else {
                    //下一个比当前的小,说明当前的是极大值
                    int justBiggerThanNextOneIndex = findJustBiggerThanIndex(nums, nums[j - 1], j);
                    swap(nums, justBiggerThanNextOneIndex, j - 1);
                    sortSuffix(nums, j);
                    return;
                }
            } else {
                //倒排降序
//                if (nums[j] >= nums[j - 1]) {
//                    //下一个小于等于当前的
//                    continue;
//                } else {
//                    //下一个大于当前的，说明当前值是极小值
//                    if (nums[j] == nums[j + 1]) {
//                        swap(nums, j, j + 2);
//                    } else {
//                        swap(nums, j, j + 1);
//                    }
//                    break;
//                }
                swap(nums, nums.length - 1, nums.length - 2);
                return;
            }
        }
        if (j == 0 && backwardsBiggerOrSmaller) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            Collections.reverse(list);
            for (int i = 0; i < nums.length; i++) {
                nums[i] = list.get(i);
            }
        } else if (j == 0 && !backwardsBiggerOrSmaller) {
            swap(nums, nums.length - 1, nums.length - 2);
        }
    }

    private static int findJustBiggerThanIndex(int[] arr, int target, int peekBigPointIndex) {
        int lastIndex = peekBigPointIndex;
        for (int i = 0; i < arr.length - peekBigPointIndex; i++) {
            if (arr[peekBigPointIndex + i] > target) {
                lastIndex = peekBigPointIndex + i;
            } else {
                return lastIndex;
            }
        }
        return arr.length - 1;
    }

    private static void swap(int[] arr, int aIndex, int bIndex) {
        int tmp = arr[aIndex];
        arr[aIndex] = arr[bIndex];
        arr[bIndex] = tmp;
    }

    private static void sortSuffix(int[] arr, int startSortIndex) {
        int[] subArr = new int[arr.length - startSortIndex];
        System.arraycopy(arr, startSortIndex, subArr, 0, arr.length - startSortIndex);
        Arrays.sort(subArr);
        System.arraycopy(subArr, 0, arr, startSortIndex, subArr.length);
    }


}


//todo: 思路，从后往前遍历，如果从后往前一开始是升序，则在遇到第一个极大值时，将极大值与极大值的前一位交换，极大值
// 后面的所有数字按升序排序，比如 1,3,4,6,8,9,7,5,2     将9与8交换，然后将8,7,5,2按升序排序得到2，5，7，8，得到
// 结果：1，3，4，6，9，2，5，7，8
// 如果从后往前遍历一开始是降序，则在遇到第一个极小值时，将极小值与极小值后一位交换即可，比如1,3,4,6,8,7,2,5,9，
// 2为从后往前遍历的第一个极小值，则将2与5交换即可。