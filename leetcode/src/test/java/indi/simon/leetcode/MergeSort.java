package indi.simon.leetcode;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] testArr = new int[]{10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
        sort(testArr, 0, testArr.length - 1);
        System.out.println(testArr);

    }

    private static void sort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + ((end - start) >> 1);
        sort(nums, mid + 1, end);
        sort(nums, start, mid);
        merge(nums, start, mid, end);
    }

    private static void merge(int[] nums, int start, int mid, int end) {
        int j = mid + 1;
        int i = start;
        int[] tmpArr = new int[end - start + 1];
        int x;
        for (x = 0; i <= mid && j <= end; x++) {
            if (nums[i] <= nums[j]) {
                tmpArr[x] = nums[i];
                i++;
            } else {
                tmpArr[x] = nums[j];
                j++;
            }
        }
        //通过判断对方是否到达终点来判断自己是否有剩余元素需要拷贝到tmpArr
        if (j == end + 1) {
            System.arraycopy(nums, i, tmpArr, x, mid - i + 1);
        } else if (i == mid + 1) {
            System.arraycopy(nums, j, tmpArr, x, end - j + 1);
        }
        System.arraycopy(tmpArr, 0, nums, start, end - start + 1);
    }
}
