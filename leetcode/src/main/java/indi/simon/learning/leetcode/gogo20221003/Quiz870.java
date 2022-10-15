package indi.simon.learning.leetcode.gogo20221003;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz870 {

    public static void main(String[] args) {
        Quiz870 quiz870 = new Quiz870();
        int[] nums1 = new int[]{2, 0, 4, 1, 2};
        int[] nums2 = new int[]{1, 3, 0, 0, 2};
        int[] res = quiz870.advantageCount(nums1, nums2);
        System.out.println(res);
    }

    public int[] advantageCount(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        boolean[] used = new boolean[nums1.length];

        int[] res = new int[nums1.length];

        for (int index = 0; index < res.length; index++) {
            int j = findJustBiggerNumIndex(nums1, nums2[index], used);
            if (j == -1) {
                //如果在nums1没有找到比nums2该数更大的，那么就放一个最小的数在此处
                for (int i = 0; i < nums1.length; i++) {
                    if (!used[i]) {
                        res[index] = nums1[i];
                        used[i] = true;
                        break;
                    }
                }
            } else {
                res[index] = nums1[j];
                used[j] = true;
            }
        }

        return res;
    }

    private int findJustBiggerNumIndex(int[] nums1, int target, boolean[] used) {
        int left = 0;
        int right = nums1.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums1[mid] > target) {
                if (mid - 1 >= 0 && nums1[mid - 1] > target) {
                    right = mid - 1;
                } else if (mid == 0) {
                    while (mid < nums1.length) {
                        if (!used[mid]) {
                            used[mid] = true;
                            return mid;
                        }
                        mid++;
                    }
                    //todo: 第二次提交忘了这里，如果从Mid往后找都没有
                    return -1;
                } else if (nums1[mid - 1] <= target) {
                    while (mid < nums1.length) {
                        if (!used[mid]) {
                            used[mid] = true;
                            return mid;
                        }
                        mid++;
                    }
                    return -1;
                }
            } else if (nums1[mid] <= target) {
                left = mid + 1;
            }

        }
        return -1;
    }

}
