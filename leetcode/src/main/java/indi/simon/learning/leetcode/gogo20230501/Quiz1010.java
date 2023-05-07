package indi.simon.learning.leetcode.gogo20230501;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1010 {

    public static void main(String[] args) {
        Quiz1010 quiz1010 = new Quiz1010();
        int res = quiz1010.numPairsDivisibleBy60(new int[]{30, 20, 150, 100, 40});
        System.out.println(res);
    }

    public int numPairsDivisibleBy60(int[] time) {
        //保存所有歌曲对60的余数的个数
        Map<Integer, Integer> remainCnt = new HashMap<>();

        for (int singleTime : time) {
            remainCnt.merge(singleTime % 60, 1, Integer::sum);
        }

        int pairs = 0;
        for (int singleTime : time) {
            //本数对60的余数
            int remain = singleTime % 60;
            //配对的数对60的余数
            int pairRemain = 60 - (singleTime % 60);
            if (remain == pairRemain || remain == 0) {
                //remain和pairRemain相等的情况，下面两者都认为是相等
                //如果singleTime对60取余是30，那么假如某数对60的余数也是30，两者可以配对，
                // 如果singleTime对60取余是0，那么假如某数对60的余数是0或者60，两者可以配对
                if (remainCnt.containsKey(remain) && remainCnt.get(remain) > 1) {
                    int pairCnt = 0;
                    for (int i = remainCnt.get(remain) - 1; i >= 1; i--) {
                        pairCnt += i;
                    }
                    pairs += pairCnt;
                }
            } else {
                //remain和pairRemain不相等的情况
                //前一个条件保证他自己仍然健在，之前没有配对过（因为配对完就被remove了），后一个条件保证与他配对的健在
                if (remainCnt.containsKey(remain) && remainCnt.containsKey(pairRemain)) {
                    int pairCnt = remainCnt.get(remain) * remainCnt.get(pairRemain);
                    pairs += pairCnt;
                }
            }
            //两者都被考虑过了，以后遍历到就不用考虑了
            remainCnt.remove(remain);
            remainCnt.remove(pairRemain);
        }
        return pairs;
    }


}
