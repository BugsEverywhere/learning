package indi.simon.learning.leetcode.gogo20220425;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz179_hinted {

    public static void main(String[] args) {
        Quiz179_hinted quiz179 = new Quiz179_hinted();
        int[] nums = new int[]{3, 30, 34, 5, 9};
        String res = quiz179.largestNumber(nums);
        System.out.println(res);
    }

    public String largestNumber(int[] nums) {

        Integer[] copy = new Integer[nums.length];

        boolean onlyZero = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                onlyZero = false;
            }
            copy[i] = nums[i];
        }

        if (onlyZero) {
            return "0";
        }

        Arrays.sort(copy, (o1, o2) -> {
            String o1Str = o1.toString();
            String o2Str = o2.toString();
            String combine1 = o1Str + o2Str;
            char[] combine1CharArr = combine1.toCharArray();
            String combine2 = o2Str + o1Str;
            char[] combine2CharArr = combine2.toCharArray();

            for (int i = 0; i < combine1CharArr.length; i++) {
                if (combine1CharArr[i] != combine2CharArr[i]) {
                    return Integer.compare(combine2CharArr[i], combine1CharArr[i]);
                }
            }
            return 0;
        });

        StringBuilder stringBuilder = new StringBuilder();
        for (Integer intNum : copy) {
            stringBuilder.append(intNum);
        }

        return stringBuilder.toString();
    }

}
