package indi.simon.learning.leetcode.gogo20230424;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1048 {

    public static void main(String[] args) {
        Quiz1048 quiz1048 = new Quiz1048();
        int res = quiz1048.longestStrChain(new String[]{"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"});
        System.out.println(res);
    }

    public int longestStrChain(String[] words) {
        //先将单词数组按照长度排序好，磁力链接，啊不，词链肯定是从短的单词开始
        Arrays.sort(words, Comparator.comparingInt(String::length));
        //dp[i]表示words[i]处可得到的最长词链
        int[] dp = new int[words.length];
        //每个单词至少能自己组成长度为1的词链
        Arrays.fill(dp, 1);
        int res = 1;
        //开始遍历，状态转移
        for (int i = 0; i < words.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (words[i].length() - words[j].length() > 1) {
                    break;
                } else if (words[i].length() - words[j].length() == 1 && isPre(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    private boolean isPre(String pre, String post) {
        boolean diff = false;
        int i = 0;
        int j = 0;
        while (i < pre.length() && j < post.length()) {
            if (pre.charAt(i) != post.charAt(j) && diff) {
                return false;
            } else if (pre.charAt(i) != post.charAt(j)) {
                //仅自增长的那个即可
                j++;
                diff = true;
            } else if (pre.charAt(i) == post.charAt(j)) {
                i++;
                j++;
            }
        }
        return i == pre.length();
    }


}
