package indi.simon.learning.leetcode.gogo20220425;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz137_hinted {

    public static void main(String[] args) {
        Quiz137_hinted quiz137_hinted = new Quiz137_hinted();
        int[] nums = new int[]{0, 1, 0, 1, 0, 1, 99};
        int res = quiz137_hinted.singleNumber(nums);
        System.out.println(res);
    }

    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i <= 31; i++) {
            //统计该bit位在数组中出现的总次数，结果是1或者0，结果是3的倍数加上多出来的一个数在该位上的值
            int bitTotalCount = 0;
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
