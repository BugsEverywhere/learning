package indi.simon.learning.leetcode.gogo20220314;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz18 {

    public static void main(String[] args) {

    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        Set<String> records = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int leftP = j + 1;
                int rightP = nums.length - 1;
                while (leftP < rightP) {
                    if (nums[i] + nums[j] + nums[leftP] + nums[rightP] == target) {
                        if (!records.contains(nums[i] + "_" + nums[j] + "_" + nums[leftP] + "_" + nums[rightP])) {
                            List<Integer> list = new ArrayList<>();
                            res.add(list);
                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(nums[leftP]);
                            list.add(nums[rightP]);
                            records.add(nums[i] + "_" + nums[j] + "_" + nums[leftP] + "_" + nums[rightP]);
                        }
                        leftP++;
                        rightP--;
                    } else if (nums[i] + nums[j] + nums[leftP] + nums[rightP] < target) {
                        leftP++;
                    } else if (nums[i] + nums[j] + nums[leftP] + nums[rightP] > target) {
                        rightP--;
                    }
                }
            }
        }
        return res;
    }

}
