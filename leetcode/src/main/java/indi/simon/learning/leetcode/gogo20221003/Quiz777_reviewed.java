package indi.simon.learning.leetcode.gogo20221003;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz777_reviewed {

    public static void main(String[] args) {
        Quiz777_reviewed quiz777TimeExceed = new Quiz777_reviewed();
        boolean res = quiz777TimeExceed.canTransform("XLXXXXXRXXRXLXXXXXXRXRXXXRXXXLXLLXXLXXXR", "LXRXRXXLXXRXXXRXXXRXLXXXLXXXXXXXXLXXLXRX");
        System.out.println(res);
    }

    public boolean canTransformV2(String start, String end) {

        char[] cArr = start.toCharArray();


        return false;
    }

    public boolean canTransformV2(char[] cArr, String end, int index) {


        return false;
    }

    //todo: 官方解法，双指针比较起始和结束字符串中的非X字符及其下标，简单明了
    public boolean canTransformOfficial(String start, String end) {
        int n = start.length();
        int i = 0, j = 0;
        while (i < n && j < n) {
            while (i < n && start.charAt(i) == 'X') {
                i++;
            }
            while (j < n && end.charAt(j) == 'X') {
                j++;
            }
            if (i < n && j < n) {
                if (start.charAt(i) != end.charAt(j)) {
                    return false;
                }
                char c = start.charAt(i);
                if ((c == 'L' && i < j) || (c == 'R' && i > j)) {
                    return false;
                }
                i++;
                j++;
            }
        }
        while (i < n) {
            if (start.charAt(i) != 'X') {
                return false;
            }
            i++;
        }
        while (j < n) {
            if (end.charAt(j) != 'X') {
                return false;
            }
            j++;
        }
        return true;
    }


    //todo: 使用字符串替换，超时。因为题目虽然说是替换字符串，其实完全没必要使用替换这种做法，费时费力
    /**
     * val: 1==true, 2==false, 3==来过
     */
    private Map<String, Integer> mem;

    public boolean canTransform(String start, String end) {
        if (mem == null) {
            mem = new HashMap<>();
            mem.put(end, 1);
        }

        if (start.equals(end) || start.replaceFirst("22", "RX").replaceFirst("11", "XL").equals(end)) {
            return true;
        }

        if (mem.containsKey(start)) {
            if (mem.get(start) == 3) {
                //如果来过一次了，说明有环，置为false
                mem.put(start, 2);
            }
            return mem.get(start) == 1;
        }

        //dfs之前先把本start标记一下
        mem.put(start, 3);

        boolean res = false;
        //替换XL
        res = res || canTransform(start.replaceFirst("XL", "LX"), end);
        //替换RX
        res = res || canTransform(start.replaceFirst("RX", "XR"), end);
        //两个都替换
        res = res || canTransform(start.replaceFirst("RX", "XR").replaceFirst("XL", "LX"), end);
        //不替换，但是以11标记已经考察过的XL，以22标记已经考察过的RX
        res = res || canTransform(start.replaceFirst("RX", "22").replaceFirst("XL", "11"), end);


        mem.put(start, res ? 1 : 2);

        return res;
    }

}
