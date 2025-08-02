package indi.simon.learning.复习.位运算;

/**
 * @author chenzhuo(zhiyue)
 *
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 *
 * 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
 *
 * 示例 1：
 *
 * 输入：nums = [2,2,3,2]
 * 输出：3
 * 示例 2：
 *
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
 * Related Topics
 * 位运算
 * 数组
 *
 */
public class Quiz137_数组中仅出现一次的1个数 {

    public static void main(String[] args) {
        Quiz137_数组中仅出现一次的1个数 quiz137_hinted = new Quiz137_数组中仅出现一次的1个数();
        int[] nums = new int[]{0, 1, 0, 1, 0, 1, 99};
        int res = quiz137_hinted.singleNumber(nums);
        System.out.println(res);
    }

    public int singleNumber(int[] nums) {
        int res = 0;
        //todo: 首先遍历的是32个位
        for (int i = 0; i <= 31; i++) {
            //todo: 记录该bit位在数组中出现的总次数
            int bitTotalCount = 0;
            //todo: 然后遍历的是数组所有的数
            for (int num : nums) {
                bitTotalCount += (num >> i) & 1;
            }
            //该bit位所有1的个数对3求余数，如果余数为1，说明结果数的该位肯定为1，因此将1移到该位
            if (bitTotalCount % 3 != 0) {
                res |= 1 << i;
            }
        }
        return res;
    }
}
