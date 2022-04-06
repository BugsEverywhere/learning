package indi.simon.learning.leetcode.gogo20220404;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz41 {

    public static void main(String[] args) {
        Quiz41 quiz41 = new Quiz41();
        int[] nums = new int[]{3,4,-1,1};
        int res = quiz41.firstMissingPositive(nums);
        System.out.println(res);
    }

    public int firstMissingPositive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();

        int threshold = 1;
        for (int num : nums) {
            numSet.add(num);
            if (num == threshold) {
                while (numSet.contains(threshold)) {
                    threshold++;
                }
            }
        }

        return threshold;
    }
}
