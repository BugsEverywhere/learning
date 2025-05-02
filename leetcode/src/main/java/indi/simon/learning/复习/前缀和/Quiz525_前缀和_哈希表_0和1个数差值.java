package indi.simon.learning.复习.前缀和;


import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 前缀和+哈希表
public class Quiz525_前缀和_哈希表_0和1个数差值 {

    public static void main(String[] args) {
        Quiz525_前缀和_哈希表_0和1个数差值 quiz525Hinted = new Quiz525_前缀和_哈希表_0和1个数差值();
        int res = quiz525Hinted.findMaxLength(new int[]{0, 1, 1, 0, 1, 1, 1, 0});
        System.out.println(res);
    }

    public int findMaxLength(int[] nums) {
        //登记簿
        Map<Integer, Integer> gapMap = new HashMap<>();
        //哨兵
        gapMap.put(0, -1);

        int oneCntAcc = 0;
        int zeroCntAcc = 0;

        int maxWidth = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCntAcc++;
            } else {
                oneCntAcc++;
            }
            int gap = oneCntAcc - zeroCntAcc;
            if (gapMap.containsKey(gap)) {
                maxWidth = Math.max(maxWidth, i - gapMap.get(gap));
            } else {
                gapMap.put(gap, i);
            }
        }

        return maxWidth;
    }

}
