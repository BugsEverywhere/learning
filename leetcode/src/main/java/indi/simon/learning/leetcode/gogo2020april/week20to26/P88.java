package indi.simon.learning.leetcode.gogo2020april.week20to26;

public class P88 {

    public static void main(String[] args) {

        merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);

    }


    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        if (nums2.length == 0) {
            return;
        }

        int index1 = 0;
        int index2 = 0;

        int[] mergedArr = new int[nums1.length];

        while (index1 <= m && index2 <= n) {

            //todo: 任何一个数组先到岸的情况，在循环中先做判断
            if (index1 == m && index2 < n) {
                //nums1到岸
                mergedArr[index1 + index2] = nums2[index2];
                index2++;
                continue;
            } else if (index2 == n && index1 < m) {
                //nums2到岸
                mergedArr[index1 + index2] = nums1[index1];
                index1++;
                continue;
            } else if (index1 == m && index2 == n) {
                break;
            }


            if (nums1[index1] <= nums2[index2] && (index1 == 0 || nums1[index1] >= nums1[index1 - 1])) {
                mergedArr[index1 + index2] = nums1[index1];
                index1++;
            } else {
                mergedArr[index1 + index2] = nums2[index2];
                index2++;
            }
        }
        System.arraycopy(mergedArr, 0, nums1, 0, mergedArr.length);
    }

}
