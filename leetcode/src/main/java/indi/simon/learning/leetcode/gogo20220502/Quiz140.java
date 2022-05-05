package indi.simon.learning.leetcode.gogo20220502;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz140 {

    public static void main(String[] args) {
        Quiz140 quiz140 = new Quiz140();
        String[] arr = new String[]{"cats", "dog", "sand", "and", "cat"};
        List<String> wordDict = Arrays.asList(arr);
        List<String> res = quiz140.wordBreak("catsandog", wordDict);
        System.out.println(res);
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        //用于存放前i个字符所组成的所有句子的组合，key为s.subString(0,i)，value为前i个字符所有句子组合
        Map<String, List<String>> resMap = new HashMap<>();
        //分步骤
        for (int i = 1; i <= s.length(); i++) {
            //状态转移，遍历之前所有步骤的状态完成转移
            int j = 0;
            for (; j < i; j++) {
                if (dp[j] && dictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    //j如果是0，那么此时s.substring(j, i)就是s的前i个字符的子串，设该子串为str1，这时str1是他这一列第一个，所以sentence要new出来
                    List<String> sentenceList = j == 0 ? new ArrayList<>() : resMap.get(s.substring(0, j));
                    if (sentenceList == null) {
                        sentenceList = new ArrayList<>();
                    }
                    List<String> baseListCopy = new ArrayList<>(sentenceList);
                    //更新句子
                    if (j == 0) {
                        baseListCopy.add(s.substring(0, i));
                    } else {
                        for (int k = 0; k < baseListCopy.size(); k++) {
                            baseListCopy.set(k, baseListCopy.get(k) + " " + s.substring(j, i));
                        }
                    }
                    //把句子列表存起来，方便存下一个词儿
                    List<String> iList = resMap.get(s.substring(0, i));
                    if (iList == null) {
                        resMap.put(s.substring(0, i), baseListCopy);
                    } else {
                        iList.addAll(baseListCopy);
                        resMap.put(s.substring(0, i), iList);
                    }
                }
            }
        }

        return resMap.get(s) == null ? Collections.emptyList() : resMap.get(s);
    }
}
