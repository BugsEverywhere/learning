package indi.simon.learning.leetcode.gogo20230814;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Chen Zhuo on 2023/8/20.
 */
public class Quiz2657 {

    public static void main(String[] args) {
        Quiz2657 quiz2657 = new Quiz2657();
        int[] res = quiz2657.findThePrefixCommonArray(new int[]{2, 3, 1}, new int[]{3, 1, 2});
        System.out.println(res);
    }

    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        //记录A的下标i的前缀的所有元素的位图
        Map<Integer, Long> aPrefixMap = new HashMap<>();
        //记录B的下标i的前缀的所有元素的位图
        Map<Integer, Long> bPrefixMap = new HashMap<>();

        int[] res = new int[A.length];

        for (int i = 0; i < A.length; i++) {
            if (i == 0) {
                aPrefixMap.put(i, (long) 1 << A[i]);
                bPrefixMap.put(i, (long) 1 << B[i]);
            } else {
                aPrefixMap.put(i, aPrefixMap.get(i - 1) | (long) 1 << A[i]);
                bPrefixMap.put(i, bPrefixMap.get(i - 1) | (long) 1 << B[i]);
            }
            //数一下有多少个1
            long publicOne = aPrefixMap.get(i) & bPrefixMap.get(i);
            int thisRes = 0;
            while(publicOne > 0){
                long one = publicOne & 1;
                if(one == 1){
                    thisRes++;
                }
                publicOne = publicOne >> 1;
            }
            res[i] = thisRes;
        }

        return res;
    }
}
