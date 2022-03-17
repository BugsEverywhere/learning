package indi.simon.learning.leetcode.gogo20220314;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz565 {


    public static void main(String[] args) {
        int[] arr = new int[]{5, 4, 0, 3, 1, 6, 2};
        Quiz565 quiz565 = new Quiz565();
        int res = quiz565.arrayNesting(arr);
        System.out.println(res);
    }

    private Set<Integer> mem;


    public int arrayNesting(int[] nums) {

        mem = new HashSet<>();

        int maxLengthSoFar = Integer.MIN_VALUE;

        for (int j : nums) {
            if (mem.contains(j)) {
                continue;
            }
            Set<Integer> set = new HashSet<>();
            int num = j;
            while (!set.contains(num)) {
                mem.add(num);
                set.add(num);
                num = nums[num];
            }
            if (set.size() > maxLengthSoFar) {
                maxLengthSoFar = set.size();
            }
        }

        return maxLengthSoFar;
    }

}
