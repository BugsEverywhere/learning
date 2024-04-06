package indi.simon.learning.leetcode.贪心;

/**
 * Created by Chen Zhuo on 2024/4/5.
 */
//todo: 需要注意的技巧：
// 1. 这种构造字符串的题，不要总是使用StringBuilder来顺序构造，这里就使用了char[]来排列，最终只需要String.valueOf一下即可
// 2. 使用长度为26的整数数组来记录26个字母出现的频率，不要动不动使用map
public class Quiz767_reviewed {

    public static void main(String[] args) {
        Quiz767_reviewed quiz767Reviewed = new Quiz767_reviewed();
        String res = quiz767Reviewed.reorganizeString("vvvlo");
        System.out.println(res);
    }

    public String reorganizeString(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;

        //todo: 技巧：使用整数数组来记录26个字母出现的频率，不要动不动使用map
        int[] count = new int[26];
        for (char c : arr) {
            count[c - 'a']++;
        }

        //找到出现次数最多的字母，记录其下标
        int maxIndex = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > count[maxIndex]) {
                maxIndex = i;
            }
        }

        // 如果出现次数最多的字母，多于整个字符串的一半以上，则肯定没戏，返回""
        //todo: 技巧： >(n+1)/2 同时考虑了数组长度为奇数和偶数的情况，用来判断元素出现频率是否过半很简洁
        if (count[maxIndex] > (n + 1) / 2) {
            return "";
        }

        // 先把出现次数最多的字符放在偶数位置上
        char[] res = new char[n];
        int i = 0;
        while (count[maxIndex]-- > 0) {
            res[i] = (char) ('a' + maxIndex);
            i += 2;
        }

        // 考虑其他的字符
        for (int j = 0; j < count.length; j++) {
            while (count[j]-- > 0) {
                if (i >= n) {
                    // 偶数位置用完了，放到奇数位置
                    i = 1;
                }
                res[i] = (char) ('a' + j);
                i += 2;
            }
        }

        return String.valueOf(res);
    }

}
