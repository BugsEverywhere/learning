package indi.simon.learning.leetcode.gogo20220425;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz139_reviewed {

    public static void main(String[] args) {
        Quiz139_reviewed quiz139 = new Quiz139_reviewed();
        String[] arr = new String[]{"leet", "code"};
        List<String> wordDict = Arrays.asList(arr);
        boolean res = quiz139.wordBreak("leetcode", wordDict);
        System.out.println(res);
    }

    private Boolean[] mem;

    public boolean wordBreak(String s, List<String> wordDict) {
        mem = new Boolean[s.length()];
        return wordBreakInternal(s, new HashSet<>(wordDict), 0);
    }

    private boolean wordBreakInternal(String s, Set<String> wordDict, int startIndex) {
        if (startIndex >= s.length()) {
            return true;
        }

        if (mem[startIndex] != null) {
            return mem[startIndex];
        }

        //遍历从startIndex开始的所有可能，往下迭代
        boolean res = false;
        for (int i = startIndex + 1; i <= s.length(); i++) {
            if (wordDict.contains(s.substring(startIndex, i))) {
                res = res | wordBreakInternal(s, wordDict, i);
            }
        }

        mem[startIndex] = res;
        return res;
    }


    //todo: 自己改良之后的记忆化回溯方式

    //以下官方DP解法：

    public boolean wordBreakOfficial(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        //定义 dp[i] 表示字符串 s 前 i 个字符组成的字符串 s[0..i−1] 是否能被空格拆分成若干个字典中出现的单词
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        //步骤拆分
        for (int i = 1; i <= s.length(); i++) {
            //每个步骤下的状态转移，要遍历处理该步骤下之前的所有状态
            for (int j = 0; j < i; j++) {
                //dp[j]表示之前的状态，如果哪怕有一个j满足：dp[j]为true，并且j到i的子串也在字典中出现，那么dp[i]就也为true，结束遍历
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


}
