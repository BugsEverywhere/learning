package indi.simon.learning.leetcode.gogo20230116;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1814 {

    public static void main(String[] args) {
        Quiz1814 quiz1814 = new Quiz1814();
        int res = quiz1814.countNicePairs(new int[]{42, 11, 1, 97});
        System.out.println(res);
    }

    public int countNicePairs(int[] nums) {
        //todo: 第一反应还没想到用哈希表，等式需要变换一下
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int revMinus = num - rev(num);
            map.merge(revMinus, 1, Integer::sum);
        }
        //todo: 以后凡是题目提醒了结果会特别大的，多留个心眼，int中间变量都用Long来保存
        long res = 0;
        for (Map.Entry<Integer, Integer> singleRevMinus : map.entrySet()) {
            int val = singleRevMinus.getValue() - 1;
            //todo: 同上
            long singleRes = 0;
            while (val >= 0) {
                singleRes += val;
                val--;
            }
            res += singleRes;
        }
        return (int) (res % (Math.pow(10, 9) + 7));
    }

    private int rev(int num) {
        String numStr = Integer.toString(num);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = numStr.length() - 1; i >= 0; i--) {
            stringBuilder.append(numStr.charAt(i));
        }
        return Integer.parseInt(stringBuilder.toString());
    }

}
