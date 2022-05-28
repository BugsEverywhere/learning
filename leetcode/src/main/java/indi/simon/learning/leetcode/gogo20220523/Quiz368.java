package indi.simon.learning.leetcode.gogo20220523;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz368 {

    public static void main(String[] args) {
        Quiz368 quiz368 = new Quiz368();
        int[] nums = new int[]{1,2,4,8,7,6,5};
        List<Integer> res = quiz368.largestDivisibleSubset(nums);
        System.out.println(res);
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> dp = new ArrayList<>();
        List<Integer> zero = new ArrayList<>();
        dp.add(zero);
        zero.add(nums[0]);

        List<Integer> maxLengthArr = zero;
        for (int i = 1; i < nums.length; i++) {
            List<Integer> thisArr = new ArrayList<>();
            for (int j = i - 1; j >= 0; j--) {
                List<Integer> jArr = dp.get(j);
                int maxNumOfJArr = jArr.get(jArr.size() - 1);
                if (nums[i] % maxNumOfJArr == 0 && jArr.size() + 1 > thisArr.size()) {
                    thisArr = new ArrayList<>(jArr);
                    thisArr.add(nums[i]);
                }
            }
            if (thisArr.size() == 0) {
                thisArr.add(nums[i]);
            }
            dp.add(thisArr);
            if (thisArr.size() > maxLengthArr.size()) {
                maxLengthArr = thisArr;
            }
        }
        return maxLengthArr;
    }
}
