package indi.simon.learning.复习.排序;

import java.util.Arrays;

/**
 * 给定一个无序的数组 nums，返回 数组在排序之后，相邻元素之间最大的差值 。如果数组元素个数小于 2，则返回 0 。
 * <p>
 * 您必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 * 示例 2:
 * <p>
 * 输入: nums = [10]
 * 输出: 0
 * 解释: 数组元素个数小于 2，因此返回 0。
 * 提示:
 * <p>
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 */
//todo: int数字的基数排序，可以实现O(N)复杂度的排序，将所有num从个位开始，每一个数位进行排序
public class Quiz164_排序后相邻数字的最大间距 {

    public static void main(String[] args) {
        Quiz164_排序后相邻数字的最大间距 quiz164_排序后相邻数字的最大间距 = new Quiz164_排序后相邻数字的最大间距();
        int res = quiz164_排序后相邻数字的最大间距.maximumGap(new int[]{88, 107, 2, 43, 26, 77});
        System.out.println(res);
    }

    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        //先排序
        //记录当前分桶的数位，个位、十位、百位......
        long exp = 1;
        //求出原数组中的最大数
        int maxVal = Arrays.stream(nums).max().getAsInt();
        //使用最大数是否大于等于十的整数次幂的方式判断是否将所有位数都排好序了，简洁高效
        while (maxVal >= exp) {
            //用于记录nums中，当前数位在1~10上的分布情况
            int[] cnt = new int[10];
            //遍历所有数，将其在当前数位上的数出现次数加1
            for (int num : nums) {
                int digit = (num / (int) exp) % 10;
                cnt[digit]++;
            }
            //将cnt中各位从前往后累加，得到的是当前数位上，数组中小于等于当前数的数有多少个，
            // 例如cnt[3]代表nums中当前数位小于等于3的数有几个
            for (int i = 1; i < 10; i++) {
                cnt[i] += cnt[i - 1];
            }
            //用于保存排序后的数组，长度等于nums的长度n
            int[] buf = new int[n];
            //开始遍历nums
            for (int i = n - 1; i >= 0; i--) {
                //得到当前数num在当前数位上的值digit
                int digit = (nums[i] / (int) exp) % 10;
                //将num放入buf的对应位置，依据是nums当前数位上小于等于digit的数有cnt[digit]个，那么就把num放到排序后数组的cnt[digit]-1的位置
                buf[cnt[digit] - 1] = nums[i];
                //同时，nums中未排序的数在当前数位上小于等于digit的数就减一（因为刚刚排了一个），
                // 这意味着如果后续还有num1在当前数位上也是digit，那么它将排到刚刚num前面一位
                cnt[digit]--;
            }
            //一趟下来，在当前数位上，nums就排好序了，将排好序的结果复制到原数组中
            System.arraycopy(buf, 0, nums, 0, n);
            exp *= 10;
        }

        //排序后找到最大间距
        int ret = 0;
        for (int i = 1; i < n; i++) {
            ret = Math.max(ret, nums[i] - nums[i - 1]);
        }
        return ret;
    }

}
