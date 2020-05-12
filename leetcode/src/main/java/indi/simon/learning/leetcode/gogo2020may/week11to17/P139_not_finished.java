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


        return false;
    }
}

//todo 一开始基本的思路就是不对的，不应该在递归的时候去对字符串消除指定单词，因为字符串情况很复杂，在做消除的时候有可能意外生成或者破坏了其他单词的结构，比如：
// 1. bcbba, [bb,b,ca]，消除的时候首先消除bb，变为了bca，此时ca把本不可以的变成可以的了。
// 2. 为了杜绝1中的情况，在replace的时候就应该替换为空格，这时候暴露了第二种情况：ccaccc，字典是[cc,ac]，在消除cc的时候，就会是变成了" a c"，本来可以的变成不可以的了。
