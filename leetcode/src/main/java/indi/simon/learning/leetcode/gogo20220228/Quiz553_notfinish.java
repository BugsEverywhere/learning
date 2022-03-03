package indi.simon.learning.leetcode.gogo20220228;

import java.util.List;
import java.util.Stack;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz553_notfinish {

    public static void main(String[] args


    ) {

    }

    private double[][] mem;

    private double maxValSoFar = Double.MIN_VALUE;

    public String optimalDivision(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        mem = new double[nums.length][nums.length];
        double maxValSoFar = Double.MIN_VALUE;


        return null;
    }

    private void divideValue(int[] nums, int currentIndex, List<Double> numsInLastBracket) {
        //最后一个数了，只有可能加右括号，或者不加任何括号，然后直接返回
        if (currentIndex >= nums.length - 1) {
//            if (leftBracketCount == 1) {
//                //加右括号
//                values.add(valFromLastLeftBracketSoFar / nums[currentIndex]);
//            } else {
//                //不加括号
//                values.add((double) nums[currentIndex]);
//            }
//            double accDivide = values.get(0);
//            for (Double s : values) {
//                accDivide = accDivide / s;
//            }
//            if (accDivide > maxValSoFar) {
//                maxValSoFar = accDivide;
//            }
            return;
        }

        //本位置放左括号，最多有可能放nums.length-1-currentIndex个左括号，遍历一下每一种可能往下递归
        for (int i = 0; i <= nums.length - 1 - currentIndex; i++) {
//            divideValue(nums, currentIndex+1, leftBracketCount+1, ,);
        }


    }

}
