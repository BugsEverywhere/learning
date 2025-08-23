package indi.simon.learning.复习.字符串;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串 s ，返回 其重新排列组合后可能构成的所有回文字符串，并去除重复的组合 。
 *
 * 你可以按 任意顺序 返回答案。如果 s 不能形成任何回文排列时，则返回一个空列表。
 *
 *
 *
 * 示例 1：
 *
 * 输入: s = "aabb"
 * 输出: ["abba", "baab"]
 * 示例 2：
 *
 * 输入: s = "abc"
 * 输出: []
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 */
public class Quiz267_回文排列ii {

    public static void main(String[] args) {
        Quiz267_回文排列ii quiz = new Quiz267_回文排列ii();
        List<String> res = quiz.generatePalindromes("abc");
        System.out.println(res);
    }

    //记录半边回文字符串
    private List<String> half;

    //记录完整的回文字符串
    private List<String> res;

    public List<String> generatePalindromes(String s) {
        half = new ArrayList<>();
        res = new ArrayList<>();

        //s中所有字符出现次数记一下
        int[] cnt = new int[26];
        for(char c : s.toCharArray()){
            cnt[(int)(c - 'a')]++;
        }

        //只允许有一个字符出现奇数次，不然就无法拼成回文
        //把出现奇数次的字符记下来
        int oddCharIndex = -1;
        for(int i = 0; i < 26; i++){
            if(cnt[i] % 2 != 0){
                //校验是否还有其他字符出现奇数次，有则直接返回[]
                if(oddCharIndex != -1){
                    return new ArrayList<>();
                } else {
                    oddCharIndex = i;
                }
            }
            //出现次数减半，因为后头递归只需要得到半边回文
            cnt[i] = cnt[i] / 2;
        }

        //递归得到所有半边回文字符串
        dfs(new StringBuffer(), cnt, s.length() / 2);

        //拼结果
        for(String halfStr : half){
            //前半部分
            StringBuffer resStr = new StringBuffer(halfStr);
            //加一个中心，如果有
            if(oddCharIndex != -1){
                resStr.append((char)('a' + oddCharIndex));
            }
            //后半部分，half反转一下
            String reversed = new StringBuilder(halfStr).reverse().toString();
            resStr.append(reversed);
            res.add(resStr.toString());
        }

        //收工
        return res;

    }

    public void dfs(StringBuffer path, int[] cnt, int n){
        if(path.length() == n){
            half.add(path.toString());
            return;
        }

        for(int i=0;i < cnt.length;i++){
            if(cnt[i] == 0){
                continue;
            }
            path.append((char)('a' + i));
            cnt[i]--;
            dfs(path, cnt, n);
            path.deleteCharAt(path.length() - 1);
            cnt[i]++;
        }
    }

}
