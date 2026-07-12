package indi.simon.learning.复习.排序;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 * <p>
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * <p>
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [10,2]
 * 输出："210"
 * 示例 2：
 * <p>
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 109
 */
public class Quiz179_排序后组成最大整数 {

    public static void main(String[] args) {
        Quiz179_排序后组成最大整数 quiz179 = new Quiz179_排序后组成最大整数();
        int[] nums = new int[]{700000000,500000000};
        String res = quiz179.largestNumber(nums);
        System.out.println(res);
    }

    public String largestNumber(int[] nums) {
        int n = nums.length;
        // 转换成包装类型，以便传入 Comparator 对象（此处为 lambda 表达式）
        Integer[] numsArr = new Integer[n];
        for (int i = 0; i < n; i++) {
            numsArr[i] = nums[i];
        }

        Arrays.sort(numsArr, (x, y) -> {
            //todo: sx是刚刚比x大的十的整次幂，例如10，100，1000，10000
            // sy是刚刚比y大的十的整次幂
            long sx = 10, sy = 10;
            while (sx <= x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            //todo: yFirst表示y前x后，xFirst表示x前y后。直接比较yFirst和xFirst大小即可
            // 如果使用字符串拼接之后，使用Long.parseLong来得到yFirst、xFirst，则会有数字太大时的异常
            long yFirst = sx * y + x;
            long xFirst = sy * x + y;
            return Long.compare(yFirst, xFirst);
        });

        if (numsArr[0] == 0) {
            return "0";
        }
        StringBuilder ret = new StringBuilder();
        for (int num : numsArr) {
            ret.append(num);
        }
        return ret.toString();
    }

}
