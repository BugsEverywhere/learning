package indi.simon.learning.复习.动态规划;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 *
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 *
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 *      注意，你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * 提示：
 *
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s 和 wordDict[i] 仅由小写英文字母组成
 * wordDict 中的所有字符串 互不相同
 */
public class Quiz139_单词拆分 {

    public static void main(String[] args) {
        Quiz139_单词拆分 quiz139 = new Quiz139_单词拆分();
        String[] arr = new String[]{"leet", "code"};
        List<String> wordDict = Arrays.asList(arr);
        boolean res = quiz139.wordBreak("leetcode", wordDict);
        System.out.println(res);
    }

    //todo：自己的动态规划做法
    public boolean wordBreak(String s, List<String> wordDict) {
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

}
