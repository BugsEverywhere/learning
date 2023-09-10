package indi.simon.learning.leetcode.gogo20230904;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2023/9/10.
 */
public class Quiz159 {

    public static void main(String[] args) {
        String s = "a";
        Quiz159 quiz159 = new Quiz159();
        int res = quiz159.lengthOfLongestSubstringTwoDistinct(s);
        System.out.println(res);
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s.length() == 1){
            return 1;
        }
        int maxLength = 0;

        int i = 0;
        int j = i + 1;

        Map<Character, Integer> charCnt = new HashMap<>();
        //todo: 注意启动时别忘了初始化j，因为后头循环都是默认前一轮的j的字符已经算上了
        charCnt.put(s.charAt(0), 1);
        charCnt.merge(s.charAt(j), 1, Integer::sum);
        while (i <= j && j < s.length()) {
            if (charCnt.size() <= 2) {
                maxLength = Math.max(maxLength, j - i + 1);
                j++;
                if(j < s.length()){
                    charCnt.merge(s.charAt(j), 1, Integer::sum);
                }
            } else if (charCnt.size() > 2) {
                int newIcnt = charCnt.get(s.charAt(i)) - 1;
                if (newIcnt == 0) {
                    charCnt.remove(s.charAt(i));
                } else {
                    //todo: 此处不要忘了更新被删掉的i的字符的新数目
                    charCnt.put(s.charAt(i),newIcnt);
                }
                i++;
            }
        }

        return maxLength;
    }
}
