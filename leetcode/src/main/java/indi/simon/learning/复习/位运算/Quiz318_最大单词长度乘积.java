package indi.simon.learning.复习.位运算;

/**
 * 给你一个字符串数组 words ，找出并返回 length(words[i]) * length(words[j]) 的最大值，并且这两个单词不含有公共字母。如果不存在这样的两个单词，返回 0 。
 *
 * 示例 1：
 *
 * 输入：words = ["abcw","baz","foo","bar","xtfn","abcdef"]
 * 输出：16
 * 解释：这两个单词为 "abcw", "xtfn"。
 * 示例 2：
 *
 * 输入：words = ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出：4
 * 解释：这两个单词为 "ab", "cd"。
 * 示例 3：
 *
 * 输入：words = ["a","aa","aaa","aaaa"]
 * 输出：0
 * 解释：不存在这样的两个单词。
 * 提示：
 *
 * 2 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] 仅包含小写字母
 */
public class Quiz318_最大单词长度乘积 {

    public static void main(String[] args) {

    }


    public int maxProduct(String[] words) {
        int length = words.length;
        //masks[i]这个整数代表words[i]中的字符出现情况，因为整数有32位，
        // 只需取前26位用来表示字符是否出现过即可
        int[] masks = new int[length];
        for (int i = 0; i < length; i++) {
            String word = words[i];
            //遍历words[i]的每一个字符，将mask[i]的对应位，置位为1
            int wordLength = word.length();
            for (int j = 0; j < wordLength; j++) {
                masks[i] |= 1 << (word.charAt(j) - 'a');
            }
        }
        //记录结果
        int maxProd = 0;
        //两层遍历找最大长度乘积
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                //两个整数与运算，为0则没有公共字符，艹，简单高效
                if ((masks[i] & masks[j]) == 0) {
                    maxProd = Math.max(maxProd, words[i].length() * words[j].length());
                }
            }
        }
        return maxProd;
    }

}
