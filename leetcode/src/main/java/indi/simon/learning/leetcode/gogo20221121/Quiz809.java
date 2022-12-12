package indi.simon.learning.leetcode.gogo20221121;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz809 {

    public static void main(String[] args) {
        Quiz809 quiz809 = new Quiz809();
        int res = quiz809.expressiveWords("abcd", new String[]{"abc"});
        System.out.println(res);
    }

    public int expressiveWords(String s, String[] words) {
        int res = 0;
        for (String word : words) {
            if (isStretchy(s, word)) {
                res++;
            }
        }
        return res;
    }

    private boolean isStretchy(String s, String word) {
        int i = 0;
        int j = 0;

        while (i < s.length() && j < word.length()) {
            char sChar = s.charAt(i);
            char wChar = word.charAt(j);

            if (sChar != wChar) {
                return false;
            }

            //判断连续出现的sChar和wChar的个数
            int sCharCount = 0;
            while (i < s.length() && s.charAt(i) == sChar) {
                sCharCount++;
                i++;
            }

            int wCharCount = 0;
            while (j < word.length() && word.charAt(j) == wChar) {
                wCharCount++;
                j++;
            }

            if (wCharCount == sCharCount) {
                continue;
            } else if (wCharCount > sCharCount) {
                return false;
            } else if (sCharCount < 3) {
                return false;
            }
        }
        //todo:第一次提交忽略了s多出了字符的情况，所以要判断一下结尾的时候两个指针是否都到达了各自串的末尾
        return i == s.length() && j == word.length();
    }


}
