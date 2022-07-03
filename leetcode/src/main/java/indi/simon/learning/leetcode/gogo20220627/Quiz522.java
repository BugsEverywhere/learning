package indi.simon.learning.leetcode.gogo20220627;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 很没劲的一道题。。。
public class Quiz522 {

    public static void main(String[] args) {
        String[] strs = new String[]{"aaa", "a", "aa"};
        Quiz522 quiz522 = new Quiz522();
        int res = quiz522.findLUSlength(strs);
        System.out.println(res);
    }

    public int findLUSlength(String[] strs) {
        int maxLength = -1;
        for (int i = 0; i < strs.length; i++) {
            int j = 0;
            for (; j < strs.length; j++) {
                if (i == j) {
                    continue;
                }
                if (isSubStr(strs[i], strs[j])) {
                    break;
                }
            }
            if (j == strs.length) {
                //strs[i]跟其他所有的字符串比较了一圈下来都不是人家的子串
                maxLength = Math.max(maxLength, strs[i].length());
            }
        }
        return maxLength;
    }

    private boolean isSubStr(String subStr, String fatherStr) {
        int i = 0;
        int j = 0;

        while (i < subStr.length() && j < fatherStr.length()) {
            if (subStr.charAt(i) == fatherStr.charAt(j)) {
                i++;
            }
            j++;
        }

        return i == subStr.length();
    }

}
