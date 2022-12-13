package indi.simon.learning.leetcode.gogo20221212;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz775 {

    public static void main(String[] args) {

        Quiz775 quiz775 = new Quiz775();
        int res = quiz775.minOperations(new int[]{2, 2, 4, 3, 1, 1, 5, 2, 5, 2, 5, 6, 1, 1, 6, 4, 5, 2, 5, 3}, new int[]{3, 3, 4});
        System.out.println(res);
    }

    public int minOperations(int[] nums1, int[] nums2) {

        int sum1 = 0;
        Map<Integer, Integer> numCount1 = new HashMap<>();
        for (int num : nums1) {
            sum1 += num;
            numCount1.merge(num, 1, Integer::sum);
        }

        int sum2 = 0;
        Map<Integer, Integer> numCount2 = new HashMap<>();
        for (int num : nums2) {
            sum2 += num;
            numCount2.merge(num, 1, Integer::sum);
        }

        int gap = Math.abs(sum1 - sum2);

        if (sum1 > sum2) {
            return calInternal(gap, numCount1, numCount2);
        } else if (sum1 == sum2) {
            return 0;
        } else {
            return calInternal(gap, numCount2, numCount1);
        }
    }

    private int calInternal(int gap, Map<Integer, Integer> biggerNumCount, Map<Integer, Integer> smallerNumCount) {
        int res = 0;
        int threshold1 = 6;
        int threshold2 = 1;
        while (gap > 0) {
            if (threshold1 <= 0 || threshold2 > 6) {
                return -1;
            }
            if (Math.abs(threshold1 - 1) >= Math.abs(6 - threshold2)) {
                if (biggerNumCount.get(threshold1) == null) {
                    threshold1--;
                } else {
                    gap -= (threshold1 - 1);
                    res++;
                    int newCount = biggerNumCount.get(threshold1) - 1;
                    if (newCount == 0) {
                        biggerNumCount.remove(threshold1);
                        threshold1--;
                    } else {
                        biggerNumCount.put(threshold1, newCount);
                    }
                }
            } else {
                if (smallerNumCount.get(threshold2) == null) {
                    threshold2++;
                } else {
                    gap -= (6 - threshold2);
                    res++;
                    int newCount = smallerNumCount.get(threshold2) - 1;
                    if (newCount == 0) {
                        smallerNumCount.remove(threshold2);
                        threshold2++;
                    } else {
                        smallerNumCount.put(threshold2, newCount);
                    }
                }
            }
        }
        return res;
    }

}
