package indi.simon.learning.leetcode.gogo20230327;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2367 {

    public static void main(String[] args) {
        Quiz2367 quiz2367 = new Quiz2367();
        int res = quiz2367.arithmeticTriplets(new int[]{4,5,6,7,8,9}, 2);
        System.out.println(res);
    }

    public int arithmeticTriplets(int[] nums, int diff) {

        boolean[] exist = new boolean[nums[nums.length - 1] + 1];

        for (int num : nums) {
            exist[num] = true;
        }

        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] + diff < exist.length && exist[nums[i] + diff]) && (nums[i] + diff + diff < exist.length && exist[nums[i] + diff + diff])) {
                res++;
            }
        }

        return res;
    }

}
