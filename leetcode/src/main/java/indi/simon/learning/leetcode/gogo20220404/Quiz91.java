package indi.simon.learning.leetcode.gogo20220404;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz91 {

    public static void main(String[] args) {
        Quiz91 quiz91 = new Quiz91();
        int res = quiz91.numDecodings("111111111111111111111111111111111111111111111");
        System.out.println(res);
    }

    private int[] mem;

    public int numDecodings(String s) {
        mem = new int[s.length()];
        Arrays.fill(mem, -1);
        return numDecodingsInternal(s, 0, new ArrayList<>());
    }

    private int numDecodingsInternal(String s, int index, List<String> path) {
        if (index >= s.length()) {
            return 1;
        }

        if (mem[index] > 0) {
            return mem[index];
        }

        //只看本位
        int thisPosCount = 0;
        if (s.charAt(index) != '0') {
            path.add(String.valueOf(s.charAt(index)));
            thisPosCount = numDecodingsInternal(s, index + 1, new ArrayList<>(path));
            path.remove(String.valueOf(s.charAt(index)));
        }

        //本位和下一位一起看
        int thisPosPlusNextPosCount = 0;
        if (s.charAt(index) != '0' && index + 1 < s.length()) {
            int num = Integer.parseInt(String.valueOf(s.charAt(index)) + s.charAt(index + 1));
            if (num >= 10 && num <= 26) {
                path.add(String.valueOf(s.charAt(index)) + s.charAt(index + 1));
                thisPosPlusNextPosCount = numDecodingsInternal(s, index + 2, new ArrayList<>(path));
                path.remove(String.valueOf(s.charAt(index)) + s.charAt(index + 1));
            }
        }

        mem[index] = thisPosCount + thisPosPlusNextPosCount;

        return thisPosCount + thisPosPlusNextPosCount;

    }


}
