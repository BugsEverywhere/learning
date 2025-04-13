package indi.simon.learning.复习.字符串;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz139_动态规划_记忆化回溯 {

    public static void main(String[] args) {
        Quiz139_动态规划_记忆化回溯 quiz139 = new Quiz139_动态规划_记忆化回溯();
        String[] arr = new String[]{"leet", "code"};
        List<String> wordDict = Arrays.asList(arr);
        boolean res = quiz139.wordBreak("leetcode", wordDict);
        System.out.println(res);
    }


    //todo：自己的动态规划做法
    public boolean wordBreakDp(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        Set<String> set = new HashSet<>(wordDict);

        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (set.contains(s.substring(j, i))) {
                    dp[i] = dp[j] || dp[i];
                }
            }
        }

        return dp[s.length()];

    }



    //todo：记忆化回溯
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
