package indi.simon.learning.leetcode.gogo20220627;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 只能靠记住这个方法了，面试场上临场很难想全面。。。反正就是奇数个，就交叉填充后交差；偶数个，交叉填充完之后，成对成对反向填入原数组
// 这还没考验自己进阶难度。。。。回头看完
public class Quiz324_tobeReviewed {

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,5,5,5,6,6,6};
        Quiz324_tobeReviewed quiz324TobeReviewed = new Quiz324_tobeReviewed();
        quiz324TobeReviewed.wiggleSort(nums);
        System.out.println(nums);
    }

    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int j = nums.length % 2 == 0 ? nums.length / 2 : nums.length / 2 + 1;
        int mid = j;
        List<Integer> list = new ArrayList<>();
        int i = 0;
        for (; i < mid && j < nums.length; i++, j++) {
            list.add(nums[i]);
            list.add(nums[j]);
        }
        //如果数组个数为偶数，后半段填到前半段，前半段填到后半段即可
        if (nums.length % 2 == 0) {
            for (int index = 0; index < nums.length; index += 2) {
                nums[index] = list.get(nums.length - index - 2);
                nums[index + 1] = list.get(nums.length - index - 1);
            }
        } else {
            //总数是奇数的情况，j会先触达nums.length，所以要把i补在最后一位
            list.add(nums[i]);

            for (int index = 0; index < nums.length; index++) {
                nums[index] = list.get(index);
            }

        }
    }

}
