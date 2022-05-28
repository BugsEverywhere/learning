package indi.simon.learning.leetcode.gogo20220516;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz436 {

    public static void main(String[] args) {
        Quiz436 quiz436 = new Quiz436();
        int[][] intervals = new int[][]{{1, 1}, {3, 4}};
        int[] res = quiz436.findRightInterval(intervals);
        System.out.println(res);
    }

    public int[] findRightInterval(int[][] intervals) {
        Map<Integer, Integer> leftSortMap = new HashMap<>();
        int[] leftArr = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            leftArr[i] = intervals[i][0];
            leftSortMap.put(intervals[i][0], i);
        }
        Arrays.sort(leftArr);
        int[] res = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            int rightNum = intervals[i][1];
            int okStartIndex = findBiggerEquals(leftArr, rightNum);
            if (okStartIndex == -1) {
                res[i] = -1;
            } else {
                res[i] = leftSortMap.get(leftArr[okStartIndex]);
            }
        }
        return res;
    }

    private int findBiggerEquals(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (arr[mid] >= target && mid - 1 >= 0 && arr[mid - 1] < target) {
                return mid;
            } else if (arr[mid] >= target && mid == 0) {
                return mid;
            } else if (arr[mid] >= target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }


}
