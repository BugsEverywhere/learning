package indi.simon.learning.leetcode.前缀和;


import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 前缀和+哈希表
public class Quiz523_hinted {

    public static void main(String[] args) {
        Quiz523_hinted quiz523Hinted = new Quiz523_hinted();
        boolean res = quiz523Hinted.checkSubarraySum(new int[]{1, 0}, 2);
        System.out.println(res);
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        //key为前缀和，val为该前缀和首次出现的下标
        Map<Integer, Integer> preSumMap = new HashMap<>();
        //前缀和数组
        int[] preSum = new int[nums.length];
        preSum[0] = nums[0];
        preSumMap.put(preSum[0] % k, 0);
        for (int i = 1; i < nums.length; i++) {
            //todo:此处注意相邻元素(i与i-1)要拎出来单独看
            //先校验相邻元素是否能凑个整，如果能，直接返回
            if ((nums[i - 1] + nums[i]) % k == 0) {
                return true;
            }
            //todo:看完i-1，再看i-1之前的，这时候才用到前缀和与哈希表
            //后面是看i-1之前
            preSum[i] = preSum[i - 1] + nums[i];
            int remainder = preSum[i] % k;
            if (remainder == 0) {
                //如果某下标的前缀和本身就可以整除k，满足题意，直接返回
                return true;
            }

            //校验之前是否有过对k取余等于该remainder的前缀和，并且该下标距离当前i下标大于1，至少隔了两个
            if (preSumMap.containsKey(remainder) && i - preSumMap.get(remainder) > 1) {
                //如果有，说明从该下标之后，到i之间，数组和增加了整数倍，满足题意
                return true;
            } else {
                //如没有，加入该下标
                preSumMap.put(remainder, i);
            }
        }

        return false;
    }

}
