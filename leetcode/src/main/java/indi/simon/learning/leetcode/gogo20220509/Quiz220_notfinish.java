package indi.simon.learning.leetcode.gogo20220509;


import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz220_notfinish {

    public static void main(String[] args) {
        int[] nums = new int[]{1};
        Quiz220_notfinish quiz220Notfinish = new Quiz220_notfinish();
        boolean res = quiz220Notfinish.containsNearbyAlmostDuplicate(nums, 1, 1);
        System.out.println(res);
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1) {
            return false;
        }

        //窗口大小大于原数组
        if (k + 1 > nums.length) {
            Arrays.sort(nums);
            for (int index = 0; index < nums.length; index++) {
                if (index + 1 < nums.length && Math.abs((long) nums[index + 1] - (long) nums[index]) <= t) {
                    return true;
                }
            }
            return false;
        }


        int i = 0;
        int j = k;

        int[] subArr = new int[k + 1];
        System.arraycopy(nums, 0, subArr, 0, k + 1);
        Arrays.sort(subArr);

        //窗口移动
        while (true) {
            for (int index = 0; index <= k; index++) {
                if (index + 1 <= k && Math.abs((long) subArr[index + 1] - (long) subArr[index]) <= t) {
                    return true;
                }
            }
            i++;
            j++;
            if (j < nums.length) {
                updateSortedList(nums[i - 1], subArr, nums[j]);
            } else {
                break;
            }
        }

        return false;
    }

    private void updateSortedList(int pastNum, int[] subArr, int newNum) {
        int pastNumIndex = Arrays.binarySearch(subArr, pastNum);
        subArr[pastNumIndex] = newNum;
        Arrays.sort(subArr);
    }


}
