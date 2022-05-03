package indi.simon.learning.leetcode.gogo20220502;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz167 {

    public static void main(String[] args) {
        int[] numbers = new int[]{1, 2, 7, 11, 15};
        Quiz167 quiz167 = new Quiz167();
        int[] res = quiz167.twoSum(numbers, 26);
        System.out.println(res);

    }

    public int[] twoSum(int[] numbers, int target) {
        //遍历使用每一个数作为两数之一的情况
        for (int i = 0; i < numbers.length; i++) {
            int targetV2 = target - numbers[i];
            int targetIndex = Arrays.binarySearch(numbers, i + 1, numbers.length, targetV2);
            if (targetIndex >= i + 1) {
                int[] res = new int[2];
                res[0] = i + 1;
                res[1] = targetIndex + 1;
                return res;
            }
        }
        return null;
    }


}
