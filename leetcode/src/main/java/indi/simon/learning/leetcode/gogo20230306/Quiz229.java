package indi.simon.learning.leetcode.gogo20230306;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz229 {

    public static void main(String[] args) {

    }

    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> appearanceCount = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        for (int num : nums) {
            appearanceCount.merge(num, 1, Integer::sum);
        }

        for (Map.Entry<Integer, Integer> entry : appearanceCount.entrySet()) {
            if (entry.getValue() > (nums.length / 3)) {
                res.add(entry.getKey());
            }
        }

        return res;
    }

}
