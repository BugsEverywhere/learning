package indi.simon.learning.leetcode.gogo20221226;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz442 {

    public static void main(String[] args) {

    }

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int[] count = new int[nums.length];

        for (int num : nums) {
            count[num - 1]++;
            if (count[num - 1] > 1) {
                res.add(num);
            }
        }

        return res;
    }

}
