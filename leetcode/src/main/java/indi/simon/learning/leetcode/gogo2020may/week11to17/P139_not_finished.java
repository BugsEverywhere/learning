package indi.simon.learning.leetcode.gogo2020may.week11to17;

import java.util.Arrays;
import java.util.List;

public class P139_not_finished {

    public static void main(String[] args) {
        String s = "ccaccc";
        List<String> list = Arrays.asList("cc", "ac");
        boolean res = wordBreak(s, list);
        System.out.println(res);
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        return wordBreakInternal(s, wordDict, 0);
    }

    private static boolean wordBreakInternal(String s, List<String> wordDict, int index) {

        String tmp = s.replaceAll(" ","");
        if(tmp.equals("")){
            return true;
        }

        if (index >= wordDict.size()) {
            return false;
        }

        //如果s根本就没有该字符串，直接不考虑，往下递归
        if (!s.contains(wordDict.get(index))) {
            return wordBreakInternal(s, wordDict, index + 1);
        }

        //s中有该字符串
        //采纳该字符串
        String s1 = s.replaceAll(wordDict.get(index), " ");
        boolean resConsiderThis = wordBreakInternal(s1, wordDict, index + 1);
        if (resConsiderThis) {
            return true;
        }

        //不采纳该字符串
        boolean resultNotConsider = wordBreakInternal(s, wordDict, index + 1);
        if (resultNotConsider) {
            return true;
        }

        return false;
    }
}
