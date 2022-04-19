package indi.simon.learning.leetcode.gogo20220418;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz128 {

    public static void main(String[] args) {
        Quiz128 quiz128 = new Quiz128();
        int[] arr = new int[]{};
        int res = quiz128.longestConsecutive(arr);
        System.out.println(res);
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();

        for (int num : nums) {
            numSet.add(num);
        }

        int maxConsecutiveCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!numSet.contains(nums[i])) {
                //已经探索过该数
                continue;
            }

            numSet.remove(nums[i]);

            int consecutiveCount = 1;
            //往前探索
            int biggerNum = nums[i] + 1;
            while (numSet.contains(biggerNum)) {
                numSet.remove(biggerNum);
                biggerNum = biggerNum + 1;
                consecutiveCount++;
            }

            //往后探索
            int lessNum = nums[i] - 1;
            while (numSet.contains(lessNum)) {
                numSet.remove(lessNum);
                lessNum = lessNum - 1;
                consecutiveCount++;
            }

            if (consecutiveCount > maxConsecutiveCount) {
                maxConsecutiveCount = consecutiveCount;
            }
        }

        return maxConsecutiveCount;
    }

}
